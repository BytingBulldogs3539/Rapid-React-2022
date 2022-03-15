// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import com.swervedrivespecialties.swervelib.ModuleConfiguration;
import com.swervedrivespecialties.swervelib.control.TrajectoryConstraint;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.utilities.GearRatio;
import frc.robot.utilities.PIDConstants;

/*** An outline for two classes containing constants for both the practice bot and the comp bot. */
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
     * @return the Pneumatics Constants
     * @see PneumaticsConstants
     */
    public abstract PneumaticsConstants getPneumaticsConstants();

    /** 
     * @return the Candle/Lights Constants
     * @see LightsConstants
     */
    public abstract LightsConstants getLightsConstants();

    /*** Class containing drive constants */
    public abstract class DriveConstants {
        // Trajectory constraints for auton paths.
        /*** @return Trajectory constraints for the robot. */
        public abstract TrajectoryConstraint[] getConstraints();
        // Camera PID Constants
        /*** @return The PID constants for the front camera */
        public abstract PIDConstants getFrontCameraPIDConstants();
        /*** @return The PID constants for the shooter camera */
        public abstract PIDConstants getShooterCameraPIDConstants();
        /*** @return The PID constants for translation X */
        public abstract PIDConstants getTranslationXPIDConstants();
        /*** @return The PID constants for translation Y */
        public abstract PIDConstants getTranslationYPIDConstants();
        /*** @return The PID rotation constants*/
        public abstract PIDConstants getRotationConstants();

        // Camera Constants
        /*** @return The front camera's height in meters */
        public abstract double getFrontCameraHeightMeters();
        /*** @return the front camera's pitch in radians */
        public abstract double getFrontCameraPitchRadians();
        /*** @return The name of the front camera as a string */
        public abstract String getFrontCameraName();
        /*** @return The shooter camera height in meters*/
        public abstract double getShooterCameraHeightMeters();
        /*** @return The shooter camera pitch in radians as a double */
        public abstract double getShooterCameraPitchRadians();
        /*** @return The name of the shooter camera as a string */
        public abstract String getShooterCameraName();


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

        public abstract int getPDHID();

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
    /*** Class containing intake constants */
    public abstract class IntakeConstants {
        /*** @return the id of the intake motor*/
        public abstract int getIntakeMotorID();

        /*** @return the id of the knock down motor*/
        public abstract int getKnockDownMotorID();

        /*** @return the gear ratio of the intake*/
        public abstract GearRatio getIntakeGearRatio();

        /*** @return the gear ratio of the knock down*/
        public abstract GearRatio getKnockDownGearRatio();
    }
    /*** Class containing shooter constants */
    public abstract class ShooterConstants {
        // Shooter Motor ID
        /*** @return The ID of the first shooter motor*/
        public abstract int getSM1ID();
        /*** @return The ID of the second shooter motor*/
        public abstract int getSM2ID();
        /*** @return  The ID of the third shooter motor*/
        public abstract int getSM3ID();
        /*** @return  The ID of the kicker motor*/
        public abstract int getKMID();
        /*** @return The values of the p, i, d, & f PID constants*/
        public abstract PIDConstants getPIDConstants();
        /*** @return The values of the gear ratio and inverted constants for shooter motor 1*/
        public abstract GearRatio getSM1GearRatio();
        /*** @return The values of the gear ratio and inverted constants for shooter motor 2*/
        public abstract GearRatio getSM2GearRatio();
        /*** @return The values of the gear ratio and inverted constants for shooter motor 3*/
        public abstract GearRatio getSM3GearRatio();
        /*** @return The values of the gear ratio and inverted constants for shooter motor 4*/
        public abstract GearRatio getKMGearRatio();
        /*** @return The digital input of the sensor (default is 0) */
        public abstract int getDigitalInput();
        /*** @return True if the robot has a color sensor, false if not */
        public abstract boolean getColorSensor();
        /*** @return The color of the blue ball respective to the color sensor.*/
        public abstract Color getBlueColor();
        /*** @return The color of the red ball respective to the color sensor.*/
        public abstract Color getRedColor();
        /*** @return The tolerance of the blue ball's RGB values */
        public abstract int getBlueTolerance();
        /*** @return The tolerance of the red ball's RGB values */
        public abstract int getRedTolerance();

        /*** @return True if inverted, false if not */
        public abstract boolean invertSensor();
        /***  @return The shooter speed using the pitch of the target in the cameras view (bottom wheels). */
        public abstract double getShooterSpeed(double pitch);
        /*** @return The shooter speed of the top wheels using pitch. */
        public abstract double getTopShooterSpeed(double pitch);
        /***  @return If the hood should be used or not according the the pitch of the target in the cameras view.*/
        public abstract boolean getUseHood(double pitch);
        /***  @return The distance the robot is from the target in meters.*/
        public abstract double getDistance(double pitch);
        /***  @return The pitch the camera would see at a distance.*/
        public abstract double getPitch(double distance);
        /***  @return The estimated time that it takes the ball from leaving the robot to entering the hub.*/
        public abstract double getShotTime(double pitch);
        /***  @return The estimated yaw to target to for moving shots.*/
        public abstract double[] getMovingShotInfo(double pitch, ChassisSpeeds chassisSpeeds);
    }
    /*** Class containing climber constants */
    public abstract class ClimberConstants {
        // Climber Motor ID
        /*** @return the ID of the first climber motor*/
        public abstract int getLClimberMotorID();
        /*** @return the ID of the second climber motor*/
        public abstract int getRClimberMotorID();

        // Climber Motor Inversions
        /*** @return True if inverted, false if not*/
        public abstract boolean getLClimberMotorInversion();
        /*** @return True if inverted, false if not*/
        public abstract boolean getRClimberMotorInversion();
        
        // Limit Switch Motor IDs
        /*** @return the ID of the left limit switch */
        public abstract int getLeftLimitID();
        /*** @return the ID of the right limit switch*/
        public abstract int getRightLimitID();

        // Limit Switch Inversion IDs
        /*** @return True if inverted, false if not */
        public abstract boolean invertLeftLimitSensor();
        /*** @return True if inverted, false if not */
        public abstract boolean invertRightLimitSensor();

        /*** @return the gear ratio of the left climber */
        public abstract GearRatio getLClimberGearRatio();
        /*** @return the gear ratio of the right climber*/
        public abstract GearRatio getRClimberGearRatio();

        // Soft bottom getters for the override
        /*** @return the encoder value that corresponds to the bottom of the range for climbing on the left*/
        public abstract int getLClimberSoftBottom();
        /*** @return the encoder value that corresponds to the bottom of the range for climbing on the right*/
        public abstract int getRClimberSoftBottom();

        // Left and right climber frame height and extension height constants
        /*** @return The left climber arm's frame height */
        public abstract int getLFrameHeight();
        /*** @return The left climber arm's max extension height */
        public abstract int getLMaxExtensionHeight();

        /*** @return The right climber arm's frame height */
        public abstract int getRFrameHeight();
        /*** @return The right climber arm's max extension height */
        public abstract int getRMaxExtensionHeight();
    }

    /*** Class containing the Pneumatics Constants (for the compressor & the solenoids) */
    public abstract class PneumaticsConstants {
        public abstract int getCompressorID();
        public abstract int getIntakeSolenoidIn();
        public abstract int getIntakeSolenoidOut();

        public abstract int getStaticClimberSolenoidOpen();
        public abstract int getStaticClimberSolenoidClose();

        public abstract int getClimberSolenoidIn();
        public abstract int getClimberSolenoidOut();

        public abstract int getShooterForward();
        public abstract int getShooterReverse();
    }

    /*** Class containing the Candle/Lights Constants */
    public abstract class LightsConstants {
        // Candle ID
        public abstract int getCandleID();

        // Number of lights on comp and practice bots
        public abstract int getNumOfLights();
    }
}