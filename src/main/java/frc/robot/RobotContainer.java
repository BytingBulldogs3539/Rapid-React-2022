// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.ArchTest;
import frc.robot.auton.Back2Start;
import frc.robot.auton.DistanceTest;
import frc.robot.auton.Lapse;
import frc.robot.commands.ClimberOutCommand;
import frc.robot.commands.ClimberInCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.constants.CompConstants;
import frc.robot.constants.Constants;
import frc.robot.constants.PracConstants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utilities.LogitechF310;
import frc.robot.utilities.MacAddress;

public class RobotContainer {
  private static final String PRACTICE_BOT_MAC_ADDRESS = "00:80:2F:33:C3:8F:00:80:2F:33:C3:90";

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

  public static ClimberSubsystem climberSubsystem;

  public static PneumaticsSubsystem pneumaticsSubsystem;

  public SendableChooser<Command> chooser;

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
    pneumaticsSubsystem = new PneumaticsSubsystem();

    configureButtonBindings();
    putAuton();

  }

  public DriveSubsystem getDrivetrain() {
    return driveSubsystem;
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

    operatorController.buttonPadDown.whenHeld(new IntakeCommand(.5, 1.0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem));
    operatorController.buttonA.whenHeld(new ShooterCommand(shooterSubsystem));
    operatorController.buttonSELECT.whenPressed(new ClimberOutCommand(climberSubsystem, pneumaticsSubsystem));
    operatorController.buttonSTART.whenPressed(new ClimberInCommand(climberSubsystem, pneumaticsSubsystem));
  }

  public void putAuton() {
    chooser = new SendableChooser<Command>();
    chooser.setDefaultOption("DistanceTest", new DistanceTest(driveSubsystem));
    chooser.addOption("ArchTest", new ArchTest(driveSubsystem));
    chooser.addOption("Lapse", new Lapse(driveSubsystem));
    chooser.addOption("Back2Start", new Back2Start(driveSubsystem));
    SmartDashboard.putData("Auto Chooser", chooser);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return this.chooser.getSelected();
}
}
