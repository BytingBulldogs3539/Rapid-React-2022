// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
	/** Creates a new ShooterCommand. */
	ShooterSubsystem shooterSubsystem;
	/*** Create a new timer */
	Timer timer = new Timer();

	// Class variables for SM1Speed and KMSpeed
	int SM1Speed;
	int KMSpeed;
	boolean useVision = false;

	public ShooterCommand(ShooterSubsystem shooterSubsystem, boolean useVision, int SM1Speed, int KMSpeed) {
		this.shooterSubsystem = shooterSubsystem;
		this.SM1Speed = SM1Speed;
		this.KMSpeed = KMSpeed;
		
		SmartDashboard.putNumber("KM Speed", 3000);
		SmartDashboard.putNumber("SM1 Speed", 4000);
		this.useVision = useVision;
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
		// If not in target range, reset and then start the timer. This effectively checks for if SM1 is running at the right RPM.
		if(!shooterSubsystem.SM1AtTarget(1500)) {
			timer.stop();
			timer.reset();
			timer.start();
		}
		if (SmartDashboard.getNumber("KM Speed", 0) == 0) {
			shooterSubsystem.setKMPercentOutput(0);
		} else {
			double tolerance = 200;
			if (timer.hasElapsed(1)) {
				// if(shooterSubsystem.SM1AtTarget(tolerance) &&
				// shooterSubsystem.SM2AtTarget(tolerance) &&
				// shooterSubsystem.SM3AtTarget(tolerance))
				shooterSubsystem.setKMSpeed(KMSpeed);
			} else {
				shooterSubsystem.setKMPercentOutput(0);
			}

		}
		if (SmartDashboard.getNumber("SM1 Speed", 0) == 0) {
			shooterSubsystem.setSM1PercentOutput(0);
		} else
		{
			if(this.useVision)
			{
				if(RobotContainer.driveSubsystem.getShooterVisionSeeing()) {
					shooterSubsystem.setSM1Speed(RobotContainer.constants.getShooterConstants().getShooterSpeed(RobotContainer.driveSubsystem.getShooterVisionPitch()));
					RobotContainer.operatorController.setRumble(RumbleType.kLeftRumble, 0); // Stops controller rumble
				} else {
					RobotContainer.operatorController.setRumble(RumbleType.kLeftRumble, 1); // Starts controller rumble
					shooterSubsystem.stop();
				}
			} else {
				shooterSubsystem.setSM1Speed(SM1Speed);
			}

		}
		if (SmartDashboard.getNumber("SM2 Speed", 0) == 0) {
			shooterSubsystem.setSM2PercentOutput(0);
		} else
			shooterSubsystem.setSM2Speed(SmartDashboard.getNumber("SM2 Speed", 0));

		if (SmartDashboard.getNumber("SM3 Speed", 0) == 0) {
			shooterSubsystem.setSM3PercentOutput(0);
		} else
			shooterSubsystem.setSM3Speed(SmartDashboard.getNumber("SM3 Speed", 0));

	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		shooterSubsystem.stop();
		RobotContainer.operatorController.setRumble(RumbleType.kLeftRumble, 0); // Stops controller rumble
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}