// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class ShooterSubsystem extends SubsystemBase {
  // Declares shooter motor objects, but does not define them yet.
  TalonFX shooterMotor1;
  TalonFX shooterMotor2;
  public ShooterSubsystem() {
    // Creates 2 objects, one object per each shooter motor.
    shooterMotor1 = new TalonFX(RobotContainer.constants.getShooterConstants().getShooterMotor1ID());
    shooterMotor2 = new TalonFX(RobotContainer.constants.getShooterConstants().getShooterMotor2ID());

    shooterMotor2.set(TalonFXControlMode.Follower, RobotContainer.constants.getShooterConstants().getShooterMotor1ID());
  }
  /**
   * A value between -1 and 1.
   * 
   * @param speed
   */
  public void setShooterPercentOutput(double speed) {
    shooterMotor2.set(TalonFXControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}