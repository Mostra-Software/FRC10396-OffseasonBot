package frc.robot.commands.Intake;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;


public class ShootCommand extends Command {

  public ShootCommand(IntakeSubsystem subsystem) {

    addRequirements(subsystem);

  }


  @Override
  public void initialize() {

    if(Constants.sorun_cozucu){

    System.out.println("Shoot Command Start!");

    }

  }


  @Override
  public void execute() {

      IntakeSubsystem.start_Shooter();
    
  }


  @Override
  public void end(boolean interrupted) {

    IntakeSubsystem.stop_intake();
    if(Constants.sorun_cozucu){

    System.out.println("Intake Command End!");
    
  }
    
  }


  @Override
  public boolean isFinished() {

    return false;

  }
}
