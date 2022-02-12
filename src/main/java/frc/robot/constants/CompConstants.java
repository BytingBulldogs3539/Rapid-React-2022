package frc.robot.constants;

import com.swervedrivespecialties.swervelib.ModuleConfiguration;
import com.swervedrivespecialties.swervelib.control.MaxAccelerationConstraint;
import com.swervedrivespecialties.swervelib.control.MaxVelocityConstraint;
import com.swervedrivespecialties.swervelib.control.TrajectoryConstraint;

import frc.robot.utilities.GearRatio;
import frc.robot.utilities.PIDConstants;

public class CompConstants extends Constants
{

    @Override
    public DriveConstants getDriveConstants() {
        return new DriveConstants();
    }

    @Override
    public IntakeConstants getIntakeConstants() {
        return new IntakeConstants();
    }

    @Override
    public ShooterConstants getShooterConstants() {
        return new ShooterConstants();
    }

    @Override
    public ClimberConstants getClimberConstants() {
        return new ClimberConstants();
    }   

    @Override
    public PneumaticsConstants getPneumaticsConstants() {
        return new Pneumatics();
    }
    public class DriveConstants extends Constants.DriveConstants {
        @Override
        public double getWheelDiameter() {
            // TODO Auto-generated method stub
            return 0.1016;
        }

        @Override
        public double getWheelTrackWidth() {
            // TODO Auto-generated method stub
            return 0.3302;
        }

        @Override
        public double getWheelBase() {
            // TODO Auto-generated method stub
            return 0.3302;
        }

        @Override
        public int getFLDriveID() {
            return 2;
        }

        @Override
        public int getFLSteeringID() {
            return 1;
        }

        @Override
        public int getFRDriveID() {
            return 18;
        }

        @Override
        public int getFRSteeringID() {
            return 17;
        }

        @Override
        public int getBLDriveID() {
            return 4;
        }

        @Override
        public int getBLSteeringID() {
            return 3;
        }

        @Override
        public int getBRDriveID() {
            return 14;
        }

        @Override
        public int getBRSteeringID() {
            return 13;
        }

        @Override
        public int getFLCanEncoderID() {
            return 30;
        }

        @Override
        public int getFRCanEncoderID() {
            return 33;
        }

        @Override
        public int getBLCanEncoderID() {
            return 31;
        }

        @Override
        public int getBRCanEncoderID() {
            return 32;
        }

        @Override
        public int getPigeonID() {
            // TODO Auto-generated method stub
            return 50;
        }

        @Override
        public double getFLSteerOffset() {
            // TODO Auto-generated method stub
            return -2.922;
        }

        @Override
        public double getFRSteerOffset() {
            // TODO Auto-generated method stub
            return -0.248;
        }

        @Override
        public double getBLSteerOffset() {
            // TODO Auto-generated method stub
            return -5.419;
        }

        @Override
        public double getBRSteerOffset() {
            // TODO Auto-generated method stub
            return -1.265;
        }

        @Override
        public double getDriveGearReduction() {
            return 1.0 / 6.75; // From MK4 Swerve Drive Specialties (specifically for L2s)
        }

        @Override
        public double getSteerGearReduction() {
            return 1.0 / 12.8; // From MK4 Swerve Drive Specialties
        }

        @Override
        public ModuleConfiguration getFLModuleGearRatio() {
            return new ModuleConfiguration(getWheelDiameter(), getDriveGearReduction(), false, getSteerGearReduction(), false); // Add appropriate parameter values when you have them.
        }

        @Override
        public ModuleConfiguration getFRModuleGearRatio() {
            return new ModuleConfiguration(getWheelDiameter(), getDriveGearReduction(), false, getSteerGearReduction(), false); // Add appropriate parameter values when you have them.
        }

        @Override
        public ModuleConfiguration getBLModuleGearRatio() {
            return new ModuleConfiguration(getWheelDiameter(), getDriveGearReduction(), false, getSteerGearReduction(), false); // Add appropriate parameter values when you have them.
        }

        @Override
        public ModuleConfiguration getBRModuleGearRatio() {
            return new ModuleConfiguration(getWheelDiameter(), getDriveGearReduction(), false, getSteerGearReduction(), false); // Add appropriate parameter values when you have them.
        }
        @Override
        public String getFrontCameraName() {
            // TODO Auto-generated method stub
            return "HD_USB_Camera";
        }
        @Override
        public String getShooterCameraName() {
            // TODO Auto-generated method stub
            return "mmal_service_16.1";
        }

        @Override
        public double getFrontCameraHeightMeters() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double getFrontCameraPitchRadians() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double getShooterCameraHeightMeters() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double getShooterCameraPitchRadians() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public PIDConstants getFrontCameraPIDConstants() {
            // TODO Auto-generated method stub
            return new PIDConstants(0.01, 0, 0);
        }

        @Override
        public PIDConstants getShooterCameraPIDConstants() {
            // TODO Auto-generated method stub
            return new PIDConstants(0.01, 0, 0);
        }

        @Override
        public PIDConstants getTranslationXPIDConstants() {
            // TODO Auto-generated method stub
            return new PIDConstants(20, 3, 0, 1.7);
        }

        @Override
        public PIDConstants getTranslationYPIDConstants() {
            // TODO Auto-generated method stub
            return new PIDConstants(20, 3, 0, 1.7);
        }

        @Override
        public PIDConstants getRotationConstants() {
            // TODO Auto-generated method stub
            return new PIDConstants(0.2, 0, 0, 0);
        }

        @Override
        public TrajectoryConstraint[] getConstraints() {
            TrajectoryConstraint[] constraints = { (TrajectoryConstraint)new MaxAccelerationConstraint(1), (TrajectoryConstraint)new MaxVelocityConstraint(1) };
            return constraints;
          }
    }
    public class IntakeConstants extends Constants.IntakeConstants {

        @Override
        public int getIntakeMotorID() {
            return 0;
        }

        @Override
        public int getKnockDownMotorID() {
            return 19;
        }

        @Override
        public GearRatio getIntakeGearRatio() {
            return new GearRatio(1, true, 40); //Gear ratio does not matter but direction does.
        }

        @Override
        public GearRatio getKnockDownGearRatio() {
            return new GearRatio(1, false, 40); //Gear ratio does not matter but direction does.
        }
        
    }
    public class ShooterConstants extends Constants.ShooterConstants {

        @Override
        public int getSM1ID() {
            // TODO Auto-generated method stub
            return 11;
        }

        @Override
        public int getSM2ID() {
            // TODO Auto-generated method stub
            return 6;
        }

        @Override
        public PIDConstants getPIDConstants() {
            // TODO Auto-generated method stub
            return new PIDConstants(0.05, 0.0005, 0, 0.04);
        }

        @Override
        public GearRatio getSM1GearRatio() {
            // TODO Auto-generated method stub
            return new GearRatio(1, true, 40);
        }

        @Override
        public int getSM3ID() {
            // TODO Auto-generated method stub
            return 5;
        }

        @Override
        public int getKMID() {
            // TODO Auto-generated method stub
            return 12;
        }

        @Override
        public GearRatio getSM2GearRatio() {
            // TODO Auto-generated method stub
            return new GearRatio(1, true, 40);
        }

        @Override
        public GearRatio getSM3GearRatio() {
            // TODO Auto-generated method stub
            return new GearRatio(1, true, 40);
        }

        @Override
        public GearRatio getKMGearRatio() {
            return new GearRatio(1, true, 40);
        }

        @Override
        public int getDigitalInput() {
            return 0;
        }

        @Override
        public boolean invertSensor() {
            return true;
        }

    }
    public class ClimberConstants extends Constants.ClimberConstants {

        @Override
        public int getLClimberMotorID() {
            return 15;
        }

        @Override
        public int getRClimberMotorID() {
            return 16;
        }

        @Override
        public GearRatio getLClimberGearRatio() {
            return new GearRatio(1, false, 40); //Gear ratio does not matter but inversion does
        }

        @Override
        public GearRatio getRClimberGearRatio() {
            return new GearRatio(1, false, 40); //Gear ratio does not matter but inversion does
        }
    }
    public class Pneumatics extends Constants.PneumaticsConstants {

        @Override
        public int getCompressorID() {
            return 25;
        }

        @Override
        public int getIntakeSolenoidIn() {
            return 2;
        }

        @Override
        public int getIntakeSolenoidOut() {
            return 3;
        }

        @Override
        public int getStaticClimberSolenoidOpen() {
            return 0;
        }

        @Override
        public int getStaticClimberSolenoidClose() {
            return 1;
        }

        @Override
        public int getClimberSolenoidIn() {
            return 4;
        }

        @Override
        public int getCLimberSolenoidOut() {
            return 5;
        }
    }
}