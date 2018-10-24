package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.teles;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Suspension;

/**
 * Created by Titanium Allies on 1/28/2018.
 */
@TeleOp(name = "BallTest",group = "TeleOp")
@Disabled
public class suspensionTest extends OpMode{

    Suspension suspension;

    public void init() {
        suspension = new Suspension(hardwareMap);
    }

    public void loop() {
        if(gamepad2.left_bumper)
            suspension.upsieDoodleDoo();
        else if(gamepad2.right_bumper)
            suspension.downsieDaisy();


    }
}
