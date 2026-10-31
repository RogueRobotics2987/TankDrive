// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  private final SparkMax leftFront = new SparkMax(DriveConstants.LEFT_FRONT_CAN_ID, MotorType.kBrushless);
  private final SparkMax leftRear = new SparkMax(DriveConstants.LEFT_REAR_CAN_ID, MotorType.kBrushless);
  private final SparkMax rightFront = new SparkMax(DriveConstants.RIGHT_FRONT_CAN_ID, MotorType.kBrushless);
  private final SparkMax rightRear = new SparkMax(DriveConstants.RIGHT_REAR_CAN_ID, MotorType.kBrushless);

  private final DifferentialDrive drive = new DifferentialDrive(leftFront, rightFront);

  public DriveSubsystem() {
    drive.setDeadband(DriveConstants.DEADBAND);
    
    // Configure the left front motor.
    SparkMaxConfig leftFrontConfig = new SparkMaxConfig();
    leftFrontConfig.inverted(true);
    leftFront.configure(
      leftFrontConfig,
      ResetMode.kResetSafeParameters,
      PersistMode.kPersistParameters
    );

    // Configure the right front motor.
    SparkMaxConfig rightFrontConfig = new SparkMaxConfig();
    rightFrontConfig.inverted(false);

    rightFront.configure(
      rightFrontConfig,
      ResetMode.kResetSafeParameters,
      PersistMode.kPersistParameters
    );

    // Configure the left rear motor to follow left front.
    SparkMaxConfig leftRearConfig = new SparkMaxConfig();
    leftRearConfig.follow(leftFront);

    leftRear.configure(
      leftRearConfig,
      ResetMode.kResetSafeParameters,
      PersistMode.kPersistParameters
    );

    // Configure the right rear motor to follow right front.
    SparkMaxConfig rightRearConfig = new SparkMaxConfig();
    rightRearConfig.follow(rightFront);

    rightRear.configure(
      rightRearConfig,
      ResetMode.kResetSafeParameters,
      PersistMode.kPersistParameters
    );
  }

  public void arcadeDrive(double forwardSpeed, double turnSpeed) {
    drive.arcadeDrive(forwardSpeed, turnSpeed);
  }

  public void stop() {
    drive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
