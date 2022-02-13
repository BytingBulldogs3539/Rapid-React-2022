// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
	/** Creates a new ShooterCommand. */
	ShooterSubsystem shooterSubsystem;
	/*** Create a new timer */
	Timer timer = new Timer();

	public ShooterCommand(ShooterSubsystem shooterSubsystem) {
		this.shooterSubsystem = shooterSubsystem;
		SmartDashboard.putNumber("KM Speed", 0);
		SmartDashboard.putNumber("SM1 Speed", 0);
		SmartDashboard.putNumber("SM2 Speed", 0);
		SmartDashboard.putNumber("SM3 Speed", 0);
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
		if (SmartDashboard.getNumber("KM Speed", 0) == 0) {
			shooterSubsystem.setKMPercentOutput(0);
		} else {
			double tolerance = 200;
			if (timer.hasElapsed(2)) {
				// if(shooterSubsystem.SM1AtTarget(tolerance) &&
				// shooterSubsystem.SM2AtTarget(tolerance) &&
				// shooterSubsystem.SM3AtTarget(tolerance))
				shooterSubsystem.setKMSpeed(SmartDashboard.getNumber("KM Speed", 0));
			}

		}
		if (SmartDashboard.getNumber("SM1 Speed", 0) == 0) {
			shooterSubsystem.setSM1PercentOutput(0);
		} else
			shooterSubsystem.setSM1Speed(SmartDashboard.getNumber("SM1 Speed", 0));
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
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
