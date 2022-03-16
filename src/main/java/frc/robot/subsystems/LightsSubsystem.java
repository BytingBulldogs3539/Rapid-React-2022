// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.*;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import frc.robot.subsystems.ShooterSubsystem.Color;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LightsSubsystem extends SubsystemBase {

  private final CANdle candle; // This creates a new CANdle object
  private final int ledCount = RobotContainer.constants.getLightsConstants().getNumOfLights(); //Number of LEDs
  private final int cutoff = 19; //Defines the cutoff of top and bottom. Offset by 8 for the CANDle
  private final int w = 0; //Used for clarity. Constant is declared in this file since it will always be 0 for RGB LEDs
  private Color ball1; //Holders for color positions, gets passed into the light strips
  private Color ball2; //^
  private Color intakeColor; //The value of the color sensor
  private Boolean lightBeam; //The light beam status
  private Boolean canPush = true; //Determines if the first ball can be set

  private Color top; //Holder values for the top and bottom colors
  private Color bottom;

  private Boolean frame = false;
  private int frameCounter = 0;

  /** Creates a new LightsSubsystem. */
  public LightsSubsystem() {
    candle = new CANdle(RobotContainer.constants.getLightsConstants().getCandleID(), "rio");
    CANdleConfiguration config = new CANdleConfiguration();
    config.stripType = LEDStripType.RGB; // Sets the strip type to RGB, allowing the candle to control LED lights with RGB
    config.brightnessScalar = 0.5; // Dims the LEDs to half brightness
    candle.configAllSettings(config);
    candle.setLEDs(0, 0, 0, w, 0, 8); //CANdle LEDs
    updateLightTop(Color.NONE);
    updateLightBottom(Color.NONE);
  }

  //Sets top LEDs
  private void updateLightTop(Color hue){
    if(top != hue && !frame){     
      top = hue;
      //SmartDashboard.putString("Top Lights", top.toString());
      if(hue == Color.RED){
        candle.setLEDs(255, 0, 0, w, cutoff, ledCount);
      }else if(hue == Color.BLUE){
        candle.setLEDs(0, 0, 255, w, cutoff, ledCount);
      }else if(hue == Color.UNKNOWN){
        candle.setLEDs(255, 0, 255, w, cutoff, ledCount);
      }else{
        candle.setLEDs(110, 255, 20, w, cutoff, ledCount);
      }
    }
  }
  //Sets bottom LEDs
  private void updateLightBottom(Color huex){
    if(bottom != huex && frame){
      bottom = huex;
      //SmartDashboard.putString("Bottom Lights", bottom.toString());
      if(huex == Color.RED){
        candle.setLEDs(255, 0, 0, w, 8, cutoff-8);
      }else if(huex == Color.BLUE){
        candle.setLEDs(0, 0, 255, w, 8, cutoff-8);
      }else if(huex == Color.UNKNOWN){
        candle.setLEDs(255, 0, 255, w, 8, cutoff-8);
      }else{
        candle.setLEDs(110, 255, 20, w, 8, cutoff-8); 
      }
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    //Turns the shooter's boolean returns into a system that will keep track of the balls
    intakeColor = RobotContainer.shooterSubsystem.getColorSensorColor();
    lightBeam = RobotContainer.shooterSubsystem.getSensor(); //True if blocked or not found
    if (intakeColor != Color.NONE && canPush) {
      canPush = false;
      ball1 = intakeColor;
    } else if (intakeColor == Color.NONE) {
      canPush = true;
      if (ball1 != Color.NONE && ball2 == Color.NONE && lightBeam) {
        ball2 = ball1;
        ball1 = Color.NONE;
      }
    }

    if(lightBeam && ball2 == Color.NONE){
      ball2 = Color.UNKNOWN;
    }

    //If the lightbeam goes off, remove ball 2.
    if (ball2 != Color.NONE && !lightBeam) {
      ball2 = Color.NONE;
    }

    // If no ball is detected in the bottom for 2s, clear ball1:
    if (intakeColor == Color.NONE && frameCounter < 200) {
      frameCounter++;
    } else if (intakeColor != Color.NONE) {
      frameCounter = 0;
    }
    if (frameCounter >= 100) {
      ball1 = Color.NONE;
    }
    
    frame = !frame;
    updateLightTop(ball2);
    updateLightBottom(ball1);
  }
}