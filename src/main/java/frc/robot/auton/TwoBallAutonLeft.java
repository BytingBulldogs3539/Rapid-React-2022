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

/*** Auton command where the robot starts with one ball, picks up another, and then shoots both. This is for the left tarmac.*/
public class TwoBallAutonLeft extends SequentialCommandGroup {

    static TrajectoryConstraint[] constraints = { (TrajectoryConstraint) new MaxAccelerationConstraint(.5),
        (TrajectoryConstraint) new MaxVelocityConstraint(1) };


    public TwoBallAutonLeft(DriveSubsystem driveSub, IntakeSubsystem intakeSubsystem, PneumaticsSubsystem pneumaticsSubsystem, ShooterSubsystem shooterSubsystem) {
        super(new Command[] {
            new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 2800,2800, 3000).withTimeout(2), //Shoots for 4 seconds
            new ParallelRaceGroup( TrajectoryCommandGenerator
                .getMotionCommand((new SimplePathBuilder(new Vector2(0.0, 0.0), Rotation2.ZERO))
                .lineTo(new Vector2(-0.38, 0.0), Rotation2.fromDegrees(-179))
                .lineTo(new Vector2(-1.52, 0.0), Rotation2.fromDegrees(-179))
                .lineTo(new Vector2(-1.3, 0.0), Rotation2.ZERO)
                .build(),constraints, false, driveSub),
                new IntakeCommand(1.0, 0, intakeSubsystem, pneumaticsSubsystem, shooterSubsystem).withTimeout(10)), // Runs intake while driving
                new ShooterCommand(shooterSubsystem, intakeSubsystem, false, false, 3100,3150, 3000).withTimeout(4)}); // Shoots after it has finished driving
    }
}