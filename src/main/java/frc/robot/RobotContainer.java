// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.Climb.ClimbCommand;
import frc.robot.commands.Drive.JoystickDriveCommand;
import frc.robot.commands.Intake.AmpSequence;
import frc.robot.commands.Intake.IntakeCommand;
import frc.robot.commands.Intake.RunIntake;
import frc.robot.commands.Intake.ShootCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;



public class RobotContainer {

  private final ClimbSubsystem m_climbSubsystem = new ClimbSubsystem();
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

  //private final CommandPS4Controller m_driverController = new CommandPS4Controller(JoystickConstants.driver);
  public static XboxController m_operatorController = new XboxController(JoystickConstants.operator);



  public RobotContainer() {
    
    configureBindings();

    m_driveSubsystem.setDefaultCommand(
                new JoystickDriveCommand(m_driveSubsystem, () -> m_operatorController.getRawAxis(1),
                                                            () -> m_operatorController.getRawAxis(2),
                                                            () -> m_operatorController.getRightBumperPressed()));
    
  }

 
  private void configureBindings() {
    // Eski Intake Commandi (onceki haftalardan)
    /*
    new JoystickButton(m_operatorController, 7).
      whileTrue(new SequentialCommandGroup(new IntakeCommand(m_intakeSubsystem, true)));
     new JoystickButton(m_operatorController, 3)
      .onTrue(new ParallelRaceGroup(new WaitCommand(0.05),new IntakeCommand(m_intakeSubsystem, false)));

     */

    new JoystickButton(m_operatorController, 7)
      .whileTrue(new RunIntake(m_intakeSubsystem, m_operatorController))
      .onFalse(
        new RunCommand(()-> m_intakeSubsystem.stop(), m_intakeSubsystem)
        .alongWith(new InstantCommand(()-> m_operatorController.setRumble(RumbleType.kBothRumble,0)))
        );

    // Shoot Command (onceki haftalarda kullandiginiz)
    //new JoystickButton(m_operatorController, 8).whileTrue(new ShootCommand(m_intakeSubsystem));
    //ampe birakma commandi
    new JoystickButton(m_operatorController, 8)
    .whileTrue(new AmpSequence(m_intakeSubsystem))
    .onFalse(new RunCommand(()-> m_intakeSubsystem.stop(), m_intakeSubsystem));

    // Climb Command
    new JoystickButton(m_operatorController, 2).whileTrue(new ClimbCommand(m_climbSubsystem, false));
    new JoystickButton(m_operatorController,4 ).whileTrue(new ClimbCommand(m_climbSubsystem, true));
  
  }

 
  public Command getAutonomousCommand() {
    return null;
  }
}
