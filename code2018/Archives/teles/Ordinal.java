package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.teles;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claw;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Lift;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;

/**
 * Created by Titanium Allies on 11/3/2017.
 */
@TeleOp(name = "Ordinal",group = "TeleOp")
@Disabled
public class Ordinal extends OpMode {
    MecanumDrive mecanumDrive;
    Claw claw;
    //NonContClaw nonContClaw;
    //Rotator rotator;
    Lift lift;
   // JewelPusher jewelPusher;
    //Suspension suspension;
  //  Continuous continuous;


    //Instantiate variables
    double LEFTFRONT;
    double RIGHTFRONT;
    double LEFTBACK;
    double RIGHTBACK;
    double MOTORPOWER = 1;

    double PosOne = 0, PosTwo = 0;

   // boolean FLIPMOTOR = false;

    @Override
    public void init() {
       // mecanumDrive = new MecanumDrive(hardwareMap);
        claw = new Claw(hardwareMap);
        //rotator = new Rotator(hardwareMap);
        lift = new Lift(hardwareMap);
      //  jewelPusher = new JewelPusher(hardwareMap);
      // suspension = new Suspension(hardwareMap);
      //  continuous = new Continuous(hardwareMap);
        //nonContClaw = new NonContClaw(hardwareMap);


    }

    @Override
    public void loop() {

        //Mecanum Drive

        LEFTFRONT = ((gamepad1.left_stick_y / 4) - (gamepad1.left_stick_x) / 4) - (gamepad1.right_stick_x / 2);
        RIGHTFRONT = ((gamepad1.left_stick_y / 4) + (gamepad1.left_stick_x) / 4) + (gamepad1.right_stick_x / 2);
        LEFTBACK = ((gamepad1.left_stick_y / 4) + (gamepad1.left_stick_x) / 4) - (gamepad1.right_stick_x / 2);
        RIGHTBACK = ((gamepad1.left_stick_y / 4) - (gamepad1.left_stick_x) / 4) + (gamepad1.right_stick_x / 2);


        //Speeds up robot when not turning
        if (Math.abs(gamepad1.right_stick_x) < .1) {
            LEFTFRONT = LEFTFRONT * 3;
            RIGHTFRONT = RIGHTFRONT * 3;
            LEFTBACK = LEFTBACK * 3;
            RIGHTBACK = RIGHTBACK * 3;
        }

        //Clips motor powers
        LEFTBACK = Range.clip(LEFTBACK, -1, 1);
        RIGHTBACK = Range.clip(RIGHTBACK, -1, 1);
        LEFTFRONT = Range.clip(LEFTFRONT, -1, 1);
        RIGHTFRONT = Range.clip(RIGHTFRONT, -1, 1);


        //Normalises individual joystick values
        if (gamepad1.left_stick_x > .999) {
            gamepad1.left_stick_x = 1;
        }
        if (gamepad1.left_stick_y > .999) {
            gamepad1.left_stick_y = 1;
        }
        if (gamepad1.right_stick_x > .999) {
            gamepad1.right_stick_x = 1;
        }

        //Sets motor power
        mecanumDrive.motorLeftBack(LEFTBACK);
        mecanumDrive.motorLeftFront(LEFTFRONT);
        mecanumDrive.motorRightBack(RIGHTBACK);
        mecanumDrive.motorRightFront(RIGHTFRONT);


        //claw
     /*   if (gamepad2.x){
        continuous.open();}

        if (gamepad2.y){
            continuous.close();}*/


        //Jewel pushers
     //   if (gamepad2.right_bumper){
      //      jewelPusher.RaiseOne();
      //  }
      //  if (gamepad2.left_bumper){
      //      jewelPusher.LowerTwo();
      //  }


      /*  //suspension
        if (gamepad1.left_bumper){
            suspension.upsieDoodleDoo();
        }*/


        //Claw
        if (gamepad2.b/* && PosOne < 4500*/){
            claw.openOne();
            //PosOne += .05;
        } else if (/*PosOne > 0 && !gamepad2.b*/ gamepad2.a) {
            claw.closeOne();
            /*PosOne -= .05;*/
        } else
            claw.stopOne();

        if (gamepad2.x/* && PosTwo < 15000*/){
            claw.openTwo();
            //PosTwo += .05;
        } else if (gamepad2.y/*PosTwo > 0 && !gamepad2.x*/){
            claw.closeTwo();
            //PosTwo -= .05;
        } else
            claw.stopTwo();

        /*if (gamepad2.y && PosOne < 15000){
            claw.closeOne();
            PosTwo += .05;
        } else if (PosOne > 0 && !gamepad2.y){
            claw.openOne();
            PosTwo -= .05;
        } else
            claw.stopOne();*/

       /* if (gamepad2.a && PosTwo < 15000){
            claw.closeTwo();
            PosTwo += .05;
        } else if (PosTwo > 0 && !gamepad2.x){
            claw.openTwo();
            PosTwo -= .05;
        } else
            claw.stopTwo();*/
/*
        if(gamepad2.b){
            ncc.closeNCBot();
        }
        if(gamepad2.a){
            ncc.openNCBot();
        }
        if(gamepad2.x){
            ncc.closeNCTop();
        }
        if(gamepad2.y){
            ncc.openNCTop();
        }
*/

     /*   if(gamepad2.dpad_left){
            rotator.move(.16);
        }
        if(gamepad2.dpad_right){
            rotator.move(-.16);
        }
        if(!gamepad2.dpad_left && !gamepad2.dpad_right)
        rotator.stop();*/


        //Lift

        if(gamepad2.dpad_up) {
            lift.upwards(-MOTORPOWER);
        }
        else if(gamepad2.dpad_down) {
            lift.downwards(-MOTORPOWER);
        }
        else {
            lift.downwards(0);
            lift.setZeroLift();
        }



    }


    }

       /* if(FLIPMOTOR){
            if(gamepad1.a){
                for(int i = 0; i <=48500; i++){
                    rotator.move(.55);
                }
                rotator.stop();
                FLIPMOTOR = false;
            }
        }
        if(!FLIPMOTOR){
            if(gamepad1.b){
                for(int i = 0; i <=48500; i++){
                    rotator.move(-.55);
                }
                rotator.stop();
                FLIPMOTOR = true;
            }
        }*/

       /* //Rotator
        if (FLIPMOTOR) {
            if (gamepad1.a) {
                rotator.flipParallel();
                FLIPMOTOR = false;
            }
        }
        if (!FLIPMOTOR) {
            if (gamepad1.b) {
                rotator.flipNormal();
                FLIPMOTOR = true;
            }
        }*/