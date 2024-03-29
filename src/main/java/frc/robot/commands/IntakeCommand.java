// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class IntakeCommand extends CommandBase {

	IntakeSubsystem intakeSubsystem;
	PneumaticsSubsystem pneumatics;
	ShooterSubsystem shooterSubsystem;

	double speed = 0.0;
	double kDwnSpeed = 0.0;

	/** Creates a new IntakeCommand. */
	public IntakeCommand(double speed, double kDwnSpeed, IntakeSubsystem intakeSubsystem,
			PneumaticsSubsystem pneumatics,
			ShooterSubsystem shooterSubsystem) {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(intakeSubsystem);

		this.speed = speed;
		this.kDwnSpeed = kDwnSpeed;
		this.pneumatics = pneumatics;
		this.shooterSubsystem = shooterSubsystem;

		this.intakeSubsystem = intakeSubsystem;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		pneumatics.setIntakeOut();
		intakeSubsystem.setIntakeSpeed(speed);
		intakeSubsystem.setKnockDownSpeed(kDwnSpeed);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if (!shooterSubsystem.getSensor()) {
			shooterSubsystem.setKMPercentOutput(0.35/2);
		} else {
			shooterSubsystem.setKMPercentOutput(0.0);
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		pneumatics.setIntakeIn();
		intakeSubsystem.setIntakeSpeed(0);
		shooterSubsystem.setKMPercentOutput(0.0);
		intakeSubsystem.setKnockDownSpeed(0.0);

	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {

		return false;
	}
}
