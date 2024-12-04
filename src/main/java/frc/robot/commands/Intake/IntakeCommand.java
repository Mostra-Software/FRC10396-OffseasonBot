package frc.robot.commands.Intake;

import frc.robot.Constants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;


public class IntakeCommand extends Command {

  public IntakeCommand(IntakeSubsystem subsystem) {

    addRequirements(subsystem);

  }


  @Override
  public void initialize() {

    if(Constants.sorun_cozucu){

    System.out.println("Intake Command Start!");

    }

  }


  @Override
  public void execute() {

      IntakeSubsystem.intake_motor.set(ControlMode.PercentOutput, IntakeConstants.intake_speed*-1);
    
  }


  @Override
  public void end(boolean interrupted) {

    IntakeSubsystem.intake_motor.set(ControlMode.PercentOutput, 0);
    
    if(Constants.sorun_cozucu){

    System.out.println("Intake Command End!");
    
  }
    
  }


  @Override
  public boolean isFinished() {

    return !IntakeSubsystem.get_intake_sensor();

  }
}
