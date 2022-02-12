package frc.robot.constants;

import com.swervedrivespecialties.swervelib.ModuleConfiguration;
import com.swervedrivespecialties.swervelib.control.MaxAccelerationConstraint;
import com.swervedrivespecialties.swervelib.control.MaxVelocityConstraint;
import com.swervedrivespecialties.swervelib.control.TrajectoryConstraint;

import frc.robot.utilities.GearRatio;
import frc.robot.utilities.PIDConstants;

public class PracConstants extends Constants
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
            // TODO Auto-generated method stub
            return 2;
        }

        @Override
        public int getFLSteeringID() {
            // TODO Auto-generated method stub
            return 1;
        }

        @Override
        public int getFRDriveID() {
            // TODO Auto-generated method stub
            return 18;
        }

        @Override
        public int getFRSteeringID() {
            // TODO Auto-generated method stub
            return 17;
        }

        @Override
        public int getBLDriveID() {
            // TODO Auto-generated method stub
            return 4;
        }

        @Override
        public int getBLSteeringID() {
            // TODO Auto-generated method stub
            return 3;
        }

        @Override
        public int getBRDriveID() {
            // TODO Auto-generated method stub
            return 14;
        }

        @Override
        public int getBRSteeringID() {
            // TODO Auto-generated method stub
            return 13;
        }

        @Override
        public int getFLCanEncoderID() {
            // TODO Auto-generated method stub
            return 32;
        }

        @Override
        public int getFRCanEncoderID() {
            // TODO Auto-generated method stub
            return 33;
        }

        @Override
        public int getBLCanEncoderID() {
            // TODO Auto-generated method stub
            return 30;
        }

        @Override
        public int getBRCanEncoderID() {
            // TODO Auto-generated method stub
            return 31;
        }

        @Override
        public int getPigeonID() {
            // TODO Auto-generated method stub
            return 25;
        }

        @Override
        public double getFLSteerOffset() {
            // TODO Auto-generated method stub
            return -0.1808 + 0.5 * Math.PI;
        }

        @Override
        public double getFRSteerOffset() {
            // TODO Auto-generated method stub
            return -5.6420 + 0.5 * Math.PI;
        }

        @Override
        public double getBLSteerOffset() {
            // TODO Auto-generated method stub
            return -2.8973 + 0.5 * Math.PI;
        }

        @Override
        public double getBRSteerOffset() {
            // TODO Auto-generated method stub
            return -0.6336- 0.1426 + 0.5 * Math.PI;
        }

        @Override
        public double getDriveGearReduction() {
            // TODO Auto-generated method stub
            return 1.0 / 6.75; // From MK4 Swerve Drive Specialties (specifically for L2s)
        }

        @Override
        public double getSteerGearReduction() {
            // TODO Auto-generated method stub
            return 1.0 / 12.8; // From MK4 Swerve Drive Specialties
        }

        @Override
        public ModuleConfiguration getFLModuleGearRatio() {
            // TODO Auto-generated method stub
            return new ModuleConfiguration(getWheelDiameter(), getDriveGearReduction(), false, getSteerGearReduction(), false); // Add appropriate parameter values when you have them.
        }

        @Override
        public ModuleConfiguration getFRModuleGearRatio() {
            // TODO Auto-generated method stub
            return new ModuleConfiguration(getWheelDiameter(), getDriveGearReduction(), false, getSteerGearReduction(), false); // Add appropriate parameter values when you have them.
        }

        @Override
        public ModuleConfiguration getBLModuleGearRatio() {
            // TODO Auto-generated method stub
            return new ModuleConfiguration(getWheelDiameter(), getDriveGearReduction(), false, getSteerGearReduction(), false); // Add appropriate parameter values when you have them.
        }

        @Override
        public ModuleConfiguration getBRModuleGearRatio() {
            // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub
            return -1;
        }

    }
    public class ShooterConstants extends Constants.ShooterConstants {

        @Override
        public int getSM1ID() {
            // TODO Auto-generated method stub
            return -1;
        }

        @Override
        public int getSM2ID() {
            // TODO Auto-generated method stub
            return -1;
        }

        @Override
        public PIDConstants getPIDConstants() {
            // TODO Auto-generated method stub
            return new PIDConstants(0, 0, 0, 0);
        }

        @Override
        public GearRatio getSM1GearRatio() {
            // TODO Auto-generated method stub
            return new GearRatio(1, false);
        }

        @Override
        public int getSM3ID() {
            // TODO Auto-generated method stub
            return -1;
        }

        @Override
        public int getKMID() {
            // TODO Auto-generated method stub
            return -1;
        }

        @Override
        public GearRatio getSM2GearRatio() {
            // TODO Auto-generated method stub
            return new GearRatio(1, false);
        }

        @Override
        public GearRatio getSM3GearRatio() {
            // TODO Auto-generated method stub
            return new GearRatio(1, false);
        }

        @Override
        public GearRatio getKMGearRatio() {
            // TODO Auto-generated method stub
            return new GearRatio(1, false);
        }

        @Override
        public int getDigitalInput() {
            // TODO Auto-generated method stub
            return 0;
        }

    }
    public class ClimberConstants extends Constants.ClimberConstants {

        @Override
        public int getClimberMotor1ID() {
            // TODO Auto-generated method stub
            return -1;
        }

        @Override
        public int getClimberMotor2ID() {
            // TODO Auto-generated method stub
            return -1;
        }
    }
    public class Pneumatics extends Constants.PneumaticsConstants {

        @Override
        public int getCompressorID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getIntakeSolenoidForward() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getIntakeSolenoidReverse() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getStaticClimberSolenoidForward() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getStaticClimberSolenoidReverse() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getClimberSolenoidForward() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getCLimberSolenoidReverse() {
            // TODO Auto-generated method stub
            return 0;
        }
        
    }
}