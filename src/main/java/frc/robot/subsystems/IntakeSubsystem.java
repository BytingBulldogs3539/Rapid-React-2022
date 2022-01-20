// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase {

  TalonFX intakeMotor1;
  final boolean shouldRun;
  public IntakeSubsystem() {
    if(RobotContainer.constants.getIntakeConstants().getIntakeMotorID()!=-1){
      shouldRun = true;
      intakeMotor1 = new TalonFX(RobotContainer.constants.getIntakeConstants().getIntakeMotorID());
    }
    else
    {
      shouldRun = false;
    }

  }
  /**
   * 
   * @param speed a value between -1 & 1
   */
  public void setIntakeSpeed(double speed) {
    if(!shouldRun){
      return;
    }
    intakeMotor1.set(TalonFXControlMode.PercentOutput, speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
