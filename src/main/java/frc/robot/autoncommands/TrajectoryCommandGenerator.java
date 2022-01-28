package frc.robot.autoncommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utilities.SwerveController;
//import java.util.Objects;
import com.swervedrivespecialties.swervelib.control.Path;
import com.swervedrivespecialties.swervelib.control.PidConstants;
import com.swervedrivespecialties.swervelib.control.PidController;
import com.swervedrivespecialties.swervelib.control.Trajectory;
import com.swervedrivespecialties.swervelib.math.Vector2;

public class TrajectoryCommandGenerator {
    public static Command getMotionCommand(Path path, boolean reverse, DriveSubsystem driveSub) {
        Trajectory trajectory = new Trajectory(path, RobotContainer.constants.getDriveConstants().getConstraints(),1.0D);
        /*
         * Objects.requireNonNull(driveSub); 
         * Objects.requireNonNull(driveSub);
         * Objects.requireNonNull(driveSub);
         */
        SwerveController swerveControllerCommand = new SwerveController(trajectory, driveSub::getPose,
                new PidController(new PidConstants(RobotContainer.constants.getAutoConstants().getKPXController(),
                        RobotContainer.constants.getAutoConstants().getKIXController(),
                        RobotContainer.constants.getAutoConstants().getKDXController())),
                new PidController(new PidConstants(RobotContainer.constants.getAutoConstants().getKPYController(),
                        RobotContainer.constants.getAutoConstants().getKIYController(),
                        RobotContainer.constants.getAutoConstants().getKDYController())),
                new PidController(new PidConstants(
                        RobotContainer.constants.getAutoConstants().getKPThetaController(), 0.0D, 0.0D)),
                driveSub::getVisionSeeing, driveSub::getVisionAngle, Boolean.valueOf(false), driveSub,
                new Subsystem[] { (Subsystem) driveSub });

        return (Command) swerveControllerCommand.andThen(() -> driveSub.drive(new Vector2(0.0D, 0.0D), 0.0D, false),
                new Subsystem[0]);
    }
}

/*
 * Location: C:\Users\Ivan\Infinite-Recharge-2021.jar!\frc\robot\autonCommands\
 * TrajectoryCommandGenerator.class Java compiler version: 11 (55.0) JD-Core
 * Version: 1.1.3
 */