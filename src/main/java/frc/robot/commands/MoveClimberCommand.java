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
		double lMotorSpeed = 0.0;
		double rMotorSpeed = 0.0;
		double motorDirection = 1.0;

		if (RobotContainer.operatorController.buttonB.get()) {
			motorDirection = -1.0;
		}

		// Allows for robot to be tilted by pressing one trigger and for the robot to go
		// straight by pressing both.
		if (RobotContainer.operatorController.getLeftTrigger() > 0.1) {
			lMotorSpeed = motorDirection * RobotContainer.operatorController.getLeftTrigger();
		}

		if (RobotContainer.operatorController.getRightTrigger() > 0.1) {
			rMotorSpeed = motorDirection * RobotContainer.operatorController.getRightTrigger();
		}

		climberSubsystem.setMotorSpeed(lMotorSpeed, rMotorSpeed); // Sets the motor speeds
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
