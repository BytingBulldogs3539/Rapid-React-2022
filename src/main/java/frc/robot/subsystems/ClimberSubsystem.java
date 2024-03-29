// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.MoveClimberCommand;
import frc.robot.utilities.GearRatio;

public class ClimberSubsystem extends SubsystemBase {
	private TalonFX lClimber;
	private TalonFX rClimber;

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

		// Checks for a left limit switch. If it is on the robot (if there is an ID for
		// it), then an object for it is created here. Otherwise, it is not created and
		// its lack of presence is noted.
		if (RobotContainer.constants.getClimberConstants().getLeftLimitID() != -1) {
			leftLimit = new DigitalInput(RobotContainer.constants.getClimberConstants().getLeftLimitID());
			SmartDashboard.putData(leftLimit);
			hasLeftLimit = true;
		} else {
			hasLeftLimit = false;
		}

		// Checks for a right limit switch. If it is on the robot (if there is an ID for
		// it), then an object for it is created here. Otherwise, it is not created and
		// its lack of presence is noted.
		if (RobotContainer.constants.getClimberConstants().getRightLimitID() != -1) {
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

		lClimber.setSensorPhase(RobotContainer.constants.getClimberConstants().getLClimberMotorInversion());
		rClimber.setSensorPhase(RobotContainer.constants.getClimberConstants().getRClimberMotorInversion());

		this.setDefaultCommand(new MoveClimberCommand(this));
	}

	public TalonFX configMotor(int id, GearRatio gearRatio) {
		TalonFX motor = new TalonFX(id);
		motor.setInverted(gearRatio.getInverted());
		motor.setNeutralMode(NeutralMode.Brake);
		motor.setSelectedSensorPosition(0);
		motor.configClearPositionOnLimitR(true, 0);
		motor.configSupplyCurrentLimit(
				new SupplyCurrentLimitConfiguration(true, gearRatio.getCurrentLimit(), gearRatio.getCurrentLimit(), 0));
		return motor;
	}


	public double getClimberPosition() {
		return (lClimber.getSelectedSensorPosition() + rClimber.getSelectedSensorPosition()) / 2.0;
	}

	/*** Sets the speed of both the left and right climbers. */
	public void setMotorSpeed(double lClimberSpeed, double rClimberSpeed) {
		if (hasLClimber)
			lClimber.set(ControlMode.PercentOutput, lClimberSpeed
					* RobotContainer.constants.getClimberConstants().getLClimberGearRatio().getGearRatio());

		if (hasRClimber)
			rClimber.set(ControlMode.PercentOutput, rClimberSpeed
					* RobotContainer.constants.getClimberConstants().getRClimberGearRatio().getGearRatio());
	}

	public boolean armsAboveFrameHeight() {
		double leftPos = lClimber.getSelectedSensorPosition();
		double rightPos = rClimber.getSelectedSensorPosition();
		if (leftPos > RobotContainer.constants.getClimberConstants().getLFrameHeight()
				|| rightPos > RobotContainer.constants.getClimberConstants().getRFrameHeight()) {
			return true;
		}
		return false;
	}

	/*** Sets the speed of both the left and right climbers to the same thing. */
	public void setMotorSpeed(double climberSpeed, boolean override) {
		double lMotorSpeed = climberSpeed;
		double rMotorSpeed = climberSpeed;
		double leftPos = lClimber.getSelectedSensorPosition();
		double rightPos = rClimber.getSelectedSensorPosition();
		// The following code checks to see if one climber is farther along in the
		// process of climbing up or down than one another. In doing so, it looks to see
		// which direction the climber arms are going and will check to see which arm it
		// needs to stop from there if any arm needs to be stopped at all (must be
		// stopped if it is 500 or higher units along).
		// Can be overidden by hitting up in the d-pad of the operator controller.
		if (!override) {
			// If the position of a particular climber arm is less than the soft bottom and
			// the climber speed is negative, stop that particular climber.
			// This effectively sets a soft limit.
			if (leftPos < RobotContainer.constants.getClimberConstants().getLClimberSoftBottom() && climberSpeed < 0) {
				lMotorSpeed = 0.0;
			}
			if (rightPos < RobotContainer.constants.getClimberConstants().getRClimberSoftBottom() && climberSpeed < 0) {
				rMotorSpeed = 0.0;
			}

			// Apply height limits if the climber direction is up "positive"
			if (climberSpeed > 0) {
				// If the climber arms are not out, only allow the arms to extend to the robots
				// max height.
				if (!RobotContainer.pneumaticsSubsystem.areArmsOut) {
					if (leftPos > RobotContainer.constants.getClimberConstants().getLFrameHeight()) {
						lMotorSpeed = 0.0;
					}
					if (rightPos > RobotContainer.constants.getClimberConstants().getRFrameHeight()) {
						rMotorSpeed = 0.0;
					}
				}
				// If the climber arms are out, allow the arms to be extended to their full
				// length.
				else {
					if (leftPos > RobotContainer.constants.getClimberConstants().getLMaxExtensionHeight()) {
						lMotorSpeed = 0.0;
					}
					if (rightPos > RobotContainer.constants.getClimberConstants().getRMaxExtensionHeight()) {
						rMotorSpeed = 0.0;
					}
				}
			}

			// If climber speed is positive, check to see if one arm is lower than the other
			// by 500 or more and then adjust it.
			if (climberSpeed > 0) {
				if (lClimber.getSelectedSensorPosition() - 500 > rClimber.getSelectedSensorPosition()) {
					lMotorSpeed *= 0.75;
				} else if (rClimber.getSelectedSensorPosition() - 500 > lClimber.getSelectedSensorPosition()) {
					rMotorSpeed *= 0.75;
				}
				// Otherwise, check to see if climber speed is negative. If so, check to see if
				// one arm is higher than the other by 500 or more and then adjust it.
			} else if (climberSpeed < 0) {
				if (lClimber.getSelectedSensorPosition() + 500 < rClimber.getSelectedSensorPosition()) {
					lMotorSpeed *= 0.75;
				} else if (rClimber.getSelectedSensorPosition() + 500 < lClimber.getSelectedSensorPosition()) {
					rMotorSpeed *= 0.75;
				}
			}
		}

		if(lMotorSpeed<0 && getLeftLimit() && lClimber.getSelectedSensorPosition()<RobotContainer.constants.getClimberConstants().getLMaxExtensionHeight()*.15)
		{
			rMotorSpeed = 0;//Inverted on purpose
		}
		if(rMotorSpeed<0 && getRightLimit() && rClimber.getSelectedSensorPosition()<RobotContainer.constants.getClimberConstants().getRMaxExtensionHeight()*.15)
		{
			lMotorSpeed = 0;// inverted on purpose
		}

		setMotorSpeed(lMotorSpeed, rMotorSpeed);
	}

	/*** Getter method for the left limit switch */
	public boolean getLeftLimit() {
		if (hasLeftLimit) {
			// Returns the left limit switch as inverted if it is. Otherwise, the method
			// returns it as normal. If left limit switch is not present, the method returns
			// false.
			if (RobotContainer.constants.getClimberConstants().invertLeftLimitSensor()) {
				return !leftLimit.get(); // Inverted
			}
			return leftLimit.get(); // Not inverted
		}
		return false; // No left limit switch present
	}

	/*** Getter method for the right limit switch */
	public boolean getRightLimit() {
		if (hasRightLimit) {
			// Returns the right limit switch as inverted if it is. Otherwise, the method
			// returns it as normal. If right limit switch is not present, the method
			// returns false.
			if (RobotContainer.constants.getClimberConstants().invertRightLimitSensor()) {
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