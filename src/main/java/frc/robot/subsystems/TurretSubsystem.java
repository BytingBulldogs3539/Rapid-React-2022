// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
// Imports
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.RobotContainer;

/** Turret class */
public class TurretSubsystem {
    // Turret motor instance variable
    private TalonFX turretMotor;

    public TurretSubsystem() {
        turretMotor = new TalonFX(16);
        // RobotContainer.constants.getIntakeConstants().getIntakeMotorID();
    }

    /**
	 * @param speed a value between -1 & 1
	 */
	public void setTurretSpeed(double speed) {
		turretMotor.set(TalonFXControlMode.PercentOutput, speed);
	}

    // TO DO: Make a method that puts the turret into break mode. Code to model off of can be found in the climber subsystem.
}
