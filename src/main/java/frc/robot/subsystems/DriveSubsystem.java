// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.Pigeon2;
import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SwerveModule;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.ShooterSubsystem.Color;

public class DriveSubsystem extends SubsystemBase {
	public static final double MAX_VOLTAGE = 12.0;

	public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 *
			RobotContainer.constants.getDriveConstants().getWheelTrackWidth() *
			RobotContainer.constants.getDriveConstants().getWheelDiameter() * Math.PI;

	public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
			Math.hypot(RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0,
					RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0);

	private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
			// Front left
			new Translation2d(RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0,
					RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0),
			// Front right
			new Translation2d(RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0,
					-RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0),
			// Back left
			new Translation2d(-RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0,
					RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0),
			// Back right
			new Translation2d(-RobotContainer.constants.getDriveConstants().getWheelTrackWidth() / 2.0,
					-RobotContainer.constants.getDriveConstants().getWheelBase() / 2.0));

	SwerveDriveOdometry m_odometry;

	private final Pigeon2 m_pigeon = new Pigeon2(RobotContainer.constants.getDriveConstants().getPigeonID());

	Pose2d m_pose;

	private final SwerveModule m_frontLeftModule;
	private final SwerveModule m_frontRightModule;
	private final SwerveModule m_backLeftModule;
	private final SwerveModule m_backRightModule;

	private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

	private PhotonCamera frontCamera;
	private PhotonCamera shooterCamera;

	final boolean shouldRunFront;
	final boolean shouldRunShooter;

	PowerDistribution PDH;

	final boolean hasPDH;
	//final double sensorPositionCoefficient;
	//private static final double TICKS_PER_ROTATION = 2048.0;


	public DriveSubsystem() {
		int PDHID = RobotContainer.constants.getDriveConstants().getPDHID();
		if (PDHID != -1) {
			PDH = new PowerDistribution(PDHID, ModuleType.kRev);
			SmartDashboard.putData(PDH);
			hasPDH = true;
		} else
			hasPDH = false;

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
				RobotContainer.constants.getDriveConstants().getFRModuleGearRatio(),
				RobotContainer.constants.getDriveConstants().getFRDriveID(),
				RobotContainer.constants.getDriveConstants().getFRSteeringID(),
				RobotContainer.constants.getDriveConstants().getFRCanEncoderID(),
				RobotContainer.constants.getDriveConstants().getFRSteerOffset());

		m_backLeftModule = Mk3SwerveModuleHelper.createFalcon500(
				tab.getLayout("Back Left Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(4,
						0),
				RobotContainer.constants.getDriveConstants().getBLModuleGearRatio(),
				RobotContainer.constants.getDriveConstants().getBLDriveID(),
				RobotContainer.constants.getDriveConstants().getBLSteeringID(),
				RobotContainer.constants.getDriveConstants().getBLCanEncoderID(),
				RobotContainer.constants.getDriveConstants().getBLSteerOffset());

		m_backRightModule = Mk3SwerveModuleHelper.createFalcon500(
				tab.getLayout("Back Right Module", BuiltInLayouts.kList).withSize(2, 4).withPosition(6,
						0),
				RobotContainer.constants.getDriveConstants().getBRModuleGearRatio(),
				RobotContainer.constants.getDriveConstants().getBRDriveID(),
				RobotContainer.constants.getDriveConstants().getBRSteeringID(),
				RobotContainer.constants.getDriveConstants().getBRCanEncoderID(),
				RobotContainer.constants.getDriveConstants().getBRSteerOffset());

		zeroGyroscope();

		m_odometry = new SwerveDriveOdometry(m_kinematics, getGyroscopeRotation(),
				new Pose2d(0, 0, new Rotation2d()));

		// Creates two photon camera objects to represent both of the cameras on the
		// robot that are used for vision tracking if their names are not left blank. If
		// they are left blank, they will not be created, which will effectively disable
		// that part of the robot.
		if (RobotContainer.constants.getDriveConstants().getFrontCameraName().equals("")) {
			shouldRunFront = false;
		} else {
			shouldRunFront = true;
			frontCamera = new PhotonCamera(
					RobotContainer.constants.getDriveConstants().getFrontCameraName());
			frontCamera.setDriverMode(true);
		}

		if (RobotContainer.constants.getDriveConstants().getShooterCameraName().equals("")) {
			shouldRunShooter = false;
		} else {
			shouldRunShooter = true;
			shooterCamera = new PhotonCamera(
					RobotContainer.constants.getDriveConstants().getShooterCameraName());

		}
		//sensorPositionCoefficient = 2.0 * Math.PI / TICKS_PER_ROTATION * RobotContainer.constants.getDriveConstants().getBLModuleGearRatio().getSteerReduction();
		

		
		setDefaultCommand(new DriveCommand(this));
	}

	/*** Sets the state of the driver mode (on or off) */
	public void setDriverMode(boolean shouldUse) {
		frontCamera.setDriverMode(shouldUse);
	}

	/*** Sets the pipeline index (color of balls to look for) using the alliance color */
	public void setPipeline() {
		if(RobotContainer.shooterSubsystem.getAllianceColor() == Color.RED) {
			frontCamera.setPipelineIndex(0);
		} else if(RobotContainer.shooterSubsystem.getAllianceColor() == Color.BLUE) {
			frontCamera.setPipelineIndex(1);
		}
	}

	/**
	 * Used to zero the pigeon / gyroscope.
	 */
	public void zeroGyroscope() {
		m_pigeon.setYaw(0);
	}

	/**
	 * Used to get the angle of rotation of the robot from the last time the gyro
	 * was zeroed.
	 * 
	 * @returns gyro angle.
	 */
	public Rotation2d getGyroscopeRotation() {
		return Rotation2d.fromDegrees(m_pigeon.getYaw());
	}

	public void drive(ChassisSpeeds chassisSpeeds) {
		m_chassisSpeeds = chassisSpeeds;
	}

	public SwerveModuleState getState(SwerveModule swerveModule) {
		return new SwerveModuleState(swerveModule.getDriveVelocity(),
				Rotation2d.fromDegrees(Math.toDegrees(swerveModule.getSteerAngle())));			}

	public Pose2d getPose() {
		return m_pose;
	}

	public void resetPose(Pose2d pose) {
		m_odometry.resetPosition(pose, getGyroscopeRotation());
	}

	// Getter methods for front camera
	public Boolean getFrontVisionSeeing() {
		if (!RobotContainer.constants.getDriveConstants().getFrontCameraName().equals("")) {
			return frontCamera.getLatestResult().hasTargets();
		}
		return false;
	}

	public double getFrontVisionYaw() {
		if (!RobotContainer.constants.getDriveConstants().getFrontCameraName().equals("")) 
		{
			if (getFrontVisionSeeing()) 
			{
				PhotonTrackedTarget target = frontCamera.getLatestResult().getBestTarget();
				if (target != null)
					return target.getYaw();
				else
					return 0;
			}
		}
		return 0;
	}

	public double getFrontVisionPitch() {
		if (!RobotContainer.constants.getDriveConstants().getFrontCameraName().equals("")) {
			if (getFrontVisionSeeing()) {
				return frontCamera.getLatestResult().getBestTarget().getPitch();
			}
		}

		return 0;
	}

	// Getter methods for the shooter camera
	public Boolean getShooterVisionSeeing() {
		if (!RobotContainer.constants.getDriveConstants().getShooterCameraName().equals("")) {
			return shooterCamera.getLatestResult().hasTargets();
		}
		return false;
	}
	public double getShooterVisionYaw() {
		if (!RobotContainer.constants.getDriveConstants().getShooterCameraName().equals("")) {
			if (getShooterVisionSeeing()) {
				PhotonTrackedTarget target = shooterCamera.getLatestResult().getBestTarget();
				if (target != null)
					return target.getYaw();
				else
					return 0;
			}
		}
		return 0;
	}

	public double getShooterVisionPitch() {
		if (!RobotContainer.constants.getDriveConstants().getShooterCameraName().equals("")) {
			if (getShooterVisionSeeing()) {
				PhotonTrackedTarget target = shooterCamera.getLatestResult().getBestTarget();
				if (target != null)
					return target.getPitch();
				else
					return 0;
			}
		}

		return 0;
	}

	// Vision tracking target getter methods for the front camera
	public double getFrontTargetHeight() {
		return 0.0;
	}

	public double getFrontTargetDistance() {
		if (getFrontVisionSeeing()) {
			return PhotonUtils.calculateDistanceToTargetMeters(
					RobotContainer.constants.getDriveConstants().getFrontCameraHeightMeters(),
					getFrontTargetHeight(),
					RobotContainer.constants.getDriveConstants().getFrontCameraPitchRadians(),
					Units.degreesToRadians(getFrontVisionPitch()));
		}
		return 0; // This is the return value
	}

	// Vision tracking target getter methods for the shooter camera
	public double getShooterTargetHeight() {
		return 0.0;
	}

	public ChassisSpeeds getChassisSpeeds() {
		return m_kinematics.toChassisSpeeds(getState(m_frontLeftModule),
				getState(m_frontRightModule),
				getState(m_backLeftModule), getState(m_backRightModule));
	}
	
	public double getMovingShooterTargetYaw() {
		return RobotContainer.constants.getShooterConstants().getMovingShotInfo(getShooterVisionPitch(), getChassisSpeeds())[2];
	}

	public double getMovingShooterTargetSpeed() {
		return RobotContainer.constants.getShooterConstants().getMovingShotInfo(getShooterVisionPitch(), getChassisSpeeds())[0];
	}

	public double getMovingTopShooterTargetSpeed() {
		return RobotContainer.constants.getShooterConstants().getMovingShotInfo(getShooterVisionPitch(), getChassisSpeeds())[1];
	}

	@Override
	public void periodic() {
		SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
		SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);
		
		/*short[] ba_xyz = new short[3];
		m_pigeon.getBiasedAccelerometer(ba_xyz);
		SmartDashboard.putNumber("Accel X", ba_xyz[0]);
		SmartDashboard.putNumber("Accel Y", ba_xyz[1]);
		SmartDashboard.putNumber("Accel Z", ba_xyz[2]);

		double[] ypr = new double[3];
		m_pigeon.getYawPitchRoll(ypr);
		SmartDashboard.putNumber("Rotation Y", ypr[0]);
		SmartDashboard.putNumber("Rotation P", ypr[1]);
		SmartDashboard.putNumber("Rotation R", ypr[2]);*/


		m_frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
				states[0].angle.getRadians());
		m_frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
				states[1].angle.getRadians());
		m_backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
				states[2].angle.getRadians());
		m_backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
				states[3].angle.getRadians());

		m_pose = m_odometry.update(getGyroscopeRotation(), getState(m_frontLeftModule),
				getState(m_frontRightModule),
				getState(m_backLeftModule), getState(m_backRightModule));

		SmartDashboard.putNumber("X Pose", m_pose.getX());
		SmartDashboard.putNumber("Y Pose", m_pose.getY());
		SmartDashboard.putNumber("Theta Pose", m_pose.getRotation().getDegrees());
		SmartDashboard.putNumber("Target Distance", RobotContainer.constants.getShooterConstants().getDistance(getShooterVisionPitch()));
	}
}