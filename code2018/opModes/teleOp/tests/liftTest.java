package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Lift;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.tankdrive;

@TeleOp(name = "liftTest", group = "TeleOp")
public class liftTest extends OpMode {

    Lift lift;
    MecanumDrive mecanumDrive;


    double LEFTFRONT;
    double RIGHTFRONT;
    double LEFTBACK;
    double RIGHTBACK;
    @Override
    public void init() {
        lift = new Lift(hardwareMap);
        lift.setZeroLift();

        mecanumDrive = new MecanumDrive(hardwareMap);
        mecanumDrive.setZeroPowerBehavior();




    }

    @Override
    public void loop() {
        double liftPower;

        liftPower = gamepad1.left_stick_y;

        if (gamepad1.dpad_up){
            liftPower = -1;
        } else if (gamepad1.dpad_down){
            liftPower = 1;
        } else {
            liftPower = 0;
            lift.stop();
        }
        lift.lifing(liftPower);

        //mecanum driving stuff

        LEFTFRONT = ((gamepad1.left_stick_y / -3) - (gamepad1.left_stick_x) / 3) - (gamepad1.right_stick_x/1.5);
        RIGHTFRONT = ((gamepad1.left_stick_y / -3) + (gamepad1.left_stick_x) / 3) + (gamepad1.right_stick_x/1.5);
        LEFTBACK = ((gamepad1.left_stick_y / -3) + (gamepad1.left_stick_x) / 3) - (gamepad1.right_stick_x/1.5);
        RIGHTBACK = ((gamepad1.left_stick_y / -3) - (gamepad1.left_stick_x) / 3) + (gamepad1.right_stick_x/1.5);


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
