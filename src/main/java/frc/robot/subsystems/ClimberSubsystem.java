// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.MoveClimberCommand;
import frc.robot.utilities.GearRatio;

public class ClimberSubsystem extends SubsystemBase {
	private TalonSRX lClimber;
	private TalonSRX rClimber;

	final boolean hasLClimber;
	final boolean hasRClimber;

	/** Creates a new ClimberSubsystem. */
	public ClimberSubsystem() {
		if (RobotContainer.constants.getClimberConstants().getLClimberMotorID() != -1) {
			lClimber = configMotor(RobotContainer.constants.getClimberConstants().getLClimberMotorID(),
					RobotContainer.constants.getClimberConstants().getLClimberGearRatio());
			hasLClimber = true;
		} else {
			hasLClimber = false;
		}

		if (RobotContainer.constants.getClimberConstants().getRClimberMotorID() != -1) {
			rClimber = configMotor(RobotContainer.constants.getClimberConstants().getRClimberMotorID(),
					RobotContainer.constants.getClimberConstants().getRClimberGearRatio());
			hasRClimber = true;
		} else {
			hasRClimber = false;
		}

		this.setDefaultCommand(new MoveClimberCommand(this));
	}

	public TalonSRX configMotor(int id, GearRatio gearRatio) {
		TalonSRX motor = new TalonSRX(id);
		motor.setInverted(gearRatio.getInverted());
		motor.setSensorPhase(gearRatio.getInverted());
		motor.setNeutralMode(NeutralMode.Brake);
		motor.configSupplyCurrentLimit(
				new SupplyCurrentLimitConfiguration(true, gearRatio.getCurrentLimit(), gearRatio.getCurrentLimit(), 0));
		return motor;
	}

	/*** Sets the speed of both the left and right climbers. */
	public void setMotorSpeed(double lClimberSpeed, double rClimberSpeed) {
		if (hasLClimber)
			lClimber.set(ControlMode.PercentOutput, lClimberSpeed);

		if (hasRClimber)
			rClimber.set(ControlMode.PercentOutput, rClimberSpeed);
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}