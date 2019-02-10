package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Extension;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Grabber;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Lift;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.tankdrive;

@TeleOp(name = "liftTest", group = "TeleOp")
public class liftTest extends OpMode {

    Lift lift;
    MecanumDrive mecanumDrive;
    Extension extension;
    armAngle ArmAngle;

    Grabber grabber;


    double LEFTFRONT;
    double RIGHTFRONT;
    double LEFTBACK;
    double RIGHTBACK;

    double FLEXPOWER;

    double EXTEND;

    double TILTED;
    @Override
    public void init() {
        lift = new Lift(hardwareMap);
        lift.setZeroLift();

        mecanumDrive = new MecanumDrive(hardwareMap);
        mecanumDrive.setZeroPowerBehavior();

        extension = new Extension(hardwareMap);
        extension.setZeroPowerBehavior();

        ArmAngle = new armAngle(hardwareMap);
        ArmAngle.setZeroPowerBehavior();

        grabber = new Grabber(hardwareMap);
        grabber.setZeroPowerBehavior();

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


        mecanumDrive.motorLeftBack(LEFTBACK * .9);
        mecanumDrive.motorLeftFront(LEFTFRONT * .9);
        mecanumDrive.motorRightBack(RIGHTBACK * .9);
        mecanumDrive.motorRightFront(RIGHTFRONT * .9);



        // extension
        EXTEND = gamepad2.right_stick_y;

        extension.movement(EXTEND *.7);

        EXTEND = Range.clip(EXTEND, -1, 1);

        if (gamepad2.right_stick_y > .999) {
            gamepad2.right_stick_y = 1;
        }

        if (gamepad2.right_stick_y < -.999) {
            gamepad2.right_stick_y = -1;
        }



        //arm rotation
        TILTED = gamepad2.left_stick_y;

        ArmAngle.armPower(TILTED * .8);

        TILTED = Range.clip(TILTED, -1, 1);

        if (gamepad2.left_stick_y > .999) {
            gamepad2.left_stick_y = 1;
        }

        if (gamepad2.left_stick_y < -.999) {
            gamepad2.left_stick_y = -1;
        }

        //flexshaft

        FLEXPOWER = gamepad2.right_trigger;

        Range.clip(FLEXPOWER, 0, 1);

        grabber.spin(-FLEXPOWER);

    }
}
