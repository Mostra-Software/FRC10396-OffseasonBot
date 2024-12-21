// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.IntakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RunIntake extends SequentialCommandGroup {
  /** Creates a new RunIntake. */
  private IntakeSubsystem intake;
  private XboxController controller;
  public RunIntake(IntakeSubsystem s_intake, XboxController s_controller) {
    this.intake = s_intake;
    this.controller = s_controller;

    addCommands(
      new RunCommand(() -> intake.intake(), intake)
      .until(intake::is_gp_present),
      new RunCommand(() -> intake.stop(), intake),
      new RunCommand(() -> controller.setRumble(RumbleType.kBothRumble, IntakeConstants.rumbleStrength))
    );
    
  }
}
