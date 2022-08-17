// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.util.net.PortForwarder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.DistanceTest;
import frc.robot.auton.FourBallAuton;
import frc.robot.auton.OneBallAuton;
import frc.robot.auton.QuickerFourBallAuton;
import frc.robot.auton.TwoBallAuton;
import frc.robot.auton.TwoBallAutonLeft;
import frc.robot.auton.ThreeBallAuton;
import frc.robot.commands.ClimberOutCommand;
import frc.robot.commands.ClimberInCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.MoveShooterHood;
import frc.robot.commands.ShootReverse;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.TurretCommand;
import frc.robot.commands.ZeroGyroCommand;
import frc.robot.constants.CompConstants;
import frc.robot.constants.Constants;
import frc.robot.constants.PracConstants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LightsSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.utilities.LogitechF310;
import frc.robot.utilities.MacAddress;

public class RobotContainer {
	private static final String PRACTICE_BOT_MAC_ADDRESS = "00:80:2F:33:C3:90";

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public static Constants constants;
	public static MacAddress macAddress = new MacAddress(PRACTICE_BOT_MAC_ADDRESS);

	public static LogitechF310 driverController = new LogitechF310(1);
	public static LogitechF310 operatorController = new LogitechF310(0);

	/* public static IntakeSubsystem intakeSubsystem;
	public static ShooterSubsystem shooterSubsystem;
	public static DriveSubsystem driveSubsystem;
	public static ClimberSubsystem climberSubsystem;
	public static LightsSubsystem lightsSubsystem; */

	public static PneumaticsSubsystem pneumaticsSubsystem;
	public static TurretSubsystem turretSubsystem;

	public SendableChooser<Command> chooser;

	public RobotContainer() {
		PortForwarder.add(5800, "10.35.39.10", 5800);
		
		PortForwarder.add(1181, "10.35.39.10", 1181);
		
		PortForwarder.add(1182, "10.35.39.10", 1182);
		
		PortForwarder.add(1183, "10.35.39.10", 1183);
		
		PortForwarder.add(1184, "10.35.39.10", 1184);

		SmartDashboard.putBoolean("Practice", false);
		if (macAddress.getIsPractice()) {
			SmartDashboard.putBoolean("Practice", true);
			constants = new PracConstants();
		} else {
			constants = new CompConstants();
		}

		/* intakeSubsystem = new IntakeSubsystem();
		shooterSubsystem = new ShooterSubsystem();
		driveSubsystem = new DriveSubsystem();
		climberSubsystem = new ClimberSubsystem();
		lightsSubsystem = new LightsSubsystem(); */

		pneumaticsSubsystem = new PneumaticsSubsystem(); // Creates pneumaticsSystem object *declared earlier
		turretSubsystem = new TurretSubsystem(); // Creates turretSubsystem object (declared earlier)

		configureButtonBindings();
		putAuton();

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

		/* operatorController.buttonTL.whenHeld(new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 1200, 1200, 3000));
		operatorController.buttonBL.whenHeld(new ShootReverse(shooterSubsystem));
		operatorController.buttonBR.whenHeld(new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 3400, 4600, 3000));
		operatorController.buttonPadDown
				.whenHeld(new IntakeCommand(1, 0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem));
		operatorController.buttonA.whenHeld(new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 2900, 3200, 2000)); // Static shot
		operatorController.buttonPadRight
				.whenHeld(new ShooterCommand(shooterSubsystem, intakeSubsystem, false, true, 2800, 2800, 2000)); // Vision shot
		operatorController.buttonSELECT.whenPressed(new ClimberOutCommand(climberSubsystem, pneumaticsSubsystem));
		operatorController.buttonSTART.whenPressed(new ClimberInCommand(climberSubsystem, pneumaticsSubsystem));
		operatorController.buttonB.whenHeld(new MoveShooterHood(pneumaticsSubsystem));
				operatorController.buttonPadLeft.whenHeld(new ShooterCommand(shooterSubsystem, intakeSubsystem, true, true, 2800, 2800, 2000)); // Revs to setpoint using vision.
		
		*/

		operatorController.buttonBR.whenHeld(new TurretCommand(1.0, turretSubsystem)); // Move the turret right (bottom right bumper)
		operatorController.buttonBL.whenHeld(new TurretCommand(-1.0, turretSubsystem)); // Move the turret left (bottom left bumper)

		driverController.buttonSTART.whenPressed(new ZeroGyroCommand());
	}

	/*** Autons not used at the moment, so none are put into the Smart Dashboard. */
	public void putAuton() {
		/* chooser = new SendableChooser<Command>();
		chooser.addOption("TwoBallAutonLEFT",
		new TwoBallAutonLeft(driveSubsystem, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem));
		chooser.addOption("TwoBallAuton",
				new TwoBallAuton(driveSubsystem, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem));
		chooser.addOption("ThreeBallAuton",
				new ThreeBallAuton(driveSubsystem, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem));
		chooser.addOption("OneBallAuton",
				new OneBallAuton(driveSubsystem, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem));		
		chooser.addOption("FourBallAuton",
				new FourBallAuton(driveSubsystem, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem));
		chooser.addOption("QuickerFourBallAuton",
				new QuickerFourBallAuton(driveSubsystem, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem));
		SmartDashboard.putData("Auto Chooser", chooser); */
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
