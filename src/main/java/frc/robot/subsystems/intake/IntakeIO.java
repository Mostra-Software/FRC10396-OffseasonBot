// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

/** Add your docs here. */
public interface IntakeIO {

  @AutoLog
  public static class IntakeIOInputs {
    public double appliedPercent = 0.0;
    public boolean isGPPresent = false;
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(IntakeIOInputs inputs) {}

  public default void runIntake(double voltage) {}

  public default void stopIntake() {}
}
