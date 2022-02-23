package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.autoncommands.TrajectoryCommandGenerator;
import frc.robot.subsystems.DriveSubsystem;
import com.swervedrivespecialties.swervelib.control.*;
import com.swervedrivespecialties.swervelib.math.Rotation2;
import com.swervedrivespecialties.swervelib.math.Vector2;

public class ArchTest extends SequentialCommandGroup {
    public ArchTest(DriveSubsystem driveSub) {
        super(new Command[] { TrajectoryCommandGenerator
                .getMotionCommand((new SimplePathBuilder(new Vector2(0.0, 0.0), Rotation2.ZERO))
                        .arcTo(new Vector2(2, -2), new Vector2(0, -2))
                        .arcTo(new Vector2(1, -3), new Vector2(1, -2), Rotation2.fromDegrees(180))
                        .arcTo(new Vector2(0, -4), new Vector2(1, -4), Rotation2.ZERO)
                        .build(),RobotContainer.constants.getDriveConstants().getConstraints(), false, driveSub) });
    }
}