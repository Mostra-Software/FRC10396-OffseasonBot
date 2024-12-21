package frc.robot.commands.Drive;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class JoystickDriveCommand extends Command {

  /** Creates a new JoystickDriveCommand. */
  private final DriveSubsystem m_drive;

  private final DoubleSupplier m_left;
  private final DoubleSupplier m_right;
  private final BooleanSupplier turnInPlace;
  private final SlewRateLimiter speedLimiter = new SlewRateLimiter(DriveConstants.kSpeedRateLimit);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(DriveConstants.kRotRateLimit);


  public JoystickDriveCommand(DriveSubsystem drive, DoubleSupplier left, DoubleSupplier right, BooleanSupplier turnInPlace) {
    this.m_drive = drive;
    this.m_left = left;
    this.m_right = right;
    this.turnInPlace = turnInPlace;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    DriveSubsystem.drive_.curvatureDrive(
    speedLimiter.calculate(m_left.getAsDouble()), 
    rotLimiter.calculate(m_right.getAsDouble()),
    turnInPlace.getAsBoolean()
    );
  }

  @Override
  public void end(boolean interrupted) {

    DriveSubsystem.drive_.arcadeDrive(0, 0);
  
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}