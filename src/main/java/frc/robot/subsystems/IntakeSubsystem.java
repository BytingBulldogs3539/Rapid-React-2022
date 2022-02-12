// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase {

  TalonFX intakeMotor1;
  TalonSRX knockDownMotor;
  final boolean hasIntakeMotor;
  final boolean hasKnockDownMotor;
  
  public IntakeSubsystem() {
    if(RobotContainer.constants.getIntakeConstants().getIntakeMotorID()!=-1){
      hasIntakeMotor = true;
      intakeMotor1 = new TalonFX(RobotContainer.constants.getIntakeConstants().getIntakeMotorID());
    }
    else
    {
      hasIntakeMotor = false;
    }

    if(RobotContainer.constants.getIntakeConstants().getKnockDownMotorID()!=-1){
      hasKnockDownMotor = true;
      intakeMotor1 = new TalonFX(RobotContainer.constants.getIntakeConstants().getIntakeMotorID());
    }
    else
    {
      hasKnockDownMotor = false;
    }

  }
  /**
   * 
   * @param speed a value between -1 & 1
   */
  public void setIntakeSpeed(double speed) {
    if(!hasIntakeMotor){
      return;
    }
    intakeMotor1.set(TalonFXControlMode.PercentOutput, speed);
  }

    /**
   * 
   * @param speed a value between -1 & 1
   */
  public void setKnockDownSpeed(double speed) {
    if(!hasKnockDownMotor){
      return;
    }
    knockDownMotor.set(ControlMode.PercentOutput, speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
