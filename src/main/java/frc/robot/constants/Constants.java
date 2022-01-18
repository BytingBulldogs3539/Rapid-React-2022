// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import com.swervedrivespecialties.swervelib.ModuleConfiguration;

import frc.robot.utilities.GearRatio;
import frc.robot.utilities.PIDConstants;

/**
 * An outline for two classes containing constants for both the practice bot and the comp bot.
 */
public abstract class Constants {
    /**
     * @return the Drivetrain Constants
     * @see DriveConstants
    */
    public abstract DriveConstants getDriveConstants();
    /**
     * @return the Intake Constants
     * @see IntakeConstants
     */
    public abstract IntakeConstants getIntakeConstants();
    /** 
     * @return the Shooter Constants
     * @see ShooterConstants
     */
    public abstract ShooterConstants getShooterConstants();
    /**
     * @return the Climber Constants
     * @see ClimberConstants
     */
    public abstract ClimberConstants getClimberConstants();

    /**
     * Class containing drive constants
     */
    public abstract class DriveConstants {
        
        // Wheel measurement constants
        /*** @return the diameter of the drive wheel in meters*/
        public abstract double getWheelDiameter();
        /*** @return the width of the robot from wheel to wheel in meters*/
        public abstract double getWheelTrackWidth();
        /*** @return the length of the robot from wheel to wheel in meters*/
        public abstract double getWheelBase();

        // All of the motor IDs
        /*** @return the id of the front left drive motor*/
        public abstract int getFLDriveID();
        /*** @return the id of the front left steering motor*/
        public abstract int getFLSteeringID();

        /*** @return the id of the front right drive motor*/
        public abstract int getFRDriveID();
        /*** @return the id of the front right steering motor*/
        public abstract int getFRSteeringID();
        
        /*** @return the id of the back left drive motor*/
        public abstract int getBLDriveID();
        /*** @return the id of the back left steering motor*/
        public abstract int getBLSteeringID();

        /*** @return the id of the back right drive motor*/
        public abstract int getBRDriveID();
        /*** @return the id of the back right steering motor*/
        public abstract int getBRSteeringID();

        // All of the cancoder IDs
        /*** @return the id of the front left steering CANCoder*/
        public abstract int getFLCanEncoderID();
        /*** @return the id of the front right steering CANCoder*/
        public abstract int getFRCanEncoderID();
        /*** @return the id of the back left steering CANCoder*/
        public abstract int getBLCanEncoderID();
        /*** @return the id of the back right steering CANCoder*/
        public abstract int getBRCanEncoderID();

        // Pigeon ID constant
        /*** @return the id of the pigeon gyro*/
        public abstract int getPigeonID();

        // Encoder distance on axis (in radians) / Steer offset constants
        /*** @return the offset from zero in radians for the front left steering*/
        public abstract double getFLSteerOffset();
        /*** @return the offset from zero in radians for the front right steering*/
        public abstract double getFRSteerOffset();
        /*** @return the offset from zero in radians for the back left steering*/
        public abstract double getBLSteerOffset();
        /*** @return the offset from zero in radians for the back right steering*/
        public abstract double getBRSteerOffset();

        // Gear ratio & drive reduction constants
        /*** @return the gear ratio from the drive motor to the wheel*/
        public abstract double getDriveGearReduction();
        /*** @return the gear ratio from the steer motor to the wheel*/
        public abstract double getSteerGearReduction();
        /*** @return the module configuration for the front left module*/
        public abstract ModuleConfiguration getFLModuleGearRatio();
        /*** @return the module configuration for the front right module*/
        public abstract ModuleConfiguration getFRModuleGearRatio();
        /*** @return the module configuration for the back left module*/
        public abstract ModuleConfiguration getBLModuleGearRatio();
        /*** @return the module configuration for the back right module*/
        public abstract ModuleConfiguration getBRModuleGearRatio();
    }
    /**
     * Class containing intake constants
     */
    public abstract class IntakeConstants {
        /*** @return the id of the intake motor*/
        public abstract int getIntakeMotorID();
    }
    /**
     * Class containing shooter constants
     */
    public abstract class ShooterConstants {
        // Shooter Motor ID
        /*** @return The id of the first shooter motor*/
        public abstract int getShooterMotor1ID();
        /*** @return The id of the second shooter motor*/
        public abstract int getShooterMotor2ID();
        /*** @return The values of the p, i, d, & f PID constants*/
        public abstract PIDConstants getPIDConstants();
        /*** @return The values of the gear ratio and inverted constants*/
        public abstract GearRatio getGearRatio();
    }
    /**
     * Class containing climber constants
     */
    public abstract class ClimberConstants {
        // Climber Motor ID
        /*** @return the id of the first climber motor*/
        public abstract int getClimberMotor1ID();
        /*** @return the id of the second climber motor*/
        public abstract int getClimberMotor2ID();
        /*** @return the id of the thirt climber motor*/
        public abstract int getClimberMotor3ID();
    }
}