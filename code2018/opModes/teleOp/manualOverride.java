package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Extension;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Grabbing;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Lift;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;

@TeleOp (name = "manualOverride" , group = "TeleOp")
public class manualOverride extends OpMode{
    MecanumDrive mecanumDrive;
    armAngle armAngle;
    Extension extension;
   // Grabbing grabbing;
    Lift lift;




    double LEFTFRONT;
    double RIGHTFRONT;
    double LEFTBACK;
    double RIGHTBACK;

    double thetaPower;
    double gripPower;
    double grabPower;
    double grab2Power;
    double grip2Power;
    double extendPower;
    double liftPower;

    public void init() {
        mecanumDrive = new MecanumDrive(hardwareMap);
        armAngle = new armAngle(hardwareMap);
        extension = new Extension(hardwareMap);
     //   grabbing = new Grabbing(hardwareMap);
        lift = new Lift(hardwareMap);

        mecanumDrive.setZeroPowerBehavior();
        armAngle.setZeroPowerBehavior();
        extension.setZeroPowerBehavior();
    //   grabbing.setZeroPowerBehavior();
        lift.setZeroLift();

    }


    public void loop() {

        //mecanum driving stuff

        LEFTFRONT = ((gamepad1.left_stick_y / 3) - (gamepad1.left_stick_x) / 3) - (gamepad1.right_stick_x/1.5);
        RIGHTFRONT = ((gamepad1.left_stick_y / 3) + (gamepad1.left_stick_x) / 3) + (gamepad1.right_stick_x/1.5);
        LEFTBACK = ((gamepad1.left_stick_y / 3) + (gamepad1.left_stick_x) / 3) - (gamepad1.right_stick_x/1.5);
        RIGHTBACK = ((gamepad1.left_stick_y / 3) - (gamepad1.left_stick_x) / 3) + (gamepad1.right_stick_x/1.5);


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



        // arm angle
        thetaPower = gamepad2.left_stick_y;

        if(gamepad2.left_stick_y > .999) {
            gamepad2.left_stick_y = 1;
        }

        //setting the angle turning to how far the joystick is pushed
        armAngle.armPower(thetaPower);


        //arm extension

        //if dpad up, then arm extends
        extendPower = gamepad2.left_stick_x;

        if (gamepad2.left_stick_x > .99) {
            gamepad2.left_stick_x = 1;
        }

            extension.extendingPower(extendPower);




        // lift for hanging during endgame


        liftPower = gamepad2.right_stick_y;
        if (gamepad2.right_stick_y > .99) {
            gamepad2.right_stick_y = 1;
        }

            lift.liftingPower(liftPower);






    }
}
