// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climb;

import frc.robot.subsystems.ClimbSubsystem;

import edu.wpi.first.wpilibj2.command.Command;


public class ClimbCommand extends Command {

  private boolean up;

  public ClimbCommand(ClimbSubsystem subsystem, Boolean up) {

    this.up = up;
    addRequirements(subsystem);

  }


  @Override
  public void initialize() {
  }


  @Override
  public void execute() {

    ClimbSubsystem.set_Climb_motor(up);
    
  }


  @Override
  public void end(boolean interrupted) {

    ClimbSubsystem.stop_Climb_motor();
  }


  @Override
  public boolean isFinished() {
    return false;
  }
}
