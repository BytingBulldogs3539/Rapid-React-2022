package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.autoncommands.TrajectoryCommandGenerator;
import frc.robot.subsystems.DriveSubsystem;
import com.swervedrivespecialties.swervelib.control.*;
import com.swervedrivespecialties.swervelib.math.Rotation2;
import com.swervedrivespecialties.swervelib.math.Vector2;

/**
 * Used to calibrate the wheel diameter.
 */
public class DistanceTest extends SequentialCommandGroup {    
    public DistanceTest(DriveSubsystem driveSub) {
        super(new Command[] { TrajectoryCommandGenerator
                .getMotionCommand((new SimplePathBuilder(new Vector2(0.0, 0.0), Rotation2.ZERO))
                        .lineTo(new Vector2(2, 0.0), Rotation2.ZERO)
                        /* .lineTo(new Vector2(2, -2), Rotation2.fromDegrees(180))
                        .lineTo(new Vector2(1, -2), Rotation2.ZERO)
                        .lineTo(new Vector2(1, -3), Rotation2.ZERO) */
                        .build(),RobotContainer.constants.getDriveConstants().getConstraints(), false, driveSub,false,false) });
    }
}