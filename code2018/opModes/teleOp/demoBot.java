package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Extension;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;

@TeleOp(name = "DemoBot" , group = "TeleOp")

public class demoBot extends OpMode{
    MecanumDrive mecanumDrive;
    org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle armAngle;
    Extension extension;

    //driveTrain
    double LEFTFRONT;
    double RIGHTFRONT;
    double LEFTBACK;
    double RIGHTBACK;

    //Arm Angling
    double thetaPower;

    //extension
    double extendPower;


    @Override
    public void init() {
        mecanumDrive = new MecanumDrive(hardwareMap);
        armAngle = new armAngle(hardwareMap);
        extension = new Extension(hardwareMap);

        mecanumDrive.setZeroPowerBehavior();
        armAngle.setZeroPowerBehavior();
        extension.setZeroPowerBehavior();

    }

    @Override
    public void loop() {


        //MecanumDrive Code
        LEFTFRONT = ((gamepad1.left_stick_y / -3) - (gamepad1.left_stick_x) / -3) - (gamepad1.right_stick_x/1.5);
        RIGHTFRONT = ((gamepad1.left_stick_y / -3) + (gamepad1.left_stick_x) / -3) + (gamepad1.right_stick_x/1.5);
        LEFTBACK = ((gamepad1.left_stick_y / -3) + (gamepad1.left_stick_x) / -3) - (gamepad1.right_stick_x/1.5);
        RIGHTBACK = ((gamepad1.left_stick_y / -3) - (gamepad1.left_stick_x) / -3) + (gamepad1.right_stick_x/1.5);


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



        //Extension Code
        extendPower = gamepad2.right_stick_y;

        if (gamepad2.right_stick_y > .99) {
            gamepad2.right_stick_y = 1;
        }
        if (gamepad2.right_stick_y != 0 ){
            extension.extendingPower(-extendPower);
        }else extension.stop();



        //Arm Angling Code
        thetaPower = gamepad2.left_stick_y;

        if(gamepad2.left_stick_y > .999) {
            gamepad2.left_stick_y = 1;
        }

        if (gamepad2.left_stick_y != 0) {
            armAngle.armPower(-thetaPower*.6);
        } else armAngle.stop();


    }
}
