// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;

public class IntakeCommand extends CommandBase {
  
  IntakeSubsystem intakeSubsystem;
  PneumaticsSubsystem pneumatics;
  
  double speed = 0;
  
  /** Creates a new IntakeCommand. */
  public IntakeCommand(double speed, IntakeSubsystem intakeSubsystem, PneumaticsSubsystem pneumatics) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeSubsystem);

    this.speed = speed;
    this.pneumatics = pneumatics;

    this.intakeSubsystem = intakeSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    pneumatics.setIntakeOut();
    intakeSubsystem.setIntakeSpeed(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pneumatics.setIntakeIn();
    intakeSubsystem.setIntakeSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
  }
}
