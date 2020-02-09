package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;


import static android.os.SystemClock.sleep;

public class Clutch {

    // -------------------------------------------------------------------------------------------------------------- //
    // clutches on the robot [SERVOS] | motors controlling clutches [MOTORS] | touch sensors for calibration [SENSORS]//
    // -------------------------------------------------------------------------------------------------------------- //
    private Servo clutchUn;
    private Servo clutchDeux;
    private Servo clutchTrois;
    private Servo clutchQuatre;
    private Servo clutchCinq;
    private Servo clutchSix;

    private DcMotor topSect;
    private DcMotor bottomSect;

    private TouchSensor senseUn;
    private TouchSensor senseDeux;
    private TouchSensor senseTrois;
    private TouchSensor senseQuatre;
    private TouchSensor senseCinq;
    private TouchSensor senseSix;


    // ---------------------------------------------------------------------------------------------------------------------------------------------------- //
    // constant positions for the servos | state of movement of the lead screw [POS = away from bevel gears, NEG = towards bevel gears, NEUTRAL = no motion //
    // ---------------------------------------------------------------------------------------------------------------------------------------------------- //
    private static /*final*/ double POS;
    private static /*final*/ double NEG;
    private static /*final*/ double NEUTRAL;


    // ---------------------------------------------------------------------------------- //
    // Reset/Start  & current positions for clutch boxes 1 through 8 | mm away from the touch sensor //
    // ---------------------------------------------------------------------------------- //

    // Position for starting / initializing
    int startUn = 50;
    int startDeux = 50;
    int startTrois = 50;
    int startQuatre = 50;
    int startCinq = 50;
    int startSix = 50;

    int[] starts = {startUn, startDeux, startTrois, startQuatre, startCinq, startSix};

    // positions for stacking 3rd height
    int thirdUn = 1;
    int thirdDeux = 2;
    int thirdTrois = 3;
    int thirdQuatre = 4;
    int thirdCinq = 5;
    int thirdSix = 6;

    int[] heightThree = {thirdUn, thirdDeux, thirdTrois, thirdQuatre, thirdCinq, thirdSix};



    int currUn;
    int currDeux;
    int currTrois;
    int currQuatre;
    int currCinq;
    int currSix;



    // --------------------------------------------------------------------- //
    // converted to be in array format for easier usage in the methods below //
    // --------------------------------------------------------------------- //
    private Servo[] clutch = {clutchUn, clutchDeux, clutchTrois, clutchQuatre, clutchCinq, clutchSix};
    private Servo[] topRow = {clutchUn, clutchDeux, clutchTrois};
    private Servo[] bottomRow = {clutchQuatre, clutchCinq, clutchSix};

    private TouchSensor[] touch = {senseUn, senseDeux, senseTrois, senseQuatre, senseCinq, senseSix};

    private DcMotor [] sect = {topSect, bottomSect};

    private int reset[] = {startUn, startDeux, startTrois, startQuatre, startCinq, startSix};
    private int startTop[] = {startUn, startDeux, startTrois};
    private int startBottom[] = {startQuatre, startCinq, startSix};

    private int curr[] = {currUn, currDeux, currTrois, currQuatre, currCinq, currSix};
    private int topCurr[] = {currUn, currDeux, currTrois};
    private int bottomCurr[] = {currQuatre, currCinq, currSix};


    // ----------------------------------------------------------------------------------- //
    // conversion from encoder motor ticks to INCH & MM, easier for lead screw calibration //
    // ----------------------------------------------------------------------------------- //
    private final int leadLength = 100; //this is in mm | lead screw is 100mm long
    private final int TICKS_PER_ROTATION = 1100;
    private final double gRatio = 10/1;
    private final double TICKS_PER_INCH = TICKS_PER_ROTATION * (20/gRatio);
    // 20 rotations of the gear attached to the lead screw is 1 inch, so 20 divided by the gear ratio should give the right ratio for ticks per rotation to ticks per inch //

    private final int TICKS_PER_MM = (int)(TICKS_PER_INCH/25.4);



    // --------------------------------------------------------------- //
    // initializational stuff for in the classes and config file stuff //
    // --------------------------------------------------------------- //
    public Clutch (HardwareMap hardwareMap){
        /*clutch = {
                hardwareMap.servo.get("Clutch1"),
        }*/
        clutchUn = hardwareMap.servo.get("Clutch1");
        clutchDeux = hardwareMap.servo.get("Clutch2");
        clutchTrois = hardwareMap.servo.get("Clutch3");
        clutchQuatre = hardwareMap.servo.get("Clutch4");
        clutchCinq = hardwareMap.servo.get("Clutch5");
        clutchSix = hardwareMap.servo.get("Clutch6");

        topSect = hardwareMap.dcMotor.get("LeftSect");
        bottomSect = hardwareMap.dcMotor.get("RightSect");

        senseUn = hardwareMap.touchSensor.get("Sense1");
        senseDeux = hardwareMap.touchSensor.get("Sense2");
        senseTrois = hardwareMap.touchSensor.get("Sense3");
        senseQuatre = hardwareMap.touchSensor.get("Sense4");
        senseCinq = hardwareMap.touchSensor.get("Sense5");
        senseSix = hardwareMap.touchSensor.get("Sense6");

        // ************ INIT STATE ************ //
        servoSetting(NEUTRAL);

        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


    // ---------------------------------------------------- //
    // Method for setting the position of all clutch servos //
    // ---------------------------------------------------- //
    private void servoSetting (double position){
        for (int i = 0; i < 8 ; i++){
            clutch[i].setPosition(position);
        }
    }

    // ------------------------------------------ //
    // methods to control motors and their powers //
    // ------------------------------------------ //
    private void setMotorMode (DcMotor.RunMode mode) {
        sect[0].setMode(mode);
        sect[1].setMode(mode);
    }
    private void halt () {
        sect[0].setPower(0);
        sect[1].setPower(0);
    }
    private void powerSetting (double power) {
        sect[0].setPower(power);
        sect[1].setPower(power);
    }
    private boolean isBusy () {
        return (sect[0].isBusy() || sect[1].isBusy());
    }
    private void setTargPos (int position){
        int targ = position * TICKS_PER_MM;

        sect[0].setTargetPosition(position);
        sect[1].setTargetPosition(position);
    }

    private double convertEncoder (double distance){
        return (distance*TICKS_PER_MM);
    }



    // ---------------------------------------------------------------- //
    // Calibration method to reset arm back to its initialized position //
    // ---------------------------------------------------------------- //
        public void calibrate () {

        servoSetting(POS);
        sleep(500);

        powerSetting(1);

        // continue running the motors as well as checking if touch sensors have been touched until all of them have been //
        while (!touch[0].isPressed() || !touch[1].isPressed() || !touch[2].isPressed() || !touch[3].isPressed() || !touch[4].isPressed() || !touch[5].isPressed() || !touch[6].isPressed() || !touch[7].isPressed()) {
            for (int i = 0; i < touch.length; i++) {
                if (touch[i].isPressed()) {
                    clutch[i].setPosition(NEUTRAL);
                }
            }
        }

            halt();
            setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            sleep(300);

            setTargPos(leadLength);

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
            servoSetting(NEG);
            sleep(300);

            powerSetting(1);

            // while we are moving the clutches back, check if they are in their corresponding start positions and set the clutch to neutral if they reach or go over it//
            while (isBusy()) {

                for (int i = 0; i < 4; i++){
                    if (sect[0].getCurrentPosition() >= startTop[i]){
                        topRow[i].setPosition(NEUTRAL);
                    }
                    if (sect[1].getCurrentPosition() >= startBottom[i]){
                        bottomRow[i].setPosition(NEUTRAL);
                    }
                }
            }

            halt();
            servoSetting(NEUTRAL);
        }



        // -------------------------------------------- //
        // Moving to the desired target location method //
        // -------------------------------------------- //

    public void moveToPosition (int change1 , int change2, int change3, int change4, int change5, int change6){







    }






}
