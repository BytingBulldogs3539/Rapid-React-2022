// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Pneumatics extends SubsystemBase {
  final double minPressure = 80;
  final double maxPressure = 120;

  // Initializes compressor & solenoid objects
  Compressor compressor;
  DoubleSolenoid intakeSolenoid;
  DoubleSolenoid staticClimberSolenoid;
  DoubleSolenoid climberSolenoid;

  /** Creates a new Pneumatics. */
  public Pneumatics() {
    // Creates a compressor object
    compressor = new Compressor(RobotContainer.constants.getPneumaticsConstants().getCompressorID(), PneumaticsModuleType.REVPH);
    compressor.enableAnalog(minPressure, maxPressure);
    compressor.disable();

    // Creates 3 solenoid objects
    intakeSolenoid = new DoubleSolenoid(RobotContainer.constants.getPneumaticsConstants().getCompressorID(), PneumaticsModuleType.REVPH, RobotContainer.constants.getPneumaticsConstants().getIntakeSolenoidForward(), RobotContainer.constants.getPneumaticsConstants().getIntakeSolenoidReverse());
    staticClimberSolenoid = new DoubleSolenoid(RobotContainer.constants.getPneumaticsConstants().getCompressorID(), PneumaticsModuleType.REVPH, RobotContainer.constants.getPneumaticsConstants().getStaticClimberSolenoidForward(), RobotContainer.constants.getPneumaticsConstants().getStaticClimberSolenoidReverse());
    climberSolenoid = new DoubleSolenoid(RobotContainer.constants.getPneumaticsConstants().getCompressorID(), PneumaticsModuleType.REVPH, RobotContainer.constants.getPneumaticsConstants().getClimberSolenoidForward(), RobotContainer.constants.getPneumaticsConstants().getCLimberSolenoidReverse());

  }
  public void setIntakeOut()
  {
    intakeSolenoid.set(Value.kForward);
  }
  public void setIntakeIn()
  {
    intakeSolenoid.set(Value.kReverse);
  }
  public void grabClimbBar()
  {
    staticClimberSolenoid.set(Value.kForward);
  }
  public void releaseClimbBar()
  {
    staticClimberSolenoid.set(Value.kReverse);
  }
  public void moveClimberForward()
  {
    climberSolenoid.set(Value.kForward);
  }
  public void moveClimberBackwards()
  {
    climberSolenoid.set(Value.kReverse);
  }


  public void enableCompressor() {
    compressor.enableAnalog(minPressure, maxPressure);
  }

  public void disableCompressor() {
    compressor.disable();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
