// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class PneumaticsSubsystem extends SubsystemBase {
	// Final variables for pressure on and off
	final double minPressure = 80;
	final double maxPressure = 120;

	// Creates compressor & solenoid objects
	Compressor compressor;
	DoubleSolenoid intakeSolenoid;
	DoubleSolenoid staticClimberSolenoid;
	DoubleSolenoid climberSolenoid;
	DoubleSolenoid shooterSolenoid;

	/** Creates a new Pneumatics. */
	public PneumaticsSubsystem() {
		// Creates a compressor object
		compressor = new Compressor(RobotContainer.constants.getPneumaticsConstants().getCompressorID(),
				PneumaticsModuleType.REVPH);
		compressor.enableAnalog(minPressure, maxPressure);
		compressor.disable();

		// Creates 3 solenoid objects
		intakeSolenoid = new DoubleSolenoid(RobotContainer.constants.getPneumaticsConstants().getCompressorID(),
				PneumaticsModuleType.REVPH, RobotContainer.constants.getPneumaticsConstants().getIntakeSolenoidIn(),
				RobotContainer.constants.getPneumaticsConstants().getIntakeSolenoidOut());
		staticClimberSolenoid = new DoubleSolenoid(RobotContainer.constants.getPneumaticsConstants().getCompressorID(),
				PneumaticsModuleType.REVPH,
				RobotContainer.constants.getPneumaticsConstants().getStaticClimberSolenoidOpen(),
				RobotContainer.constants.getPneumaticsConstants().getStaticClimberSolenoidClose());
		climberSolenoid = new DoubleSolenoid(RobotContainer.constants.getPneumaticsConstants().getCompressorID(),
				PneumaticsModuleType.REVPH, RobotContainer.constants.getPneumaticsConstants().getClimberSolenoidIn(),
				RobotContainer.constants.getPneumaticsConstants().getClimberSolenoidOut());

		shooterSolenoid = new DoubleSolenoid(RobotContainer.constants.getPneumaticsConstants().getCompressorID(),
		PneumaticsModuleType.REVPH, RobotContainer.constants.getPneumaticsConstants().getShooterForward(),
		RobotContainer.constants.getPneumaticsConstants().getShooterReverse());

		// Add the compressor to the dashboard to see when its running.
		SmartDashboard.putData(compressor);
		setIntakeIn();
		releaseClimbBar();
		moveClimberIn();
		setShooterReverse();
	}

	public void setShooterForward() {
		shooterSolenoid.set(Value.kForward);
	}

	public void setShooterReverse() {
		shooterSolenoid.set(Value.kReverse);
	}

	public void setIntakeIn() {
		intakeSolenoid.set(Value.kForward);
	}

	public void setIntakeOut() {
		intakeSolenoid.set(Value.kReverse);
	}

	public void grabClimbBar() {
		staticClimberSolenoid.set(Value.kReverse);
	}

	public void releaseClimbBar() {
		staticClimberSolenoid.set(Value.kForward);
	}

	public void moveClimberIn() {
		climberSolenoid.set(Value.kForward);
	}

	public void moveClimberOut() {
		climberSolenoid.set(Value.kReverse);
	}

	public void enableCompressor() {
		compressor.enableAnalog(minPressure, maxPressure);
	}

	public void disableCompressor() {
		compressor.disable();
	}

	@Override
	public void periodic() {
	}
}
