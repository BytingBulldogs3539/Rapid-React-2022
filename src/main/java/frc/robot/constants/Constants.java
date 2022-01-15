// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import com.swervedrivespecialties.swervelib.ModuleConfiguration;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public abstract class Constants {
    public abstract DriveConstants getDriveConstants();
    public abstract IntakeConstants getIntakeConstants();
    public abstract ShooterConstants getShooterConstants();
    public abstract ClimberConstants getClimberConstants();

    public abstract class DriveConstants {
        
        // Wheel measurement constants
        public abstract double getWheelDiameter();
        public abstract double getWheelTrackWidth();
        public abstract double getWheelBase();

        // All of the motor IDs
        public abstract int getFLDriveID();
        public abstract int getFLSteeringID();

        public abstract int getFRDriveID();
        public abstract int getFRSteeringID();
        
        public abstract int getBLDriveID();
        public abstract int getBLSteeringID();

        public abstract int getBRDriveID();
        public abstract int getBRSteeringID();

        // All of the cancoder IDs
        public abstract int getFLCanEncoderID();
        public abstract int getFRCanEncoderID();
        public abstract int getBLCanEncoderID();
        public abstract int getBRCanEncoderID();

        // Pigeon ID constant
        public abstract int getPigeonID();

        // Encoder distance on axis (in radians) / Steer offset constants
        public abstract double getFLSteerOffset();
        public abstract double getFRSteerOffset();
        public abstract double getBLSteerOffset();
        public abstract double getBRSteerOffset();

        // Gear ratio & drive reduction constants
        public abstract double getDriveGearReduction();
        public abstract double getSteerGearReduction();
        public abstract ModuleConfiguration getFLModuleGearRatio();
        public abstract ModuleConfiguration getFRModuleGearRatio();
        public abstract ModuleConfiguration getBLModuleGearRatio();
        public abstract ModuleConfiguration getBRModuleGearRatio();
    }
    public abstract class IntakeConstants {
        // Intake Motor ID
        public abstract int getIntakeMotorID();
    }
    public abstract class ShooterConstants {
        // Shooter Motor ID
        public abstract int getShooterMotor1ID();
        public abstract int getShooterMotor2ID();
    }
    public abstract class ClimberConstants {
        // Climber Motor ID
        public abstract int getClimberMotor1ID();
        public abstract int getClimberMotor2ID();
        public abstract int getClimberMotor3ID();
    }
}