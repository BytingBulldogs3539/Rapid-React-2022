// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.MoveClimberCommand;

public class ClimberSubsystem extends SubsystemBase {
  private TalonFX lClimber;
  private TalonFX rClimber;
  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    lClimber = new TalonFX(RobotContainer.constants.getClimberConstants().getClimberMotor1ID());
    rClimber = new TalonFX(RobotContainer.constants.getClimberConstants().getClimberMotor2ID());
    this.setDefaultCommand(new MoveClimberCommand(this));
  }

  /*** Sets the speed of both the left and right climbers. */
  public void setMotorSpeed(double lClimberSpeed, double rClimberSpeed) {
    lClimber.set(ControlMode.PercentOutput, lClimberSpeed);
    rClimber.set(ControlMode.PercentOutput, rClimberSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}