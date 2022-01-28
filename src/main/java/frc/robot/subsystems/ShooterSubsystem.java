// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

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
  final boolean hasKM;  // Kicker motor

  public ShooterSubsystem() {
    int SM1ID = RobotContainer.constants.getShooterConstants().getSM1ID();
    GearRatio SM1GearRatio = RobotContainer.constants.getShooterConstants().getSM1GearRatio();
    if(RobotContainer.constants.getShooterConstants().getSM1ID() !=-1) {
      hasSM1 = false;
    } else {
      hasSM1 = true;
      SM1 = configureMotor(SM1ID, SM1GearRatio);
    }

    int SM2ID = RobotContainer.constants.getShooterConstants().getSM2ID();
    GearRatio SM2GearRatio = RobotContainer.constants.getShooterConstants().getSM2GearRatio();
    if(RobotContainer.constants.getShooterConstants().getSM2ID() !=-1) {
      hasSM2 = false;
    } else {
      hasSM2 = true;
      SM2 = configureMotor(SM2ID, SM2GearRatio);
    }

    int SM3ID = RobotContainer.constants.getShooterConstants().getSM3ID();
    GearRatio SM3GearRatio = RobotContainer.constants.getShooterConstants().getSM3GearRatio();
    if(RobotContainer.constants.getShooterConstants().getSM3ID() != -1) {
      hasSM3 = false;
    } else {
      hasSM3 = true;
      SM2 = configureMotor(SM3ID, SM3GearRatio);

    }

    int KMID = RobotContainer.constants.getShooterConstants().getKMID();
    GearRatio KMGearRatio = RobotContainer.constants.getShooterConstants().getKMGearRatio();
    if(RobotContainer.constants.getShooterConstants().getKMID() != -1) {
      hasKM = false;
    } else {
      hasKM = true;
      SM2 = configureMotor(KMID, KMGearRatio);
    }
  }

  public TalonFX configureMotor(int motorID, GearRatio motorGearRatio) {
    TalonFX motor = new TalonFX(motorID);
    motor.setInverted(motorGearRatio.getInverted());
    motor.setSensorPhase(RobotContainer.constants.getShooterConstants().getSM1GearRatio().getInverted());
    return motor;
  }
  /***
   * Sets the percent output of all of the shooter motors
   * @param SM1Speed (Percent output to set the first shooter motor to)
   * @param SM2Speed (Percent output to set the second shooter motor to)
   * @param SM3Speed (Percent output to set the third shooter motor to)
   * @param KMSpeed (Percent output to set the kicker motor to)
   */
  public void setShooterPercentOutput(double SM1Speed, double SM2Speed, double SM3Speed, double KMSpeed) {
    if(hasSM1)
      SM1.set(TalonFXControlMode.PercentOutput, SM1Speed);

      if(hasSM2)
      SM2.set(TalonFXControlMode.PercentOutput, SM2Speed);

      if(hasSM3)
      SM3.set(TalonFXControlMode.PercentOutput, SM3Speed);

      if(hasKM)
      KM.set(TalonFXControlMode.PercentOutput, KMSpeed);
  }

  /***
   * Sets the percent output of the first shooter motor.
   * @param SM1Speed (Percent output to set the first shooter motor to)
   */
  public void setSM1PercentOutput(double SM1Speed) {
    if(hasSM1)
      SM1.set(TalonFXControlMode.PercentOutput, SM1Speed);
  }

  /***
   * Sets the percent output of the second shooter motor.
   * @param SM2Speed (Percent output to set the second shooter motor to)
   */
  public void setSM2PercentOutput(double SM2Speed) {
    if(hasSM2)
      SM2.set(TalonFXControlMode.PercentOutput, SM2Speed);
  }

  /***
   * Sets the percent output of the third shooter motor.
   * @param SM3Speed (Percent output to set the third shooter motor to)
   */
  public void setSM3PercentOutput(double SM3Speed) {
    if(hasSM3)
      SM3.set(TalonFXControlMode.PercentOutput, SM3Speed);
  }

  /***
   * Sets the percent output of the first shooter motor.
   * @param KMSpeed (Percent output to set the kicker motor to)
   */
  public void setKMPercentOutput(double KMSpeed) {
    if(hasKM)
      KM.set(TalonFXControlMode.PercentOutput, KMSpeed);
  }

  /**
   * Sets the speed of every shooter motor.
   * @param speed (Value to set the speed of every shooter motor to)
   */
  public void setSMSpeeds(double speed) {
    if(hasSM1)
      SM1.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getSM1GearRatio().getGearRatio());
    if(hasSM2)
      SM2.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getSM2GearRatio().getGearRatio());
    if(hasSM3)
      SM3.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getSM3GearRatio().getGearRatio());
    if(hasKM)
      KM.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getKMGearRatio().getGearRatio());
  }

  /**
   * Sets the speed of the first shooter motor.
   * @param speed (Value to set the speed of the first shooter motor to)
   */
  public void setSM1Speed(double speed) {
    if(hasSM1)
      SM1.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getSM1GearRatio().getGearRatio());
  }

  /**
   * Sets the speed of the second shooter motor.
   * @param speed (Value to set the speed of the second shooter motor to)
   */
  public void setSM2Speed(double speed) {
    if(hasSM2)
      SM2.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getSM2GearRatio().getGearRatio());
  }

  /**
   * Sets the speed of the third shooter motor.
   * @param speed (Value to set the speed of the third shooter motor to)
   */
  public void setSM3Speed(double speed) {
    if(hasSM3)
      SM3.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getSM3GearRatio().getGearRatio());
  }

  /**
   * Sets the speed of the kicker motor.
   * @param speed (Value to set the speed of the kicker motor to)
   */
  public void setKMSpeed(double speed) {
    if(hasKM)
      KM.set(TalonFXControlMode.Velocity, speed * (2048.0 / 600.0) * RobotContainer.constants.getShooterConstants().getKMGearRatio().getGearRatio());
  }

  /***
   * Configures PID constants for the given shooter motor
   * @param motor (motor whose PID constants will be set)
   * @param PIDConstants (PID constants to set the shooter motor's PID constants to)
   */
  public void setPIDConstants(TalonFX motor, PIDConstants PIDConstants) {
    if(motor != null)
    {
      motor.config_kP(0, PIDConstants.getP());
      motor.config_kI(0, PIDConstants.getI());
      motor.config_kD(0, PIDConstants.getD());
      motor.config_kF(0, PIDConstants.getF());
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}