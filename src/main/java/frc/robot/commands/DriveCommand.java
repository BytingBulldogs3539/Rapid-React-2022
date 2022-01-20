package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
  private final DriveSubsystem drivetrain;

  public DriveCommand(DriveSubsystem drivetrain) {
    this.drivetrain = drivetrain;

    addRequirements(drivetrain);
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