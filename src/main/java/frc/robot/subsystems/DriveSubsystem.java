// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.constants.Constants;

public class DriveSubsystem extends SubsystemBase 
{
        public static final double MAX_VOLTAGE = 12.0;

        public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 *
                        RobotContainer.constants.getDriveConstants().getWheelTrackWidth() *
                        RobotContainer.constants.getDriveConstants().getWheelDiameter() * Math.PI;

        public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
                        Math.hypot(RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0, RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0);

        private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
                        // Front left
                        new Translation2d(RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0, RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0),
                        // Front right
                        new Translation2d(RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0, -RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0),
                        // Back left
                        new Translation2d(-RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0, RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0),
                        // Back right
                        new Translation2d(-RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0, -RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0));

        private final PigeonIMU m_pigeon = new PigeonIMU(RobotContainer.constants.getDriveConstants().getPigeonID());

        private final SwerveModule m_frontLeftModule;
        private final SwerveModule m_frontRightModule;
        private final SwerveModule m_backLeftModule;
        private final SwerveModule m_backRightModule;

        private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

        public DriveSubsystem() {
                ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");

                m_frontLeftModule = Mk3SwerveModuleHelper.createFalcon500(
                                // This parameter is optional, but will allow you to see the current state of
                                // the module on the dashboard.
                                tab.getLayout("Front Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(0,
                                                0),
                                // This can either be STANDARD or FAST depending on your gear configuration
                                RobotContainer.constants.getDriveConstants().getFLModuleGearRatio(),
                                // This is the ID of the drive motor
                                RobotContainer.constants.getDriveConstants().getFLDriveID(),
                                // This is the ID of the steer motor
                                RobotContainer.constants.getDriveConstants().getFLSteeringID(),
                                // This is the ID of the steer encoder
                                RobotContainer.constants.getDriveConstants().getFLCanEncoderID(),
                                // This is how much the steer encoder is offset from true zero (In our case,
                                // zero is facing straight forward)
                                RobotContainer.constants.getDriveConstants().getFLSteerOffset());
                

                // We will do the same for the other modules
                m_frontRightModule = Mk3SwerveModuleHelper.createFalcon500(
                                tab.getLayout("Front Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(2,
                                                0),
                                RobotContainer.constants.getDriveConstants().FRModuleGearRatio, RobotContainer.constants.getDriveConstants().FRDriveID, RobotContainer.constants.getDriveConstants().FRSteeringID,
                                RobotContainer.constants.getDriveConstants().FRCanEncoderID, RobotContainer.constants.getDriveConstants().FRSteerOffset);
                

                m_backLeftModule = Mk3SwerveModuleHelper.createFalcon500(
                                tab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(4,
                                                0),
                                RobotContainer.constants.getDriveConstants().BLModuleGearRatio, RobotContainer.constants.getDriveConstants().BLDriveID, RobotContainer.constants.getDriveConstants().BLSteeringID,
                                RobotContainer.constants.getDriveConstants().BLCanEncoderID, RobotContainer.constants.getDriveConstants().BLSteerOffset);

                m_backRightModule = Mk3SwerveModuleHelper.createFalcon500(
                                tab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(4,
                                                0),
                                RobotContainer.constants.getDriveConstants().BRModuleGearRatio, RobotContainer.constants.getDriveConstants().BRDriveID, RobotContainer.constants.getDriveConstants().BRSteeringID,
                                RobotContainer.constants.getDriveConstants().BRCanEncoderID, RobotContainer.constants.getDriveConstants().BRSteerOffset);

                CANCoder fl = new CANCoder(RobotContainer.constants.getDriveConstants().FLCanEncoderID);
                fl.configSensorDirection(false);
                CANCoder fr = new CANCoder(RobotContainer.constants.getDriveConstants().FRCanEncoderID);
                fr.configSensorDirection(false);
                CANCoder bl = new CANCoder(RobotContainer.constants.getDriveConstants().BLCanEncoderID);
                bl.configSensorDirection(false);
                CANCoder br = new CANCoder(RobotContainer.constants.getDriveConstants().BRCanEncoderID);
                br.configSensorDirection(false);
                zeroGyroscope();

        }

        public void zeroGyroscope() {
                m_pigeon.setFusedHeading(0.0);
                m_pigeon.setYaw(0);
        }

        public Rotation2d getGyroscopeRotation() {
                return Rotation2d.fromDegrees(m_pigeon.getFusedHeading());
        }

        public void drive(ChassisSpeeds chassisSpeeds) {
                m_chassisSpeeds = chassisSpeeds;
        }

        @Override
        public void periodic() {
                SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
                SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

                m_frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
                                states[0].angle.getRadians());
                m_frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
                                states[1].angle.getRadians());
                m_backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
                                states[2].angle.getRadians());
                m_backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
                                states[3].angle.getRadians());
        }
}