package frc.robot.utilities;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.ErrorMessages;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.Supplier;
import com.swervedrivespecialties.swervelib.control.Path;
import com.swervedrivespecialties.swervelib.control.PidController;
import com.swervedrivespecialties.swervelib.control.Trajectory;
import com.swervedrivespecialties.swervelib.math.RigidTransform2;
import com.swervedrivespecialties.swervelib.math.Vector2;

public class SwerveController extends CommandBase {
    private final Timer m_timer = new Timer();

    private final Trajectory m_trajectory;
    private final Supplier<RigidTransform2> m_pose;
    private final PidController m_xController;
    private final PidController m_yController;
    private final PidController m_thetaController;
    private final Supplier<Double> m_targetSupplier;
    private final boolean m_shouldVisionTrack;
    private final Supplier<Boolean> m_targetIsAvail;
    private final DriveSubsystem m_driveSub;
    private double lastTime = 0.0D;

    public SwerveController(Trajectory trajectory, Supplier<RigidTransform2> pose, PidController xController,
            PidController yController, PidController thetaController, Supplier<Boolean> targetIsAvail,
            Supplier<Double> targetSupplier, Boolean shouldVisionTrack, DriveSubsystem driveSub,
            Subsystem... requirements) {
        this.m_trajectory = (Trajectory) ErrorMessages.requireNonNullParam(trajectory, "trajectory",
                "SwerveControllerCommand");
        this.m_pose = (Supplier<RigidTransform2>) ErrorMessages.requireNonNullParam(pose, "pose",
                "SwerveControllerCommand");

        this.m_xController = (PidController) ErrorMessages.requireNonNullParam(xController, "xController",
                "SwerveControllerCommand");
        this.m_yController = (PidController) ErrorMessages.requireNonNullParam(yController, "xController",
                "SwerveControllerCommand");
        this.m_thetaController = (PidController) ErrorMessages.requireNonNullParam(thetaController, "thetaController",
                "SwerveControllerCommand");

        this.m_targetIsAvail = (Supplier<Boolean>) ErrorMessages.requireNonNullParam(targetIsAvail, "targetIsAvail",
                "SwerveControllerCommand");
        this.m_targetSupplier = (Supplier<Double>) ErrorMessages.requireNonNullParam(targetSupplier, "targetSuplier",
                "SwerveControllerCommand");
        this.m_shouldVisionTrack = ((Boolean) ErrorMessages.requireNonNullParam(shouldVisionTrack, "shouldVisionTrack",
                "SwerveControllerCommand")).booleanValue();
        this.m_driveSub = (DriveSubsystem) ErrorMessages.requireNonNullParam(driveSub, "driveSub",
                "SwerveControllerCommand");
        addRequirements(requirements);
    }

    public void initialize() {
        this.m_timer.reset();
        this.m_timer.start();
    }

    public void execute() {
        double curTime = this.m_timer.get();

        double dt = curTime - this.lastTime;

        Trajectory.State lastDesiredState = this.m_trajectory.calculate(this.lastTime);
        Path.State lastDesiredPose = lastDesiredState.getPathState();
        double LastDesiredVelocity = lastDesiredState.getVelocity();

        Trajectory.State desiredState = this.m_trajectory.calculate(curTime);
        Path.State desiredPose = desiredState.getPathState();
        double desiredVelocity = desiredState.getVelocity();

        //SmartDashboard.putNumber("Expected X", (desiredPose.getPosition()).x);
        //SmartDashboard.putNumber("Expected Y", (desiredPose.getPosition()).y);
        //SmartDashboard.putNumber("Expected Angle", desiredPose.getRotation().toDegrees());

        //SmartDashboard.putNumber("Real X", ((RigidTransform2) this.m_pose.get()).translation.x);
        //SmartDashboard.putNumber("Real Y", ((RigidTransform2) this.m_pose.get()).translation.y);
        //SmartDashboard.putNumber("Real Angle", ((RigidTransform2) this.m_pose.get()).rotation.toDegrees());

        this.m_xController.setSetpoint((desiredPose.getPosition()).x);
        double angle = Math.atan(((desiredPose.getPosition()).y - (lastDesiredPose.getPosition()).y)
                / ((desiredPose.getPosition()).x - (lastDesiredPose.getPosition()).x));

        double xvel = Math.cos(angle) * desiredVelocity;

        double targetXVel = this.m_xController.calculate(((RigidTransform2) this.m_pose.get()).translation.x, dt)
                + xvel * RobotContainer.constants.getAutoConstants().getKFController();

        this.m_yController.setSetpoint((desiredPose.getPosition()).y);
        double yvel = Math.sin(angle) * desiredVelocity;

        double targetYVel = this.m_yController.calculate(((RigidTransform2) this.m_pose.get()).translation.y, dt)
                + yvel * RobotContainer.constants.getAutoConstants().getKFController();

        this.m_thetaController.setInputRange(0.0D, 6.283185307179586D);
        this.m_thetaController.setOutputRange(-1.0D, 1.0D);
        this.m_thetaController.setContinuous(true);

        double targetAngularVel = 0.0D;

        if (this.m_shouldVisionTrack) {
            if (((Boolean) this.m_targetIsAvail.get()).booleanValue()) {
                this.m_thetaController.setSetpoint(0.0D);
                targetAngularVel = this.m_thetaController
                        .calculate(((Double) this.m_targetSupplier.get()).doubleValue(), dt);
            } else {

                this.m_thetaController.setSetpoint(desiredPose.getRotation().toRadians());
                targetAngularVel = this.m_thetaController
                        .calculate(((RigidTransform2) this.m_pose.get()).rotation.toRadians(), dt);
            }
        } else {
            this.m_thetaController.setSetpoint(desiredPose.getRotation().inverse().toRadians());

            targetAngularVel = this.m_thetaController
                    .calculate(((RigidTransform2) this.m_pose.get()).rotation.toRadians(), dt);
        }

        this.m_driveSub.drive(new Vector2(targetXVel, targetYVel), targetAngularVel, true);

        this.lastTime = this.m_timer.get();
    }

    public void end(boolean interrupted) {
        this.m_timer.stop();
    }

    public boolean isFinished() {
        return this.m_timer.hasPeriodPassed(this.m_trajectory.getDuration());
    }
}

/*
 * Location: C:\Users\Ivan\Infinite-Recharge-2021.jar!\frc\robo\\utilities\
 * SwerveController.class Java compiler version: 11 (55.0) JD-Core Version:
 * 1.1.3
 */