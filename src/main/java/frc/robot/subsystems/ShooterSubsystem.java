// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.utilities.PIDConstants;

public class ShooterSubsystem extends SubsystemBase {
  // Declares shooter motor objects, but does not define them yet.
  TalonFX shooterMotor1;
  TalonFX shooterMotor2;
  final boolean shouldRun;
  public ShooterSubsystem() {
    if(RobotContainer.constants.getShooterConstants().getShooterMotor1ID()!=-1 ||
     RobotContainer.constants.getShooterConstants().getShooterMotor2ID()!=-1 ){
      

    // Creates 2 objects, one object per each shooter motor.
    shooterMotor1 = new TalonFX(RobotContainer.constants.getShooterConstants().getShooterMotor1ID());
    shooterMotor2 = new TalonFX(RobotContainer.constants.getShooterConstants().getShooterMotor2ID());

    shooterMotor2.set(TalonFXControlMode.Follower, RobotContainer.constants.getShooterConstants().getShooterMotor1ID());
    
    shooterMotor1.setInverted(RobotContainer.constants.getShooterConstants().getGearRatio().getInverted());
    shooterMotor1.setSensorPhase(RobotContainer.constants.getShooterConstants().getGearRatio().getInverted());
    shooterMotor2.setInverted(TalonFXInvertType.FollowMaster);
    shouldRun = true;
    } 
    else 
      shouldRun = false;

  }
  /**
   * A value between -1 and 1
   * 
   * @param speed
   */
  public void setShooterPercentOutput(double speed) {
    if(shouldRun)
      shooterMotor1.set(TalonFXControlMode.PercentOutput, speed);
  }

  public void setShooterSpeed(double speed) {
    if(shouldRun)
      shooterMotor1.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getGearRatio().getGearRatio());

  }

  /***
   * Configures PID constants for shooterMotor1
   * @param PIDConstants (PID constants for shooter motor 1)
   */
  public void setPIDConstants(PIDConstants PIDConstants) {
    if(shouldRun)
    {
      shooterMotor1.config_kP(0, PIDConstants.getP());
      shooterMotor1.config_kI(0, PIDConstants.getI());
      shooterMotor1.config_kD(0, PIDConstants.getD());
      shooterMotor1.config_kF(0, PIDConstants.getF());
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}