package code2018.opModes.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import code2018.subSystem.Claw;
import code2018.subSystem.Detection.JewelPusher;
import code2018.subSystem.Lift;
import code2018.subSystem.Rotator;
import TitaniumAllies2017.subsystems.MecanumDrive;

/**
 * Created by Titanium Allies on 11/3/2017.
 */
@TeleOp(name = "Ordinal",group = "TeleOp")

public class Ordinal extends OpMode {
    MecanumDrive mecanumDrive;
    Claw claw;
    Rotator rotator;
    Lift lift;
    JewelPusher jewelPusher;

    //Instantiate variables
    double LEFTFRONT;
    double RIGHTFRONT;
    double LEFTBACK;
    double RIGHTBACK;
    double MOTORPOWER = .75;



    boolean FLIPMOTOR = false;

    @Override
    public void init() {
        mecanumDrive = new MecanumDrive(hardwareMap);
        claw = new Claw(hardwareMap);
        rotator = new Rotator(hardwareMap);
        lift = new Lift(hardwareMap);
        jewelPusher = new JewelPusher(hardwareMap);

    }

    @Override
    public void loop() {

        //Mecanum Drive
        LEFTFRONT = (-(gamepad1.left_stick_y / 4) + (gamepad1.left_stick_x) / 4) + (gamepad1.right_stick_x / 4);
        RIGHTFRONT = (-(gamepad1.left_stick_y / 4) - (gamepad1.left_stick_x) / 4) - (gamepad1.right_stick_x / 4);
        LEFTBACK = ((gamepad1.left_stick_y / 4) + (gamepad1.left_stick_x) / 4) - (gamepad1.right_stick_x / 4);
        RIGHTBACK = ((gamepad1.left_stick_y / 4) - (gamepad1.left_stick_x) / 4) + (gamepad1.right_stick_x / 4);


        //Speeds up robot when not turning
        if (Math.abs(gamepad1.right_stick_x) < .1) {
            LEFTFRONT = LEFTFRONT * 2;
            RIGHTFRONT = RIGHTFRONT * 2;
            LEFTBACK = LEFTBACK * 2;
            RIGHTBACK = RIGHTBACK * 2;
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


        //Claw
        if (gamepad2.y){
            claw.closeTop();
        }
        else {
            claw.stopTop();
        }

        if (gamepad2.b){
            claw.openTop();
        }
        else {
            claw.stopTop();
        }

        if (gamepad2.x){
            claw.closeBot();
        }
        else {
            claw.stopBot();
        }

        if (gamepad2.a){
            claw.openBot();
        }
        else {
            claw.stopBot();
        }

        //Rotator
        /*if (FLIPMOTOR) {
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

        if(gamepad2.dpad_left){
            rotator.move(.16);
        }
        if(gamepad2.dpad_right){
            rotator.move(-.16);
        }
        if(!gamepad2.dpad_left && !gamepad2.dpad_right)
        rotator.stop();


        //Lift
        if(gamepad2.dpad_up) {
            lift.upwards(MOTORPOWER);
        }
        else if(gamepad2.dpad_down) {
            lift.downwards(MOTORPOWER);
        }
        else {
            lift.downwards(0);
        }
    }




}

