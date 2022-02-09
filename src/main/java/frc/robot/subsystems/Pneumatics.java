// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Pneumatics extends SubsystemBase {
  final double minPressure = 80;
  final double maxPressure = 120;

  // Initializes compressor & solenoid objects
  Compressor compressor;
  DoubleSolenoid intakeSolenoid;

  /** Creates a new Pneumatics. */
  public Pneumatics() {
    // Creates a compressor object
    compressor = new Compressor(RobotContainer.constants.getPneumaticsConstants().getCompressorID(), PneumaticsModuleType.REVPH);
    compressor.enableAnalog(minPressure, maxPressure);
    compressor.disable();

    // Creates 4 solenoid objects
    intakeSolenoid = new DoubleSolenoid(RobotContainer.constants.getPneumaticsConstants().getIntakeSolenoid(), PneumaticsModuleType.REVPH, RobotContainer.constants.getPneumaticsConstants().getIntakeSolenoidForward(), RobotContainer.constants.getPneumaticsConstants().getIntakeSolenoidReverse());
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
