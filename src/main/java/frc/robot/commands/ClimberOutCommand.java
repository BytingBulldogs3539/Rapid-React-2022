// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;

public class ClimberOutCommand extends CommandBase {
	ClimberSubsystem climberSubsystem;
	PneumaticsSubsystem pneumaticsSubsystem;
	/** Creates a new GrabBarCommand. */
	public ClimberOutCommand(ClimberSubsystem climberSubsystem, PneumaticsSubsystem pneumatics) {
		this.climberSubsystem = climberSubsystem;
		this.pneumaticsSubsystem = pneumatics;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		pneumaticsSubsystem.moveClimberOut();
		pneumaticsSubsystem.setIntakeOut();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return true;
	}
}
