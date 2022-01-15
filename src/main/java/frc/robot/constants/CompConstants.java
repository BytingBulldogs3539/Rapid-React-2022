package frc.robot.constants;

import com.swervedrivespecialties.swervelib.ModuleConfiguration;

public class CompConstants extends Constants
{

    @Override
    public DriveConstants getDriveConstants() {
        return new DriveConstants();
    }

    @Override
    public IntakeConstants getIntakeConstants() {
        // TODO Auto-generated method stub
        return new IntakeConstants();
    }
    @Override
    public ShooterConstants getShooterConstants() {
        // TODO Auto-generated method stub
        return new ShooterConstants();
    }
    @Override
    public ClimberConstants getClimberConstants() {
        // TODO Auto-generated method stub
        return new ClimberConstants();
    }

    public class DriveConstants extends Constants.DriveConstants {
        @Override
        public double getWheelDiameter() {
            // TODO Auto-generated method stub
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
        public int FLCanEncoderID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int FRCanEncoderID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int BLCanEncoderID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int BRCanEncoderID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getPigeonID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getFLSteerOffset() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getFRSteerOffset() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getBLSteerOffset() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int getBRSteerOffset() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double getDriveGearReduction() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public double getSteerGearReduction() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public ModuleConfiguration FLModuleGearRatio() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ModuleConfiguration FRModuleGearRatio() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ModuleConfiguration BLModuleGearRatio() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public ModuleConfiguration BRModuleGearRatio() {
            // TODO Auto-generated method stub
            return null;
        }
    }
    public class IntakeConstants extends Constants.IntakeConstants {

        @Override
        public int intakeMotorID() {
            // TODO Auto-generated method stub
            return 0;
        }

    }
    public class ShooterConstants extends Constants.ShooterConstants {

        @Override
        public int shooterMotor1ID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int shooterMotor2ID() {
            // TODO Auto-generated method stub
            return 0;
        }

    }
    public class ClimberConstants extends Constants.ClimberConstants {

        @Override
        public int climberMotor1ID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int climberMotor2ID() {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public int climberMotor3ID() {
            // TODO Auto-generated method stub
            return 0;
        }

    }
}