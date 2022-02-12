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

  final boolean hasLClimber;
  final boolean hasRClimber;

  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {
    if (RobotContainer.constants.getClimberConstants().getLClimberMotorID() != -1) {
      lClimber = new TalonFX(RobotContainer.constants.getClimberConstants().getLClimberMotorID());
      hasLClimber = true;
    } else {
      hasLClimber = false;
    }

    if (RobotContainer.constants.getClimberConstants().getRClimberMotorID() != -1) {
      rClimber = new TalonFX(RobotContainer.constants.getClimberConstants().getRClimberMotorID());
      hasRClimber = true;
    } else {
      hasRClimber = false;
    }

    this.setDefaultCommand(new MoveClimberCommand(this));
  }

  /*** Sets the speed of both the left and right climbers. */
  public void setMotorSpeed(double lClimberSpeed, double rClimberSpeed) {
    if (hasLClimber)
      lClimber.set(ControlMode.PercentOutput, lClimberSpeed);

    if (hasRClimber)
      rClimber.set(ControlMode.PercentOutput, rClimberSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}