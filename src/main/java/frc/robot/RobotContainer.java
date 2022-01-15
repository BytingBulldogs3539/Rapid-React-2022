// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.CompConstants;
import frc.robot.constants.Constants;
import frc.robot.constants.PracConstants;

import java.io.IOException;
import java.net.NetworkInterface;

public class RobotContainer {
  private static final String PRACTICE_BOT_MAC_ADDRESS = "";

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  Constants constants;
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    List<byte[]> macAddresses = new ArrayList<>();;
    try
    {
      macAddresses = getMacAddresses();
    }
    catch (IOException e)
    {
      // Don't crash, just log the stacktrace and continue without any mac addresses.
      DriverStation.reportError("Error Retrieving Mac Addresses", false);
    }
    boolean isPracticeBot = false;
    for (byte[] macAddress : macAddresses)
    {
      // Check if we are the practice bot
      if (PRACTICE_BOT_MAC_ADDRESS.equals(macToString(macAddress)))
      {
        isPracticeBot = true;
        break;
      }
    }

    if (!isPracticeBot)
    {
      String[] macAddressStrings = macAddresses.stream().map(RobotContainer::macToString).toArray(String[]::new);
      SmartDashboard.putStringArray("MAC Addresses", macAddressStrings);
      SmartDashboard.putString("Practice Bot MAC Address",PRACTICE_BOT_MAC_ADDRESS);
    }

    if (isPracticeBot){constants = new PracConstants();}
    else{constants = new CompConstants();}
    

  }

    /**
   * Gets the MAC addresses of all present network adapters.
   *
   * @return the MAC addresses of all network adapters.
   */
  private static List<byte[]> getMacAddresses() throws IOException {
    List<byte[]> macAddresses = new ArrayList<>();

    Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

    NetworkInterface networkInterface;
    while (networkInterfaces.hasMoreElements())
    {
      networkInterface = networkInterfaces.nextElement();

      byte[] address = networkInterface.getHardwareAddress();
      if (address == null)
      {
        continue;
      }

      macAddresses.add(address);
    }

    return macAddresses;
  }

  private static String macToString(byte[] address) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < address.length; i++)
    {
      if (i != 0)
      {
        builder.append(':');
      }
      builder.append(String.format("%02X", address[i]));
    }
    return builder.toString();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

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
