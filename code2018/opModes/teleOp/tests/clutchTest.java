package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp.tests;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.stackClutch;

@TeleOp(name = "clutchTest", group = "TeleOp")
public class clutchTest extends OpMode {

    stackClutch stackClutch;

    boolean test = false;
    int current = 0;

    public void init() {
        stackClutch = new stackClutch(hardwareMap, telemetry);

        stackClutch.zerodrift();
        stackClutch.startMatch();
    }


    public void loop() {
        telemetry.addData("current clutch", current);
        stackClutch.telemetyDisplay();

            telemetry.update();
            if (gamepad2.dpad_up && !test){
                current++;
                test = true;
            }

            if (gamepad2.dpad_down && !test){
                current--;
                test = true;
            }

            if (gamepad2.right_bumper && !test){
                stackClutch.moveClutchUp(current);
                test = true;
            }

            if (gamepad2.left_bumper && !test){
                stackClutch.moveClutchDown(current);
                test = true;
            }

            if (!gamepad2.dpad_up && !gamepad2.left_bumper && !gamepad2.dpad_down && !gamepad2.right_bumper){
                test = false;
            }
    }
}
