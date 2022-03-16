// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
	/** Creates a new ShooterCommand. */
	ShooterSubsystem shooterSubsystem;
	IntakeSubsystem intakeSubsystem;
	/*** Create a new timer */
	Timer timer = new Timer();

	// Class variables for SM1Speed and KMSpeed
	double SM1Speed;
	double SM2Speed;
	double KMSpeed;
	boolean useVision = false;

	public ShooterCommand(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, boolean useVision,
			int SM1Speed, int SM2Speed, int KMSpeed) {
		this.shooterSubsystem = shooterSubsystem;
		this.intakeSubsystem = intakeSubsystem;
		this.SM1Speed = SM1Speed;
		this.SM2Speed = SM2Speed;
		this.KMSpeed = KMSpeed;

		SmartDashboard.putNumber("KM Speed", 3000);
		SmartDashboard.putNumber("SM1 Speed", 4000);
		SmartDashboard.putNumber("SM2 Speed", 4000);
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
		SM1Speed = SmartDashboard.getNumber("SM1 Speed", 0);
		SM2Speed = SmartDashboard.getNumber("SM2 Speed", 0);

		// If not in target range, reset and then start the timer. This effectively
		// checks for if SM1 is running at the right RPM.
		if (!shooterSubsystem.SM1AtTarget(1500)) {
			if (SM1Speed > 3000) {
				timer.stop();
				timer.reset();
			}
			timer.start();
		}
		if (SM1Speed < 3000) {
			if (timer.hasElapsed(.25)) {
				shooterSubsystem.setKMSpeed(KMSpeed);
				intakeSubsystem.setIntakeSpeed(0.2);
			} else {
				shooterSubsystem.setKMPercentOutput(0);
			}
		} else {
			if (timer.hasElapsed(1)) {
				shooterSubsystem.setKMSpeed(KMSpeed);
				intakeSubsystem.setIntakeSpeed(0.2);
			} else {
				shooterSubsystem.setKMPercentOutput(0);
			}
		}

		if (this.useVision) {
			if (RobotContainer.driveSubsystem.getShooterVisionSeeing()) {
				double pitch = RobotContainer.driveSubsystem.getShooterVisionPitch();
				if (RobotContainer.constants.getShooterConstants().getUseHood(pitch)) {
					RobotContainer.pneumaticsSubsystem.setShooterForward();
				} else {
					RobotContainer.pneumaticsSubsystem.setShooterReverse();
				}
				shooterSubsystem.setSM1Speed(RobotContainer.constants.getShooterConstants().getShooterSpeed(pitch));
				shooterSubsystem.setSM2Speed(RobotContainer.constants.getShooterConstants().getTopShooterSpeed(pitch));
			} else {
				shooterSubsystem.stop();
			}
		} else {
			RobotContainer.pneumaticsSubsystem.setShooterReverse();
			shooterSubsystem.setSM1Speed(SM1Speed);
			shooterSubsystem.setSM2Speed(SM2Speed);
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		shooterSubsystem.stop();
		intakeSubsystem.setIntakeSpeed(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}