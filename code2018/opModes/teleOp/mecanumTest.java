package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;

@TeleOp(name="mecanumTest", group = "TeleOp")

public class mecanumTest extends OpMode{
    MecanumDrive mecanumDrive;

    double LEFTFRONT;
    double RIGHTFRONT;
    double LEFTBACK;
    double RIGHTBACK;

    @Override
    public void init() {
        mecanumDrive = new MecanumDrive(hardwareMap);



    }

    @Override
    public void loop() {

        LEFTFRONT = ((gamepad1.left_stick_y / 4) - (gamepad1.left_stick_x) / 4) - (gamepad1.right_stick_x / 2);
        RIGHTFRONT = ((gamepad1.left_stick_y / 4) + (gamepad1.left_stick_x) / 4) + (gamepad1.right_stick_x / 2);
        LEFTBACK = ((gamepad1.left_stick_y / 4) + (gamepad1.left_stick_x) / 4) - (gamepad1.right_stick_x / 2);
        RIGHTBACK = ((gamepad1.left_stick_y / 4) - (gamepad1.left_stick_x) / 4) + (gamepad1.right_stick_x / 2);

        if (Math.abs(gamepad1.right_stick_x) < .1) {
            LEFTFRONT = LEFTFRONT * 3;
            RIGHTFRONT = RIGHTFRONT * 3;
            LEFTBACK = LEFTBACK * 3;
            RIGHTBACK = RIGHTBACK * 3;
        }

        LEFTBACK = Range.clip(LEFTBACK, -1, 1);
        RIGHTBACK = Range.clip(RIGHTBACK, -1, 1);
        LEFTFRONT = Range.clip(LEFTFRONT, -1, 1);
        RIGHTFRONT = Range.clip(RIGHTFRONT, -1, 1);

        if (gamepad1.left_stick_x > .999) {
            gamepad1.left_stick_x = 1;
        }
        if (gamepad1.left_stick_y > .999) {
            gamepad1.left_stick_y = 1;
        }
        if (gamepad1.right_stick_x > .999) {
            gamepad1.right_stick_x = 1;
        }

        mecanumDrive.motorLeftBack(LEFTBACK);
        mecanumDrive.motorLeftFront(LEFTFRONT);
        mecanumDrive.motorRightBack(RIGHTBACK);
        mecanumDrive.motorRightFront(RIGHTFRONT);
    }
}
