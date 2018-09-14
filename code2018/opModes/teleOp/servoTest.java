package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Continuous;

/**
 * Created by Titanium Allies on 1/12/2018.
 */
@TeleOp(name = "ServoTest",group = "TeleOp")

public class servoTest extends OpMode {
      Continuous continuous;


    public void init() {
        continuous = new Continuous(hardwareMap);

    }
    public void loop() {
        if (gamepad1.dpad_left)
            continuous.open();
        else if (gamepad1.dpad_right)
            continuous.close();
        else
            continuous.neutral();


    }
}
