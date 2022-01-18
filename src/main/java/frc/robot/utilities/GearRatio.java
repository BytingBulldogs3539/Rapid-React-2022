// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utilities;

// This class creates the gear ratio and the inverted constants for the robot.
public class GearRatio {
    private final double gearRatio;
    private final boolean inverted;

    public GearRatio(double gearRatio, boolean inverted) {
        this.gearRatio = gearRatio;
        this.inverted = inverted;
    }

    // Getter methods
    /**
     * Gets value of gear ratio constant
     * @return Value of gear ratio constant
     */
    public double getGearRatio() {
        return gearRatio;
    }

    /***
     * Gets value of inverted constant
     * @return Value of inverted constant
     */
    public boolean getInverted() {
        return inverted;
    }

}
