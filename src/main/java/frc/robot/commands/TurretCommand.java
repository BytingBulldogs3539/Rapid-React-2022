// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

public class TurretCommand extends CommandBase {

	TurretSubsystem turretSubsystem;

	double speed = 0.0;

	/** Creates a new TurretCommand. */
	public TurretCommand(double speed, TurretSubsystem turretSubsystem) {
		// Use addRequirements() here to declare subsystem dependencies.
		/* addRequirements(turretSubsystem); */

    // Insert how this works later
		this.speed = speed;
		this.turretSubsystem = turretSubsystem;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		turretSubsystem.setTurretSpeed(speed);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		turretSubsystem.setTurretSpeed(0.0); // Sets the speed of the turret to 0.
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {

		return false;
	}
}
