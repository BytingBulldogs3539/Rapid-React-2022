// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.MoveClimberCommand;
import frc.robot.utilities.GearRatio;

public class ClimberSubsystem extends SubsystemBase {
	private TalonSRX lClimber;
	private TalonSRX rClimber;

	private DigitalInput leftLimit;
	private DigitalInput rightLimit;

	final boolean hasLeftLimit;
	final boolean hasRightLimit;

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

		// Checks for a left limit switch. If it is on the robot (if there is an ID for it), then an object for it is created here. Otherwise, it is not created and its lack of presence is noted.
		if(RobotContainer.constants.getClimberConstants().getLeftLimitID() != -1) {
			leftLimit = new DigitalInput(RobotContainer.constants.getClimberConstants().getLeftLimitID());
			SmartDashboard.putData(leftLimit);
			hasLeftLimit = true;
		} else {
			hasLeftLimit = false;
		}

		// Checks for a right limit switch. If it is on the robot (if there is an ID for it), then an object for it is created here. Otherwise, it is not created and its lack of presence is noted.
		if(RobotContainer.constants.getClimberConstants().getRightLimitID() != -1) {
			rightLimit = new DigitalInput(RobotContainer.constants.getClimberConstants().getRightLimitID());
			SmartDashboard.putData(rightLimit);
			hasRightLimit = true;
		} else {
			hasRightLimit = false;
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

	/*** Getter method for the left limit switch */
	public boolean getLeftLimit() {
		if(hasLeftLimit) {
			// Returns the left limit switch as inverted if it is. Otherwise, the method returns it as normal. If left limit switch is not present, the method returns false.
			if(RobotContainer.constants.getClimberConstants().invertLeftLimitSensor()) {
				return !leftLimit.get(); // Inverted
			}
			return leftLimit.get(); // Not inverted
		}
		return false; // No left limit switch present
	}

	/*** Getter method for the right limit switch */
	public boolean getRightLimit() {
		if(hasRightLimit) {
			// Returns the right limit switch as inverted if it is. Otherwise, the method returns it as normal. If right limit switch is not present, the method returns false.
			if(RobotContainer.constants.getClimberConstants().invertRightLimitSensor()) {
				return !rightLimit.get(); // Inverted
			}
			return rightLimit.get(); // Not inverted
		}
		return false; // No right limit switch present
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}