package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;

public class JoystickDriveCommand extends Command {

  /** Creates a new JoystickDriveCommand. */
  private final DriveSubsystem m_drive;

  private final DoubleSupplier m_left;
  private final DoubleSupplier m_right;

  public JoystickDriveCommand(
      DriveSubsystem drive,
      DoubleSupplier left,
      DoubleSupplier right) {
    m_drive = drive;
    m_left = left;
    m_right = right;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {

  if(Constants.sorun_cozucu){

    System.out.println("JoystickDriveCommand Command Start!");
    }

  }

  @Override
  public void execute() {
    DriveSubsystem.drive_.arcadeDrive(m_left.getAsDouble(), m_right.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {

    DriveSubsystem.drive_.arcadeDrive(0, 0);
    
    if(Constants.sorun_cozucu){
    
      System.out.println("JoystickDriveCommand Command End!");
  
    }
  
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}