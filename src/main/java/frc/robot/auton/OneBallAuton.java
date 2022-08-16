package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.autoncommands.TrajectoryCommandGenerator;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import com.swervedrivespecialties.swervelib.control.*;
import com.swervedrivespecialties.swervelib.math.Rotation2;
import com.swervedrivespecialties.swervelib.math.Vector2;

/*** Auton command where the robot starts with one ball that it shoots. It then picks up two balls from two different locations and then shoots them from a different position than where it started. */
public class OneBallAuton extends SequentialCommandGroup {
    public OneBallAuton(DriveSubsystem driveSub, IntakeSubsystem intakeSubsystem, PneumaticsSubsystem pneumaticsSubsystem, ShooterSubsystem shooterSubsystem) {
        super(new Command[] {new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 2800,2800, 3000).withTimeout(2), //Shoots for 4 seconds
            new ParallelRaceGroup( TrajectoryCommandGenerator
                .getMotionCommand((new SimplePathBuilder
                (new Vector2(0.0, 0.0), Rotation2.ZERO))
                        .lineTo(new Vector2(-3, 0), Rotation2.fromDegrees(0))
                        .build(),RobotContainer.constants.getDriveConstants().getConstraints(), false, driveSub,false,false))}); // Shoots after it has finished driving
    }
}
