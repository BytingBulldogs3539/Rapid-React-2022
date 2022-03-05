// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.*;
import com.ctre.phoenix.led.CANdle.LEDStripType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class LightsSubsystem extends SubsystemBase {
  // This creates a new CANdle object.
  private final CANdle candle;

  private final int ledCount; // Variable for total # of lights

  /** Creates a new LightsSubsystem. */
  public LightsSubsystem() {
    candle = new CANdle(RobotContainer.constants.getLightsConstants().getCandleID(), "rio");
    ledCount = RobotContainer.constants.getLightsConstants().getNumOfLights();

    CANdleConfiguration config = new CANdleConfiguration();
    config.stripType = LEDStripType.RGB; // Sets the strip type to RGB, allowing the candle to control LED lights with
                                         // RGB
    config.brightnessScalar = 0.5; // Dims the LEDs to half brightness
    candle.configAllSettings(config);
  }

  /**
   * Sets a block of LEDs to the specified color
   * 
   * @param r        The amount of Red to set, range is [0, 255]
   * @param g        The amount of Green to set, range is [0, 255]
   * @param b        The amount of Blue to set, range is [0, 255]
   * @param startIdx Where to start setting the LEDs
   * @param count    The number of LEDs to apply this to
   */
  public void setLEDColor(int r, int g, int b, int startIndex, int count) {
    candle.setLEDs(r, g, b, 0, startIndex, count);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Sets the LEDs of the candle to be green.

    setLEDColor(255, 0, 0, 0, 10);
    setLEDColor(0, 0, 255, 11, 30);
  }
}