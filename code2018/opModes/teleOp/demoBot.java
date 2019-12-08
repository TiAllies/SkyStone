package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Extension;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccanum;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;

import java.util.concurrent.TimeUnit;

@TeleOp(name = "DemoBot" , group = "TeleOp")

public class demoBot extends OpMode{
    //MecanumDrive mecanumDrive;
    //org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle armAngle;
   // Extension extension;
    Meccanum meccanum;

    Claws claws;

    //driveTrain

    //Arm Angling
    //double thetaPower;

    //extension
   // double extendPower;

    double frontLeftPower;
    double frontRightPower;
    double backLeftPower;
    double backRightPower;

    boolean executed = false;


    @Override
    public void init() {
        //MecanumDrive = new MecanumDrive(hardwareMap);
        //armAngle = new armAngle(hardwareMap);
        //extension = new Extension(hardwareMap);
        meccanum = new Meccanum(hardwareMap, telemetry);
        claws = new Claws(hardwareMap);


        //mecanumDrive.setZeroPowerBehavior();
       // armAngle.setZeroPowerBehavior();
        //extension.setZeroPowerBehavior();


    }

    @Override
    public void loop() {


        //MecanumDrive Code

        meccanum.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

        /*if (gamepad1.dpad_up){
            meccanum.powerScalingTest();
//            executed = false;
        }

        if (gamepad1.dpad_down){
            meccanum.powerScalingReset();
//            executed = false;
        }*/

        telemetry.addData("left x", gamepad1.left_stick_x);
        telemetry.addData("left y", gamepad1.left_stick_y);
        telemetry.addData("right x", gamepad1.right_stick_x);


        if (gamepad1.left_bumper){
            claws.gripL();
        }if (gamepad1.right_bumper){
            claws.gripR();
        }if (gamepad1.dpad_left){
            claws.releaseL();
        }if (gamepad1.dpad_right){
            claws.releaseR();
        }


        //altering the power values for the front wheels
        /*if(gamepad1.a && !executed){
            //leftFront decrease
            meccanum.decreaseLeftFront();
            executed = true;
        }
        if (gamepad1.b & !executed){
            //rightFront decrease
            meccanum.decreaseRightFront();
            executed = true;
        }
        if(gamepad1.x && !executed){
            //leftFront increase
            meccanum.increaseLeftFront();
            executed = true;
        }
        if (gamepad1.y && !executed){
            //rightFront increase
            meccanum.increaseRightFront();
            executed = true;
        }*/


        //altering the power values for the back wheels
        /*if(gamepad2.a && !executed){
            //leftBack decrease
            meccanum.decreaseLeftBack();
            executed = true;
        }
        if (gamepad2.b && !executed){
            //rightBack decrease
            meccanum.decreaseRightBack();
            executed = true;
        }
        if(gamepad2.x && !executed){
            //leftBack increase
            meccanum.increaseLeftBack();
            executed = true;
        }
        if (gamepad2.y && !executed){
            //rightBack increase
            meccanum.increaseRightBack();
            executed = true;
        }*/

        /*if(gamepad1.a){
            meccanum.testLeftBackForward();
        }
        if (gamepad1.b){
            meccanum.testRightBackForward();
        }
        if(gamepad1.x){
            meccanum.testLeftFrontForward();
        }
        if (gamepad1.y){
            meccanum.testRightFrontForward();
        }*/


        /*//Extension Code
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
        } else armAngle.stop();*/


    }
}
