package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.utilities.PIDConstants;

public class DriveCommand extends CommandBase {
	private final DriveSubsystem drivetrain;
	private PIDController frontPIDController;
	private PIDController shooterPIDController;

	public DriveCommand(DriveSubsystem drivetrain) {
		this.drivetrain = drivetrain;

		addRequirements(drivetrain);

		SmartDashboard.putNumber("Steer Ratio", 0.4);
		SmartDashboard.putNumber("Drive Ratio", 0.5);

		// Declares and initializes variables holding the values of the PID constants
		// for both cameras. This helps to make the code easier to read.
		PIDConstants frontPIDConstants = RobotContainer.constants.getDriveConstants().getFrontCameraPIDConstants();
		PIDConstants shooterPIDConstants = RobotContainer.constants.getDriveConstants().getShooterCameraPIDConstants();

		// Creates a new PID controller for each of the cameras with their corresponding
		// PID constants.
		frontPIDController = new PIDController(frontPIDConstants.getP(), frontPIDConstants.getI(),
				frontPIDConstants.getD());
		shooterPIDController = new PIDController(shooterPIDConstants.getP(), shooterPIDConstants.getI(),
				shooterPIDConstants.getD());

		// Sets several values of the front PID Controller
		frontPIDController.setIntegratorRange(0.0, 1.0);
		frontPIDController.setTolerance(4.0);
		frontPIDController.setSetpoint(0);

		shooterPIDController.setIntegratorRange(0.0, 1.0);
		shooterPIDController.setTolerance(.25);
		shooterPIDController.setSetpoint(0);
	}

	private static double deadband(double value, double deadband) {
		if (Math.abs(value) > deadband) {
			if (value > 0.0) {
				return (value - deadband) / (1.0 - deadband);
			} else {
				return (value + deadband) / (1.0 - deadband);
			}
		} else {
			return 0.0;
		}
	}

	private static double modifyAxis(double value) {
		// Deadband
		value = deadband(value, 0.05);

		// Square the axis
		value = Math.copySign(value * value, value);

		return value;
	}

	@Override
	public void execute() {
		Rotation2d gyroAngle = drivetrain.getGyroscopeRotation();
		double translationXPercent = modifyAxis(RobotContainer.driverController.getLeftStickY());
		double translationYPercent = -modifyAxis(RobotContainer.driverController.getLeftStickX());
		double rotationPercent = -modifyAxis(RobotContainer.driverController.getRightStickX());
		double driveRatio = SmartDashboard.getNumber("Drive Ratio", 0.5);

		if(RobotContainer.driverController.getRightTrigger()>.1)
		{
			driveRatio = 1.0;
		}
		if(RobotContainer.driverController.getLeftTrigger()>.1)
		{
			drivetrain.setDriverMode(false);
			if (!frontPIDController.atSetpoint()) {
					translationYPercent = frontPIDController.calculate(RobotContainer.driveSubsystem.getFrontVisionYaw());
			}
		}

		if (RobotContainer.driverController.buttonBR.get()) {
				
			gyroAngle = Rotation2d.fromDegrees(0);
		}

		if (RobotContainer.driverController.buttonBL.get()) {
			if(drivetrain.getShooterVisionSeeing()) {
				rotationPercent = shooterPIDController.calculate(RobotContainer.driveSubsystem.getShooterVisionYaw());
				RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 0);
			} else {
				RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 1);
			}
		} else {
			RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 0);
		}

		drivetrain.drive(
				ChassisSpeeds.fromFieldRelativeSpeeds(
						driveRatio * translationXPercent * DriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
						driveRatio * translationYPercent * DriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
						SmartDashboard.getNumber("Steer Ratio", 0.4) * rotationPercent * DriveSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
						gyroAngle));
	}

	@Override
	public void end(boolean interrupted) {
		// Stop the drivetrain
		drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
		RobotContainer.driverController.setRumble(RumbleType.kLeftRumble, 0);
	}
}