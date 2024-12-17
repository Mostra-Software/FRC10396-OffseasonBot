package frc.robot.commands.Intake;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;


public class ShootCommand extends Command {
  private IntakeSubsystem intake;
  public ShootCommand(IntakeSubsystem intake) {
    this.intake = intake;
    addRequirements(intake);
  }


  @Override
  public void initialize() {
  }


  @Override
  public void execute() {
      intake.shoot();
    
  }


  @Override
  public void end(boolean interrupted) {
  }


  @Override
  public boolean isFinished() {
    return false;
  }
}
