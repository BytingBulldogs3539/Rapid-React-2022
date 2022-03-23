package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
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

/*** Auton command where  */
public class FourBallAuton extends SequentialCommandGroup {
    public FourBallAuton(DriveSubsystem driveSub, IntakeSubsystem intakeSubsystem, PneumaticsSubsystem pneumaticsSubsystem, ShooterSubsystem shooterSubsystem) {
        super(new Command[] {new ShooterCommand(shooterSubsystem, intakeSubsystem, false, 2800,2800, 3000).withTimeout(2), //Shoots for 4 seconds
            new ParallelRaceGroup( TrajectoryCommandGenerator
                .getMotionCommand((new SimplePathBuilder(new Vector2(0.0, 0.0), Rotation2.ZERO))
                        .lineTo(new Vector2(-0.38, 0.125), Rotation2.fromDegrees(-145))
                        .lineTo(new Vector2(-1.52, 0.700), Rotation2.fromDegrees(-145))
                        .lineTo(new Vector2(-1.2, -0.75), Rotation2.fromDegrees(-270))
                        .lineTo(new Vector2(-0.75, -2.15), Rotation2.fromDegrees(-270))
                        .lineTo(new Vector2(-0.1, -0.8), Rotation2.fromDegrees(-374))
                        .build(),RobotContainer.constants.getDriveConstants().getConstraints(), false, driveSub),
                        new IntakeCommand(.5, 1.0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem).withTimeout(10)), // Runs intake while driving
                        new ShooterCommand(shooterSubsystem, intakeSubsystem, false, 2800,2800, 3000).withTimeout(4)}); // Shoots after it has finished driving
    }
}