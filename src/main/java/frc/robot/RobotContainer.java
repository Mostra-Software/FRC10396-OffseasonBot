// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.climb.Climb;
import frc.robot.commands.drive.DriveBackwards;
import frc.robot.commands.drive.JoystickDrive;
import frc.robot.commands.intake.AmpSequence;
import frc.robot.commands.intake.RunIntake;
import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.climber.ClimberIO;
import frc.robot.subsystems.climber.ClimberIOVictorSPX;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.DriveIO;
import frc.robot.subsystems.drive.DriveIOSim;
import frc.robot.subsystems.drive.DriveIOVictorSPX;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIO;
import frc.robot.subsystems.intake.IntakeIOSim;
import frc.robot.subsystems.intake.IntakeIOVictorSPX;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private final Drive drive;
  private final Intake intake;
  private final Climber climber;

  // Controller
  private final Joystick controller = new Joystick(0);

  private final LoggedDashboardChooser<Command> autoChooser;

  // Dashboard inputs
  // private final LoggedDashboardChooser<Command> autoChooser;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    switch (Constants.currentMode) {
      case REAL:
        // Real robot, instantiate hardware IO implementations
        drive = new Drive(new DriveIOVictorSPX());
        intake = new Intake(new IntakeIOVictorSPX());
        climber = new Climber(new ClimberIOVictorSPX());
        break;

      case SIM:
        // Sim robot, instantiate physics sim IO implementations
        drive = new Drive(new DriveIOSim());
        intake = new Intake(new IntakeIOSim());
        climber = new Climber(new ClimberIO() {});
        break;

      default:
        // Replayed robot, disable IO implementations
        drive = new Drive(new DriveIO() {});
        intake = new Intake(new IntakeIO() {});
        climber = new Climber(new ClimberIO() {});

        break;
    }

    autoChooser = new LoggedDashboardChooser<>("Otonom");
    autoChooser.addOption(
        "3sn Geri", new RunCommand(() -> drive.driveArcade(-0.8, 0), drive).withTimeout(3));
    autoChooser.addOption(
        "5sn Geri", new RunCommand(() -> drive.driveArcade(-0.8, 0), drive).withTimeout(5));
    autoChooser.addOption("Yerinde Dur", new WaitCommand(13));
    autoChooser.addOption(
        "5sn bekle, 3sn Geri",
        new SequentialCommandGroup(
            new WaitCommand(5),
            new RunCommand(() -> drive.driveArcade(-0.8, 0), drive).withTimeout(3)));
    autoChooser.addOption(
        "5sn bekle, 5sn Geri",
        new SequentialCommandGroup(
            new WaitCommand(5),
            new RunCommand(() -> drive.driveArcade(-0.8, 0), drive).withTimeout(5)));
    autoChooser.addOption(
        "10sn bekle, 3sn Geri",
        new SequentialCommandGroup(new WaitCommand(10), new DriveBackwards(drive, 3)));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    drive.setDefaultCommand(new JoystickDrive(drive, controller));

    // Intake Command
    new JoystickButton(controller, 7)
        .whileTrue(new RunIntake(intake))
        .onFalse(new RunCommand(() -> intake.stop(), intake));

    // ampe birakma commandi
    new JoystickButton(controller, 8)
        .whileTrue(new AmpSequence(intake))
        .onFalse(new RunCommand(() -> intake.stop(), intake));

    // Climb Command
    new JoystickButton(controller, 2).whileTrue(new Climb(climber, false));
    new JoystickButton(controller, 4).whileTrue(new Climb(climber, true));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.get();
    // return new DriveBackwards(drive, 3);
  }
}
