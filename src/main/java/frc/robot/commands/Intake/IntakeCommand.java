package frc.robot.commands.Intake;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;


public class IntakeCommand extends Command {
  private boolean lsAttached = true;
  private IntakeSubsystem intake;

  public IntakeCommand(IntakeSubsystem intake, boolean limitSwitchAttached ) {
    this.intake = intake;
    this.lsAttached = limitSwitchAttached;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
  }


  @Override
  public void execute() {
    if(!intake.is_gp_present()){
      intake.intake();
    }
    
  }


  @Override
  public void end(boolean interrupted) {

    intake.stop();
    
  }


  @Override
  public boolean isFinished() {
    if(lsAttached){
    return intake.is_gp_present();
    }else{
      return false;
    }

  }
}
