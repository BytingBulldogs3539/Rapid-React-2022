package frc.robot.constants;

public class PracConstants extends Constants
{

    @Override
    public DriveConstants getDriveConstants() {
        return null;
    }
    public class DriveConstants extends Constants.DriveConstants {

        public double getWheelSize() {return 4.0;}
    }
    
}
