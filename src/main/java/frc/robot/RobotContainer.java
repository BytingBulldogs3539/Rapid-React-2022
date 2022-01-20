// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.constants.CompConstants;
import frc.robot.constants.Constants;
import frc.robot.constants.PracConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utilities.LogitechF310;
import frc.robot.utilities.MacAddress;

public class RobotContainer {
  private static final String PRACTICE_BOT_MAC_ADDRESS = "";

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public static Constants constants;
  public static MacAddress macAddress = new MacAddress(PRACTICE_BOT_MAC_ADDRESS);

  public static LogitechF310 driverController = new LogitechF310(1); 
  public static LogitechF310 operatorController = new LogitechF310(0);

  public static IntakeSubsystem intakeSubsystem;

  public static ShooterSubsystem shooterSubsystem;

  public static DriveSubsystem driveSubsystem;

  public RobotContainer() {
    // Configure the button bindings

    if (macAddress.getIsPractice()) {
      constants = new PracConstants();
    } else {
      constants = new CompConstants();
    }

    intakeSubsystem = new IntakeSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    driveSubsystem = new DriveSubsystem();

    configureButtonBindings();
    driveSubsystem.register();

    driveSubsystem.setDefaultCommand(new DriveCommand(
      driveSubsystem,
            () -> modifyAxis(driverController.getLeftStickY()), // Axes are flipped here on purpose
            () -> -modifyAxis(driverController.getLeftStickX()),
            () -> -modifyAxis(driverController.getRightStickX())
    ));
}

public DriveSubsystem getDrivetrain() {
    return driveSubsystem;
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
  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    operatorController.buttonPadDown.whenHeld(new IntakeCommand(1, intakeSubsystem));
    }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
