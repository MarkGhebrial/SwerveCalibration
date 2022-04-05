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
    private static final int[] CANCODER_CAN_IDS = { 50, 51, 52, 53 };

    public static final XboxController xboxController = new XboxController(2);

    @Override
    public void robotInit() {
    }

    @Override
    public void run() {
        System.out.println("Move the swerve modules to their zero positions, then press A on the Xbox controller");

        while (!xboxController.getAButton()) {}

        for (int id : CANCODER_CAN_IDS) {
            CANCoder canCoder = new CANCoder(id);
            canCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Unsigned_0_to_360);
            canCoder.configMagnetOffset(0);

            double absolutePosition = canCoder.getAbsolutePosition();
            int canCoderSlotValue = (int) (absolutePosition * 100 + 0.5);

            System.out.println(
                "Setting custom parameter on encoder" + id +
                " to " + canCoderSlotValue +
                " (absolute position = " + absolutePosition + " degrees)"
            );

            canCoder.configSetCustomParam(canCoderSlotValue, 0);
        }
    }
}
