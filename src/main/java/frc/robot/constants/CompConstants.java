package frc.robot.constants;

import com.swervedrivespecialties.swervelib.ModuleConfiguration;

import frc.robot.utilities.GearRatio;
import frc.robot.utilities.PIDConstants;

public class CompConstants extends Constants
{

    public DriveConstants getDriveConstants() {return new DriveConstants();}
    public IntakeConstants getIntakeConstants() {return new IntakeConstants();}

    public ShooterConstants getShooterConstants() {return new ShooterConstants();}
    public ClimberConstants getClimberConstants() {return new ClimberConstants();}

    public class DriveConstants extends Constants.DriveConstants {
        @Override
        public double getWheelDiameter() {
            return 4.0;
        }

        @Override
        public double getWheelTrackWidth() {
            // TODO Auto-generated method stub
            return 0.0;
        }

        @Override
        public double getWheelBase() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getFLDriveID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getFLSteeringID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getFRDriveID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getFRSteeringID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getBLDriveID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getBLSteeringID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getBRDriveID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getBRSteeringID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getFLCanEncoderID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getFRCanEncoderID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getBLCanEncoderID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getBRCanEncoderID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getPigeonID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double getFLSteerOffset() {
            // TODO Auto-generated method stub
            return 0.1808 + 0.5 * Math.PI;
        }

        @Override
        public double getFRSteerOffset() {
            // TODO Auto-generated method stub
            return 5.6420 + 0.5 * Math.PI;
        }

        @Override
        public double getBLSteerOffset() {
            // TODO Auto-generated method stub
            return 2.8973 + 0.5 * Math.PI;
        }

        @Override
        public double getBRSteerOffset() {
            // TODO Auto-generated method stub
            return 0.6336 + 0.5 * Math.PI;
        }

        @Override
        public double getDriveGearReduction() {
            // TODO Auto-generated method stub
            return (11.0 / 40.0) * (32.0 / 16.0) * (1.0 / 3.0);
        }

        @Override
        public double getSteerGearReduction() {
            // TODO Auto-generated method stub
            return -(9.0 / 24.0) * (18.0 / 72.0);
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
    }
    public class IntakeConstants extends Constants.IntakeConstants {

        @Override
        public int getIntakeMotorID() {
            // TODO Auto-generated method stub
            return 0;
        }

    }
    public class ShooterConstants extends Constants.ShooterConstants {

        @Override
        public int getShooterMotor1ID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getShooterMotor2ID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public PIDConstants getPIDConstants() {
            // TODO Auto-generated method stub
            return new PIDConstants(0, 0, 0, 0);
        }

        @Override
        public GearRatio getGearRatio() {
            // TODO Auto-generated method stub
            return new GearRatio(1, false);
        }

    }
    public class ClimberConstants extends Constants.ClimberConstants {

        @Override
        public int getClimberMotor1ID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getClimberMotor2ID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getClimberMotor3ID() {
            // TODO Auto-generated method stub
            return 0;
        }

    }
}