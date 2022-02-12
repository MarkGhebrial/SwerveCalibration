// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the run()
 * function when the
 * robot is enabled. If you change the name of this class or the package after
 * creating this
 * project, you must also update the build.gradle file in the project.
 */
public class Robot extends EducationalRobot {
    private static final int CANCODER_CAN_ID = 10;

    public static final XboxController xboxController = new XboxController(2);

    private CANCoder canCoder = new CANCoder(CANCODER_CAN_ID);

    /**
     * This function is run when the robot is first started up and should be used
     * for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        canCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
        canCoder.configMagnetOffset(0);
    }

    /** This function is run when the robot is enabled. */
    @Override
    public void run() {
        System.out.println("Move the swerve module to its zero position, then press A on the Xbox controller");

        while (!xboxController.getAButton()) {}

        double absolutePosition = canCoder.getAbsolutePosition();
        int canCoderSlotValue = (int) (absolutePosition * 100 + 0.5);

        System.out.println("Setting custom parameter 0 to " + canCoderSlotValue + " (absolute position = " + absolutePosition + " degrees)");

        canCoder.configSetCustomParam(canCoderSlotValue, 0);
    }
}
