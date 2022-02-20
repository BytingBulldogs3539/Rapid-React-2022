package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autoncommands.TrajectoryCommandGenerator;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

import com.swervedrivespecialties.swervelib.control.*;
import com.swervedrivespecialties.swervelib.math.Rotation2;
import com.swervedrivespecialties.swervelib.math.Vector2;

public class ThreeBallFromMiddleTest extends SequentialCommandGroup {
    public ThreeBallFromMiddleTest(DriveSubsystem driveSub, IntakeSubsystem intakeSubsystem, PneumaticsSubsystem pneumaticsSubsystem, ShooterSubsystem shooterSubsystem) {
        super(new Command[] {new ShooterCommand(shooterSubsystem).withTimeout(4), //Shoots for 4 seconds
            new ParallelRaceGroup( TrajectoryCommandGenerator
                .getMotionCommand((new SimplePathBuilder(new Vector2(0.0, 0.0), Rotation2.ZERO))
                        .lineTo(new Vector2(-0.76, 0.25), Rotation2.fromDegrees(-150))
                        .lineTo(new Vector2(-1.52, 0.508), Rotation2.fromDegrees(-150))
                        .lineTo(new Vector2(-1.2, -0.75), Rotation2.fromDegrees(-290))
                        .lineTo(new Vector2(-1.0, -2.0), Rotation2.fromDegrees(-290))
                        .build(), false, driveSub),
                        new IntakeCommand(.5, 1.0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem).withTimeout(10)) }); // Runs intake while driving.
    }
}
