// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final DriveSubsystem drive = new DriveSubsystem();
  private final CommandXboxController controller = new CommandXboxController(DriveConstants.DRIVER_CONTROLLER_PORT);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureDefaultCommands();
  }

  private void configureDefaultCommands() {
    drive.setDefaultCommand(
      drive.run(() -> {
        // Gas pedal: left trigger controls speed
        double outputMultiplier = Math.min(controller.getRightTriggerAxis() + Constants.DriveConstants.GAS_PEDAL_LIMIT, 1.0);

        // Forward/backward movement
        double forwardSpeed = -controller.getLeftY() * outputMultiplier;

        // Left/right turning
        double turnSpeed = -controller.getRightX() * outputMultiplier;

        // Drive using arcade mode
        drive.arcadeDrive(forwardSpeed, turnSpeed);

      }).finallyDo(interrupted -> drive.stop())
    );
  }
}
