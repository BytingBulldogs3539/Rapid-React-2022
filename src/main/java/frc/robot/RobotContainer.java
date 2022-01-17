// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.CompConstants;
import frc.robot.constants.Constants;
import frc.robot.constants.PracConstants;
import frc.robot.utilities.MacAddress;

public class RobotContainer {
  private static final String PRACTICE_BOT_MAC_ADDRESS = "";

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public static Constants constants;
  public static MacAddress macAddress = new MacAddress(PRACTICE_BOT_MAC_ADDRESS);

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    if (macAddress.getIsPractice()) {
      constants = new PracConstants();
    } else {
      constants = new CompConstants();
    }

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
