// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.math.controller.ControlAffinePlantInversionFeedforward;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.utilities.GearRatio;
import frc.robot.utilities.PIDConstants;

public class ShooterSubsystem extends SubsystemBase {
	// Declares shooter motor objects, but does not define them yet.
	TalonFX SM1;
	TalonFX SM2;
	TalonFX SM3;
	TalonFX KM;

	// Creates final boolean variables for each object.
	final boolean hasSM1; // Shooter motor 1
	final boolean hasSM2; // Shooter motor 2
	final boolean hasSM3; // Shooter motor 3
	final boolean hasKM; // Kicker motor

	final Double VELCONV = (2048.0 / 600.0);

	// Creates a new object for the sensor
	DigitalInput sensor;
	final boolean hasSensor;
	ColorSensorV3 colorSensor;


	public ShooterSubsystem() {
		PIDConstants pidConstants = RobotContainer.constants.getShooterConstants().getPIDConstants();
		int SM1ID = RobotContainer.constants.getShooterConstants().getSM1ID();
		GearRatio SM1GearRatio = RobotContainer.constants.getShooterConstants().getSM1GearRatio();
		if (RobotContainer.constants.getShooterConstants().getSM1ID() != -1) {
			hasSM1 = true;
			SM1 = configureMotor(SM1ID, SM1GearRatio, pidConstants);
			SM1.setNeutralMode(NeutralMode.Coast);
		} else {
			hasSM1 = false;
		}

		int SM2ID = RobotContainer.constants.getShooterConstants().getSM2ID();
		GearRatio SM2GearRatio = RobotContainer.constants.getShooterConstants().getSM2GearRatio();
		if (RobotContainer.constants.getShooterConstants().getSM2ID() != -1) {
			hasSM2 = true;
			SM2 = configureMotor(SM2ID, SM2GearRatio, pidConstants);
			SM2.setNeutralMode(NeutralMode.Coast);
		} else {
			hasSM2 = false;
		}

		int SM3ID = RobotContainer.constants.getShooterConstants().getSM3ID();
		GearRatio SM3GearRatio = RobotContainer.constants.getShooterConstants().getSM3GearRatio();
		if (RobotContainer.constants.getShooterConstants().getSM3ID() != -1) {
			hasSM3 = true;
			SM3 = configureMotor(SM3ID, SM3GearRatio, pidConstants);
			SM3.setNeutralMode(NeutralMode.Coast);
		} else {
			hasSM3 = false;

		}

		int KMID = RobotContainer.constants.getShooterConstants().getKMID();
		GearRatio KMGearRatio = RobotContainer.constants.getShooterConstants().getKMGearRatio();
		if (RobotContainer.constants.getShooterConstants().getKMID() != -1) {
			hasKM = true;
			KM = configureMotor(KMID, KMGearRatio, pidConstants);
			KM.setNeutralMode(NeutralMode.Brake);
		} else {
			hasKM = false;
		}

		if (RobotContainer.constants.getShooterConstants().getDigitalInput() != -1) {
			sensor = new DigitalInput(RobotContainer.constants.getShooterConstants().getDigitalInput());
			hasSensor = true;
		} else {
			hasSensor = false;
		}

		if(RobotContainer.constants.getShooterConstants().getColorSensor()) {
			colorSensor = new ColorSensorV3(Port.kOnboard);
		}
	}

	public TalonFX configureMotor(int motorID, GearRatio gearRatio, PIDConstants pidConstants) {
		TalonFX motor = new TalonFX(motorID);
		motor.setInverted(gearRatio.getInverted());
		motor.setSensorPhase(RobotContainer.constants.getShooterConstants().getSM1GearRatio().getInverted());
		motor.configSupplyCurrentLimit(
				new SupplyCurrentLimitConfiguration(true, gearRatio.getCurrentLimit(), gearRatio.getCurrentLimit(), 0));
		motor.config_kP(0, pidConstants.getP());
		motor.config_kI(0, pidConstants.getI());
		motor.config_kD(0, pidConstants.getD());
		motor.config_kF(0, pidConstants.getF());
		motor.configMaxIntegralAccumulator(0, 0);

		return motor;
	}

	/**
	 * @return returns true when the sensor in the shooter is blocked. Also returns
	 *         true if the robot does not have a sensor.
	 */
	public boolean getSensor() {
		if (hasSensor) {
			if (RobotContainer.constants.getShooterConstants().invertSensor()) {
				return !sensor.get();
			}
			return sensor.get();
		}
		return true;
	}

	/*** @return True if the robot has a ball (regardless of color), false if it does not have one. */
	public boolean hasBall() {
		int redTolerance = RobotContainer.constants.getShooterConstants().getRedTolerance();
		int redR = RobotContainer.constants.getShooterConstants().getRedR();
		int redG = RobotContainer.constants.getShooterConstants().getRedG();
		int redB = RobotContainer.constants.getShooterConstants().getRedB();

		int blueTolerance = RobotContainer.constants.getShooterConstants().getBlueTolerance();
		int blueR = RobotContainer.constants.getShooterConstants().getBlueR();
		int blueG = RobotContainer.constants.getShooterConstants().getBlueG();
		int blueB = RobotContainer.constants.getShooterConstants().getBlueB();


		if(redR + redTolerance > colorSensor.getRed() && redR - redTolerance < colorSensor.getRed()) {
			if(redG + redTolerance > colorSensor.getGreen() && redG - redTolerance < colorSensor.getGreen()) {
				if(redB + redTolerance > colorSensor.getBlue() && redB - redTolerance < colorSensor.getBlue()) {
					return true;
				}
			}

			if(blueR + blueTolerance > colorSensor.getRed() && blueR - blueTolerance < colorSensor.getRed()) {
				if(blueG + blueTolerance > colorSensor.getGreen() && blueG - blueTolerance < colorSensor.getGreen()) {
					if(blueB + blueTolerance > colorSensor.getBlue() && blueB - blueTolerance < colorSensor.getBlue()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/*** @return True if the ball in the robot is red, false if not.*/
	public boolean hasRedBall() {
		int redTolerance = RobotContainer.constants.getShooterConstants().getRedTolerance();
		int redR = RobotContainer.constants.getShooterConstants().getRedR();
		int redG = RobotContainer.constants.getShooterConstants().getRedG();
		int redB = RobotContainer.constants.getShooterConstants().getRedB();

		if(redR + redTolerance > colorSensor.getRed() && redR - redTolerance < colorSensor.getRed()) {
			if(redG + redTolerance > colorSensor.getGreen() && redG - redTolerance < colorSensor.getGreen()) {
				if(redB + redTolerance > colorSensor.getBlue() && redB - redTolerance < colorSensor.getBlue()) {
					return true;
				}
			}
		}
		return false;
	}

	/***
	 * Sets the percent output of all of the shooter motors
	 * 
	 * @param SM1Speed (Percent output to set the first shooter motor to)
	 * @param SM2Speed (Percent output to set the second shooter motor to)
	 * @param SM3Speed (Percent output to set the third shooter motor to)
	 * @param KMSpeed  (Percent output to set the kicker motor to)
	 */
	public void setShooterPercentOutput(double SM1Speed, double SM2Speed, double SM3Speed, double KMSpeed) {
		if (hasSM1)
			SM1.set(TalonFXControlMode.PercentOutput, SM1Speed*RobotContainer.constants.getShooterConstants().getSM1GearRatio().getGearRatio());

		if (hasSM2)
			SM2.set(TalonFXControlMode.PercentOutput, SM2Speed);

		if (hasSM3)
			SM3.set(TalonFXControlMode.PercentOutput, SM3Speed);

		if (hasKM)
			KM.set(TalonFXControlMode.PercentOutput, KMSpeed*RobotContainer.constants.getShooterConstants().getKMGearRatio().getGearRatio());
	}

	/***
	 * Sets the percent output of the first shooter motor.
	 * 
	 * @param SM1Speed (Percent output to set the first shooter motor to)
	 */
	public void setSM1PercentOutput(double SM1Speed) {
		if (hasSM1)
			SM1.set(TalonFXControlMode.PercentOutput, SM1Speed*RobotContainer.constants.getShooterConstants().getSM1GearRatio().getGearRatio());
	}

	/***
	 * Sets the percent output of the second shooter motor.
	 * 
	 * @param SM2Speed (Percent output to set the second shooter motor to)
	 */
	public void setSM2PercentOutput(double SM2Speed) {
		if (hasSM2)
			SM2.set(TalonFXControlMode.PercentOutput, SM2Speed);
	}

	/***
	 * Sets the percent output of the third shooter motor.
	 * 
	 * @param SM3Speed (Percent output to set the third shooter motor to)
	 */
	public void setSM3PercentOutput(double SM3Speed) {
		if (hasSM3)
			SM3.set(TalonFXControlMode.PercentOutput, SM3Speed);
	}

	/***
	 * Sets the percent output of the first shooter motor.
	 * 
	 * @param KMSpeed (Percent output to set the kicker motor to)
	 */
	public void setKMPercentOutput(double KMSpeed) {
		if (hasKM)
			KM.set(TalonFXControlMode.PercentOutput, KMSpeed*RobotContainer.constants.getShooterConstants().getKMGearRatio().getGearRatio());
	}

	/**
	 * Sets the speed of every shooter motor.
	 * 
	 * @param speed (Value to set the speed of every shooter motor to)
	 */
	public void setSMSpeeds(double speed) {
		if (hasSM1)
			SM1.set(TalonFXControlMode.Velocity,
					speed * VELCONV * RobotContainer.constants.getShooterConstants().getSM1GearRatio().getGearRatio());
		if (hasSM2)
			SM2.set(TalonFXControlMode.Velocity,
					speed * VELCONV * RobotContainer.constants.getShooterConstants().getSM2GearRatio().getGearRatio());
		if (hasSM3)
			SM3.set(TalonFXControlMode.Velocity,
					speed * VELCONV * RobotContainer.constants.getShooterConstants().getSM3GearRatio().getGearRatio());
		if (hasKM)
			KM.set(TalonFXControlMode.Velocity,
					speed * VELCONV * RobotContainer.constants.getShooterConstants().getKMGearRatio().getGearRatio());
	}

	/**
	 * Sets the speed of the first shooter motor.
	 * 
	 * @param speed (Value to set the speed of the first shooter motor to)
	 */
	public void setSM1Speed(double speed) {
		if (hasSM1)
			SM1.set(TalonFXControlMode.Velocity,
					speed * VELCONV * RobotContainer.constants.getShooterConstants().getSM1GearRatio().getGearRatio());
	}

	/**
	 * Sets the speed of the second shooter motor.
	 * 
	 * @param speed (Value to set the speed of the second shooter motor to)
	 */
	public void setSM2Speed(double speed) {
		if (hasSM2)
			SM2.set(TalonFXControlMode.Velocity,
					speed * VELCONV * RobotContainer.constants.getShooterConstants().getSM2GearRatio().getGearRatio());
	}

	/**
	 * Sets the speed of the third shooter motor.
	 * 
	 * @param speed (Value to set the speed of the third shooter motor to)
	 */
	public void setSM3Speed(double speed) {
		if (hasSM3)
			SM3.set(TalonFXControlMode.Velocity,
					speed * VELCONV * RobotContainer.constants.getShooterConstants().getSM3GearRatio().getGearRatio());
	}

	/**
	 * Sets the speed of the kicker motor.
	 * 
	 * @param speed (Value to set the speed of the kicker motor to)
	 */
	public void setKMSpeed(double speed) {
		if (hasKM)
			KM.set(TalonFXControlMode.Velocity,
					speed * VELCONV * RobotContainer.constants.getShooterConstants().getKMGearRatio().getGearRatio());
	}

	/***
	 * Configures PID constants for the given shooter motor
	 * 
	 * @param motor        (motor whose PID constants will be set)
	 * @param PIDConstants (PID constants to set the shooter motor's PID constants
	 *                     to)
	 */
	public void setPIDConstants(TalonFX motor, PIDConstants PIDConstants) {
		if (motor != null) {
			motor.config_kP(0, PIDConstants.getP());
			motor.config_kI(0, PIDConstants.getI());
			motor.config_kD(0, PIDConstants.getD());
			motor.config_kF(0, PIDConstants.getF());
		}
	}

	public boolean SM1AtTarget(double tolerance) {
		return SM1.getClosedLoopError() < tolerance;
	}

	public boolean SM2AtTarget(double tolerance) {
		return SM2.getClosedLoopError() < tolerance;
	}

	public boolean SM3AtTarget(double tolerance) {
		return SM3.getClosedLoopError() < tolerance;
	}

	public boolean KM1AtTarget(double tolerance) {
		return KM.getClosedLoopError() < tolerance;
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		// If there is a color sensor, display its RGB values to the SmartDashboard.
		if(colorSensor != null) {
			SmartDashboard.putNumber("Get Red", colorSensor.getRed());
			SmartDashboard.putNumber("Get Green", colorSensor.getGreen());
			SmartDashboard.putNumber("Get Blue", colorSensor.getBlue());
		}
	}

	public void stop() {
		if (hasSM1)
			SM1.set(TalonFXControlMode.PercentOutput, 0);

		if (hasSM2)
			SM2.set(TalonFXControlMode.PercentOutput, 0);

		if (hasSM3)
			SM3.set(TalonFXControlMode.PercentOutput, 0);

		if (hasKM)
			KM.set(TalonFXControlMode.PercentOutput, 0);
	}
}