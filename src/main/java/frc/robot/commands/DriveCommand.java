package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
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

    // Declares and initializes variables holding the values of the PID constants for both cameras. This helps to make the code easier to read.
    PIDConstants frontPIDConstants = RobotContainer.constants.getDriveConstants().getFrontCameraPIDConstants();
    PIDConstants shooterPIDConstants = RobotContainer.constants.getDriveConstants().getShooterCameraPIDConstants();

    // Creates a new PID controller for each of the cameras with their corresponding PID constants.
    frontPIDController = new PIDController(frontPIDConstants.getP(), frontPIDConstants.getI(), frontPIDConstants.getD());
    shooterPIDController = new PIDController(shooterPIDConstants.getP(), shooterPIDConstants.getI(), shooterPIDConstants.getD());
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
    double translationXPercent = modifyAxis(RobotContainer.driverController.getLeftStickY());
    double translationYPercent = -modifyAxis(RobotContainer.driverController.getLeftStickX());
    double rotationPercent = -modifyAxis(RobotContainer.driverController.getRightStickX());

    drivetrain.drive(
        ChassisSpeeds.fromFieldRelativeSpeeds(
            .3 * translationXPercent * DriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            .3 * translationYPercent * DriveSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            .3 * rotationPercent * DriveSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND,
            drivetrain.getGyroscopeRotation())

    );
  }

  @Override
  public void end(boolean interrupted) {
    // Stop the drivetrain
    drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
  }
}