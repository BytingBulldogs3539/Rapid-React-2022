package frc.robot.autoncommands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utilities.SwerveController;
import com.swervedrivespecialties.swervelib.control.Path;
import com.swervedrivespecialties.swervelib.control.PidConstants;
import com.swervedrivespecialties.swervelib.control.PidController;
import com.swervedrivespecialties.swervelib.control.Trajectory;
import com.swervedrivespecialties.swervelib.control.TrajectoryConstraint;

public class TrajectoryCommandGenerator {
	public static Command getMotionCommand(Path path, TrajectoryConstraint[] constraints,boolean reverse, DriveSubsystem driveSub, Boolean useVision, Boolean useShooterCam) {
		Trajectory trajectory = new Trajectory(path, constraints, 0.2);
		SwerveController swerveControllerCommand = new SwerveController(trajectory, driveSub::getPose,
				new PidController(new PidConstants(
						RobotContainer.constants.getDriveConstants()
								.getTranslationXPIDConstants().getP(),
						RobotContainer.constants.getDriveConstants()
								.getTranslationXPIDConstants().getI(),
						RobotContainer.constants.getDriveConstants()
								.getTranslationXPIDConstants().getD())),
				new PidController(new PidConstants(
						RobotContainer.constants.getDriveConstants()
								.getTranslationXPIDConstants().getP(),
						RobotContainer.constants.getDriveConstants()
								.getTranslationXPIDConstants().getI(),
						RobotContainer.constants.getDriveConstants()
								.getTranslationXPIDConstants().getD())),
				new PidController(new PidConstants(
						RobotContainer.constants.getDriveConstants().getRotationConstants()
								.getP(),
						RobotContainer.constants.getDriveConstants().getRotationConstants()
								.getI(),
						RobotContainer.constants.getDriveConstants().getRotationConstants()
								.getD())),
				new PidController(new PidConstants(
						RobotContainer.constants.getDriveConstants().getShooterCameraPIDConstants()
								.getP(),
						RobotContainer.constants.getDriveConstants().getShooterCameraPIDConstants()
								.getI(),
						RobotContainer.constants.getDriveConstants().getShooterCameraPIDConstants()
								.getD())),
				driveSub::getShooterVisionSeeing, driveSub::getShooterVisionYaw, useVision ,driveSub::getFrontVisionYaw, driveSub::getFrontVisionSeeing, useShooterCam, driveSub,
				new Subsystem[] { (Subsystem) driveSub });

		return (Command) swerveControllerCommand.andThen(() -> driveSub.drive(new ChassisSpeeds(0, 0, 0)),
				new Subsystem[0]);
	}
}
