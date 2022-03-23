// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class MoveClimberCommand extends CommandBase {
	ClimberSubsystem climberSubsystem;

	/** Creates a new GrabBarCommand. */
	public MoveClimberCommand(ClimberSubsystem climberSubsystem) {
		addRequirements(climberSubsystem);
		this.climberSubsystem = climberSubsystem;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {

	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		double lSpeed = RobotContainer.operatorController.getLeftStickY();
		double rSpeed = RobotContainer.operatorController.getRightStickY();
		climberSubsystem.setMotorSpeed(lSpeed, rSpeed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		climberSubsystem.setMotorSpeed(0.0, 0.0); // Stops the climber
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
