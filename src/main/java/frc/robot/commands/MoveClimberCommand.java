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
		
		// If up on d-pad is pressed, ignore all of the limits.
		if(!RobotContainer.operatorController.buttonPadUp.get()) {
			// If the left climber position is greater than the max extension height and the climber arm is moving, then set left speed to 0.
			if(climberSubsystem.getLClimberPosition() > RobotContainer.constants.getClimberConstants().getLMaxExtensionHeight() && lSpeed > 0) {
				lSpeed = 0;
			}
			// Same as above except for the right climber motor.
			if(climberSubsystem.getRClimberPosition() > RobotContainer.constants.getClimberConstants().getRMaxExtensionHeight() && rSpeed > 0) {
				rSpeed = 0;
			}

			if(climberSubsystem.getLClimberPosition() < RobotContainer.constants.getClimberConstants().getLClimberSoftBottom() && lSpeed < 0) {
				lSpeed = 0;
			}

			if(climberSubsystem.getRClimberPosition() < RobotContainer.constants.getClimberConstants().getRClimberSoftBottom() && rSpeed < 0) {
				rSpeed = 0;
			}
		}

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
