package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp.tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Grabber;

@TeleOp(name = "flexTest", group = "TeleOp")
public class flexTest extends OpMode{
    Grabber grabber;

    public void init() {
        grabber = new Grabber(hardwareMap);

        grabber.setZeroPowerBehavior();
    }


    public void loop() {
        telemetry.update();

        if (gamepad1.dpad_up) {
            grabber.spin(1);
        }else if (gamepad2.dpad_up){
            grabber.resetCube();
        }else if (gamepad2.dpad_down) {
            grabber.resetSphere();
        } else if (gamepad1.dpad_down){
            grabber.spin(-1);
        }else {grabber.stop();}

    }
}
