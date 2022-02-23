package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.autoncommands.TrajectoryCommandGenerator;
import frc.robot.subsystems.DriveSubsystem;
import com.swervedrivespecialties.swervelib.control.*;
import com.swervedrivespecialties.swervelib.math.Rotation2;
import com.swervedrivespecialties.swervelib.math.Vector2;

public class Back2Start extends SequentialCommandGroup {
    public Back2Start(DriveSubsystem driveSub) {
        super(new Command[] { TrajectoryCommandGenerator
                .getMotionCommand((new SimplePathBuilder(new Vector2(1.0, 0.0), Rotation2.ZERO))
                        .arcTo(new Vector2(2, -1), new Vector2(1, -1), Rotation2.ZERO)
                        .lineTo(new Vector2(2, -3), Rotation2.ZERO)
                        // This point for the big curve is divided into 2 to allow it to take the path
                        // that we want it to.
                        .arcTo(new Vector2(1, -4), new Vector2(1, -3), Rotation2.ZERO)
                        .arcTo(new Vector2(0, -3), new Vector2(1, -3), Rotation2.ZERO)
                        .lineTo(new Vector2(0, -1), Rotation2.ZERO)
                        .arcTo(new Vector2(1, 0), new Vector2(1, -1), Rotation2.ZERO)
                        .build(),RobotContainer.constants.getDriveConstants().getConstraints(), false, driveSub) });
    }
}