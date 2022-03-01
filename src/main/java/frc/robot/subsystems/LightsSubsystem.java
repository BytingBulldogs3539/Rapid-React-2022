// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.*;
import com.ctre.phoenix.led.CANdle.LEDStripType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class LightsSubsystem extends SubsystemBase {
  // This creates a new CANdle object, configures it, and sets its lights to be green.
  private final CANdle candle = new CANdle(RobotContainer.constants.getLightsConstants().getCandleID(), "rio");
  private final int ledCount = RobotContainer.constants.getLightsConstants().getNumOfLights(); // Variable for total # of lights
  
  /** Creates a new LightsSubsystem. */
  public LightsSubsystem() {
    CANdleConfiguration config = new CANdleConfiguration();
    config.stripType = LEDStripType.RGB; // Sets the strip type to RGB, allowing the candle to control LED lights with RGB
    config.brightnessScalar = 0.5; // Dims the LEDs to half brightness
    candle.configAllSettings(config);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    candle.setLEDs(0, 255, 0); // Sets the LEDs of the candle to be green.
  }
}