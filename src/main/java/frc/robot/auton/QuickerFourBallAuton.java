package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
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

/*** Auton command where the robot starts with one ball that it shoots. It then picks up two balls from two different locations and then shoots them from a different position than where it started. */
public class QuickerFourBallAuton extends SequentialCommandGroup {
    static TrajectoryConstraint[] LongConstraints = { (TrajectoryConstraint) new MaxAccelerationConstraint(2.1), // More aggressive constraints
        (TrajectoryConstraint) new MaxVelocityConstraint(6) };

    public QuickerFourBallAuton(DriveSubsystem driveSub, IntakeSubsystem intakeSubsystem, PneumaticsSubsystem pneumaticsSubsystem, ShooterSubsystem shooterSubsystem) {
        super(new Command[] {
            new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 2900, 2900, 2000).withTimeout(1.5),

            new ParallelRaceGroup( TrajectoryCommandGenerator
                .getMotionCommand((new SimplePathBuilder(new Vector2(0.0, 0.0), Rotation2.ZERO))
                        .lineTo(new Vector2(-0.6, 0 + 0.381), Rotation2.fromDegrees(180))
                        .lineTo(new Vector2(-1.2192, 0 + 0.42), Rotation2.fromDegrees(180))
                        .lineTo(new Vector2(-0.9, 1.5 + 0.50), Rotation2.fromDegrees(310))
                        .lineTo(new Vector2(0.60, 2.70), Rotation2.fromDegrees(310))
                        .lineTo(new Vector2(1.0, 2.30 + 0.50), Rotation2.fromDegrees(414))
                        .build(), RobotContainer.constants.getDriveConstants().getConstraints(), false, driveSub, false, false),
                        new IntakeCommand(1.0, 0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem).withTimeout(10),
                        new ShooterCommand(shooterSubsystem, intakeSubsystem, true, false, 3400, 3400, 2000).withTimeout(10)), // Runs intake while driving
                        
                        new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 3400, 3400, 2000).withTimeout(1.5),

                        new ParallelRaceGroup( TrajectoryCommandGenerator
                        .getMotionCommand((new SimplePathBuilder(new Vector2(1.0, 2.80), Rotation2.fromDegrees(414)))
                                .lineTo(new Vector2(-1.0, 6.4), Rotation2.fromDegrees(275))
                                // .lineTo(new Vector2(-4.25, 2.5), Rotation2.fromDegrees(-180))
                                .build(), LongConstraints, false, driveSub, false, false),
                                new IntakeCommand(1.0, 0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem).withTimeout(10)),
                        /*
                        // Drives into the fourth ball while using vision tracking.
                        new ParallelRaceGroup( TrajectoryCommandGenerator
                        .getMotionCommand((new SimplePathBuilder(new Vector2(-3.0, 2.5), Rotation2.fromDegrees(-175)))
                                .lineTo(new Vector2(-4.25, 2.5), Rotation2.fromDegrees(-180))
                                .build(), LongConstraints, false, driveSub, true, false),
                                new IntakeCommand(1.0, 0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem).withTimeout(10)),


                        //new IntakeCommand(1.0, 0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem).withTimeout(.5),

                         new ParallelRaceGroup( TrajectoryCommandGenerator
                        .getMotionCommand((new SimplePathBuilder(new Vector2(-4.25, 2.5), Rotation2.fromDegrees(-175)))
                                .lineTo(new Vector2(0.0,-.7), Rotation2.fromDegrees(0))
                                .build(), LongConstraints, false, driveSub,false,false),
                                new IntakeCommand(1.0, 0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem).withTimeout(10),
                                new ShooterCommand(shooterSubsystem, intakeSubsystem, true, false, 2800,2800, 3000).withTimeout(10)),

                        new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 2800,2800, 3000).withTimeout(2)
                        */
                            }); // Shoots after it has finished driving
    }
}