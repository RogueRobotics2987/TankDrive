// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  public static class DriveConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int LEFT_FRONT_CAN_ID = 3;
    public static final int LEFT_REAR_CAN_ID = 2;

    public static final int RIGHT_FRONT_CAN_ID = 1;
    public static final int RIGHT_REAR_CAN_ID = 4;

    public static final double DEADBAND = 0.10;
    public static final double GAS_PEDAL_LIMIT = 0.50;
  }
}
