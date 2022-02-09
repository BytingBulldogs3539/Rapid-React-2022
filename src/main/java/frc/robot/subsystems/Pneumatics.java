// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Pneumatics extends SubsystemBase {
  Compressor compressor;

  /** Creates a new Pneumatics. */
  public Pneumatics() {
    // Creates a compressor object
    compressor = new Compressor(RobotContainer.constants.getPneumaticsConstants().getCompressorID(), PneumaticsModuleType.REVPH);
    compressor.enableAnalog(80, 120);

    // Creates 4 solenoid objects

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
