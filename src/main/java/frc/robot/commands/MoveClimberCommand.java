// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class MoveClimberCommand extends CommandBase {
	ClimberSubsystem climberSubsystem;
	Timer timer = new Timer();

	

	/** Creates a new GrabBarCommand. */
	public MoveClimberCommand(ClimberSubsystem climberSubsystem) {
		addRequirements(climberSubsystem);
		this.climberSubsystem = climberSubsystem;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		timer.reset();
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		double motorSpeed = 0.0;
		double motorDirection = -1.0;

		if (RobotContainer.operatorController.buttonB.get()) {
			motorDirection = 1.0;
		}

		// Allows for robot to climb up by pressing the right trigger (moves both arms).
		if (RobotContainer.operatorController.getRightTrigger() > 0.1) {
			motorSpeed = motorDirection * RobotContainer.operatorController.getRightTrigger();
		}

		climberSubsystem.setMotorSpeed(motorSpeed, RobotContainer.operatorController.buttonPadUp.get()); // Sets the motor speeds

		// If X is pressed, grab the bar.
		if (RobotContainer.operatorController.buttonX.get()) {
			RobotContainer.pneumaticsSubsystem.grabClimbBar();
		}
		boolean shouldRelease = false;
		// The first if above all of this checks if the manual climb button (d-pad up)
		// is not pressed. If so, the code inside of it runs.
		if (!RobotContainer.operatorController.buttonPadUp.get()) {
			// Happens only when right trigger and B are both pressed.
			// If grabber is closed and motor speed is positive, move arms out. Otherwise,
			// if the motor speed is negative, bring the arms back in.
			if (motorSpeed > 0 && RobotContainer.pneumaticsSubsystem.grabberStatus) {
				RobotContainer.pneumaticsSubsystem.setIntakeOut();
				RobotContainer.pneumaticsSubsystem.moveClimberOut();
			} else if (motorSpeed < 0) {
				RobotContainer.pneumaticsSubsystem.moveClimberIn();
			}

			// Automatically released the climb bar if not overriden when the robot is ready
			// to do so.
			if (climberSubsystem.getClimberPosition() < RobotContainer.constants.getClimberConstants()
					.getLMaxExtensionHeight() * 0.70&& climberSubsystem.getClimberPosition() > RobotContainer.constants.getClimberConstants()
					.getLMaxExtensionHeight() * 0.50 && motorSpeed < 0 && RobotContainer.climberSubsystem.getLeftLimit() && RobotContainer.climberSubsystem.getRightLimit()) {
				shouldRelease = true;
				timer.reset();
				timer.start();
			}
		}

		// If Y is pressed, release the bar.
		if (RobotContainer.operatorController.buttonY.get()) {
			RobotContainer.pneumaticsSubsystem.releaseClimbBar();
			// When Y is not pressed, use the limit switches to determine if grabbers need
			// to be closed.
		} else {
			if (!shouldRelease) {
				// If both switches are pressed, close the all grabbers.
				if (RobotContainer.climberSubsystem.getLeftLimit() && RobotContainer.climberSubsystem.getRightLimit()&&timer.hasElapsed(1)) {
					RobotContainer.pneumaticsSubsystem.grabClimbBar();
				}
			} else {
				RobotContainer.pneumaticsSubsystem.releaseClimbBar();
			}
		}

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
