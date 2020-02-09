package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import static android.os.SystemClock.sleep;

public class stackClutch {
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

    private Telemetry telemetry;


    public void testPos (double position){
        clutchUn.setPosition(position);
    }

    // ---------------------------------------------------------------------------------------------------------------------------------------------------- //
    // constant positions for the servos | state of movement of the lead screw [POS = away from bevel gears, NEG = towards bevel gears, NEUTRAL = no motion //
    // ---------------------------------------------------------------------------------------------------------------------------------------------------- //

    public double POS = .4;
    public double NEG = .58;
    public double NEUTRAL = .49;

    public double far1 = .45;
    public double neutral1 = .52;
    public double close1 = .6;

    public double far2 = .48;
    public double neutral2 = .575;
    public double close2 = .67;

    public double far3 = .34;
    public double neutral3 = .43;
    public double close3 = .52;

    public double far4 = .46;
    public double neutral4 = .55;
    public double close4 = .63;

    public double far5 = .38;
    public double neutral5 = .49;
    public double close5 = .57;

    public double far6 = .58;
    public double neutral6 = .49;
    public double close6 = .4;

    public boolean check1 = false;
    public boolean check2 = false;
    public boolean check3 = false;
    public boolean check4 = false;
    public boolean check5 = false;
    public boolean check6 = false;




    // -------------------------------------------------------------------------------------------------------------------------------------------- //
    // Positions for the arm to go to: to collect the stone, stack at various heights, and to calibrate for fitting in the box during initialization //
    // -------------------------------------------------------------------------------------------------------------------------------------------- //

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

    //positions for collecting a stone from the mandibles
    int collUn = 1;
    int collDeux = 2;
    int collTrois = 3;
    int collQuatre = 4;
    int collCinq = 5;
    int collSix = 6;

    int[] heightThree = {thirdUn, thirdDeux, thirdTrois, thirdQuatre, thirdCinq, thirdSix};

    public void moveThird () {
        int max;
        //setting the targets to the third stone position
        tar1 = thirdUn - currUn;
        tar2 = thirdDeux - currDeux;
        tar3 = thirdTrois - currTrois;
        tar4 = thirdQuatre - currQuatre;
        tar5 = thirdCinq - currCinq;
        tar6 = thirdSix - currSix;


        //setting each clutch's servo position according to how which direction it's lead screw needs to move to reach the desired location
        if (tar1 < leadLength) {
            setUn(tar1);
        }
        if (tar2 < leadLength) {
            setDeux(tar2);
        }
        if (tar3 < leadLength){
            setTrois(tar3);
        }
        if (tar4 < leadLength){
            setQuatre(tar4);
        }
        if (tar5 < leadLength){
            setCinq(tar5);
        }
        if (tar6 < leadLength){
            setSix(tar6);
        }

        //figuring out the highest target distance
        max = Math.max(Math.max(Math.max(tar1, tar2), tar3), Math.max(Math.max(tar4, tar5), tar6));

        //plugging the highest distance changed into the distance for the motor to move to move only the necessary amount
        topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(convertEncoder(max))));
        bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(convertEncoder(max))));

        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep(100);
        setPow(.5);

        while (topSect.isBusy() && bottomSect.isBusy()){
            int distanceTopLeft = (Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
            int distanceBotLeft = (Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));


            telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
            telemetry.addData("bottomMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
            telemetry.update();

            touchFailSafe();

            //once they move the desired amount each clutch will be set to neutral
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar1)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar2)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar3)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar4)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar5)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar6)){
                clutchUn.setPosition(neutral1);
            }
        }
        currUn += tar1;
        currDeux += tar2;
        currTrois+= tar3;
        currQuatre += tar4;
        currCinq += tar5;
        currSix += tar6;
    }
    public void moveCollect () {
        int max;
        //setting the targets to the stone's position
        tar1 = collUn - currUn;
        tar2 = collDeux - currDeux;
        tar3 = collTrois - currTrois;
        tar4 = collQuatre - currQuatre;
        tar5 = collCinq - currCinq;
        tar6 = collSix - currSix;


        //setting each clutch's servo position according to how which direction it's lead screw needs to move to reach the desired location
        if (tar1 < leadLength) {
            setUn(tar1);
        }
        if (tar2 < leadLength) {
            setDeux(tar2);
        }
        if (tar3 < leadLength){
            setTrois(tar3);
        }
        if (tar4 < leadLength){
            setQuatre(tar4);
        }
        if (tar5 < leadLength){
            setCinq(tar5);
        }
        if (tar6 < leadLength){
            setSix(tar6);
        }

        //figuring out the highest target distance
        max = Math.max(Math.max(Math.max(tar1, tar2), tar3), Math.max(Math.max(tar4, tar5), tar6));

        //plugging the highest distance changed into the distance for the motor to move to move only the necessary amount
        topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(convertEncoder(max))));
        bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(convertEncoder(max))));

        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep(100);
        setPow(.5);

        while (topSect.isBusy() && bottomSect.isBusy()){
            int distanceTopLeft = (Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
            int distanceBotLeft = (Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));


            telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
            telemetry.addData("bottomMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
            telemetry.update();

            touchFailSafe();

            //once they move the desired amount each clutch will be set to neutral
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar1)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar2)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar3)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar4)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar5)){
                clutchUn.setPosition(neutral1);
            }
            if (distanceTopLeft/TICKS_PER_MM <= Math.abs(tar6)){
                clutchUn.setPosition(neutral1);
            }
        }
        currUn += tar1;
        currDeux += tar2;
        currTrois+= tar3;
        currQuatre += tar4;
        currCinq += tar5;
        currSix += tar6;
    }


    // -------------------------------------------------------------------------------------- //
    // running values to track the arm's location and change of location throughout the match //
    // -------------------------------------------------------------------------------------- //
    int currUn = 0;
    int currDeux = 0;
    int currTrois = 0;
    int currQuatre = 0;
    int currCinq = 0;
    int currSix = 0;

    int tar1 = 0;
    int tar2 = 0;
    int tar3 = 0;
    int tar4 = 0;
    int tar5 = 0;
    int tar6 = 0;


    // -------------------------------------------- //
    // getters for the current value of each clutch //
    // -------------------------------------------- //

    public int getcurrUn () {
        return (currUn);
    }

    public int getcurrDeux () {
        return (currDeux);
    }

    public int getcurrTrois () {
        return (currTrois);
    }

    public int getcurrQuatre () {
        return (currQuatre);
    }

    public int getcurrCinq () {
        return (currCinq);
    }

    public int getcurrSix () {
        return (currSix);
    }


    // ----------------------------------------------------------------------------- //
    // methods to set the positions of the clutches based on the target change given //
    // ----------------------------------------------------------------------------- //

    public void setUn (int change){
        if (change < 0 && !check1 && !(currUn+tar1 < 0)){
            clutchUn.setPosition(far1);
        }
        if (change > 0 && tar1 < leadLength){
            clutchUn.setPosition(close1);
        }
        if (change == 0){
            clutchUn.setPosition(neutral1);
        }
    }

    public void setDeux (int change){
        if (change < 0 && !check2 && !(currDeux+tar2 < 0)){
            clutchDeux.setPosition(close2);
        }
        if (change > 0 && tar2 < leadLength){
            clutchDeux.setPosition(far2);
        }
        if (change == 0){
            clutchDeux.setPosition(neutral2);
        }
    }

    public void setTrois (int change){
        if (change < 0 && !check3 && !(currTrois+tar3 < 0)){
            clutchTrois.setPosition(far3);
        }
        if (change > 0 && tar3 < leadLength){
            clutchTrois.setPosition(close3);
        }
        if (change == 0){
            clutchTrois.setPosition(neutral3);
        }
    }

    public void setQuatre (int change){
        if (change < 0 && !check4 && !(currQuatre+tar4 < 0)){
            clutchQuatre.setPosition(far4);
        }
        if (change > 0 && tar4 < leadLength){
            clutchQuatre.setPosition(close4);
        }
        if (change == 0){
            clutchQuatre.setPosition(neutral4);
        }
    }

    public void setCinq (int change){
        if (change < 0 && !check5 && !(currCinq+tar5 < 0)){
            clutchCinq.setPosition(close5);
        }
        if (change > 0 && tar5 < leadLength){
            clutchCinq.setPosition(far5);
        }
        if (change == 0){
            clutchCinq.setPosition(neutral5);
        }
    }

    public void setSix (int change){
        if (change < 0 && !check6 && !(currSix+tar6 < 0)){
            clutchSix.setPosition(far6);
        }
        if (change > 0 && tar6 < leadLength){
            clutchSix.setPosition(close6);
        }
        if (change == 0){
            clutchSix.setPosition(neutral6);
        }
    }
    // ------------------------------------------------------ //


    // -------------------------------- //
    // basic methods for setting things //
    // -------------------------------- //

    public void startMatch () {
        currUn = startUn;
        currDeux = startDeux;
        currTrois = startTrois;
        currQuatre = startQuatre;
        currCinq = startCinq;
        currSix = startSix;
    }

    private void setMotorMode (DcMotor.RunMode mode) {
        topSect.setMode(mode);
        bottomSect.setMode(mode);
    }

    public void setPow (double pow) {
        topSect.setPower(pow);
        bottomSect.setPower(pow);
    }
    public void setPowTop (double pow) {
        topSect.setPower(pow);
    }
    public void setPowBottom (double pow) {
        bottomSect.setPower(pow);
    }

    public void zerodrift () {
        topSect.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bottomSect.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }



    // ----------------------------------------------------------------------------------- //
    // conversion from encoder motor ticks to INCH & MM, easier for lead screw calibration //
    // ----------------------------------------------------------------------------------- //
    private final int leadLength = 70; //this is in mm | lead screw is 100mm long
    private final int TICKS_PER_ROTATION = 1100;
    private final double gRatio = 10/1;
    private final double TICKS_PER_INCH = TICKS_PER_ROTATION * (20/gRatio);
    // 20 rotations of the gear attached to the lead screw is 1 inch, so 20 divided by the gear ratio should give the right ratio for ticks per rotation to ticks per inch //

    private final int TICKS_PER_MM = (int)(TICKS_PER_INCH/25.4);

    public double convertEncoder (double distance) {
        return (distance*TICKS_PER_MM);
    }

    // --------------------------------------------------------------- //
    // initializational stuff for in the classes and config file stuff //
    // --------------------------------------------------------------- //
    public stackClutch (HardwareMap hardwareMap, Telemetry telemetry){

        clutchUn = hardwareMap.servo.get("Clutch1");
        clutchDeux = hardwareMap.servo.get("Clutch2");
        clutchTrois = hardwareMap.servo.get("Clutch3");
        clutchQuatre = hardwareMap.servo.get("Clutch4");
        clutchCinq = hardwareMap.servo.get("Clutch5");
        clutchSix = hardwareMap.servo.get("Clutch6");

        topSect = hardwareMap.dcMotor.get("TopMotor");
        bottomSect = hardwareMap.dcMotor.get("BottomMotor");

        senseUn = hardwareMap.touchSensor.get("Sense1");
        senseDeux = hardwareMap.touchSensor.get("Sense2");
        senseTrois = hardwareMap.touchSensor.get("Sense3");
        senseQuatre = hardwareMap.touchSensor.get("Sense4");
        senseCinq = hardwareMap.touchSensor.get("Sense5");
        senseSix = hardwareMap.touchSensor.get("Sense6");

        // ************ INIT STATE ************ //

        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.telemetry = telemetry;
        neutralAll();

    }

    public void moveClutchUp (int clutchNum){
        int distance = 2;
        double move = convertEncoder(distance);

        // moving clutch # 1
        if (clutchNum == 1 ){
            clutchUn.setPosition(far1);
            topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (topSect.isBusy()){

                telemetry.addData("Current pos 1", currUn);
                telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchUn.setPosition(neutral1);
            currUn += distance;
        }

        // moving clutch # 2
        if (clutchNum == 2){
            clutchDeux.setPosition(close2);
            topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (topSect.isBusy()){

                telemetry.addData("Current pos 1", currDeux);
                telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchDeux.setPosition(neutral2);
            currDeux += distance;
        }

        // moving clutch # 3
        if (clutchNum == 3){
            clutchTrois.setPosition(far3);
            topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (topSect.isBusy()){

                telemetry.addData("Current pos 1", currTrois);
                telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchTrois.setPosition(neutral3);
            currTrois += distance;
        }

        if (clutchNum == 4){
            clutchQuatre.setPosition(far4);
            bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowBottom(.3);

            while (topSect.isBusy()){

                telemetry.addData("Current pos 1", currQuatre);
                telemetry.addData("bottomMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchQuatre.setPosition(neutral4);
            currQuatre += distance;
        }

        if (clutchNum == 5){
            clutchCinq.setPosition(close5);
            bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (bottomSect.isBusy()){

                telemetry.addData("Current pos 1", currCinq);
                telemetry.addData("topMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchCinq.setPosition(neutral5);
            currCinq += distance;
        }

        if (clutchNum == 6){
            clutchSix.setPosition(far6);
            bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (bottomSect.isBusy()){

                telemetry.addData("Current pos 1", currSix);
                telemetry.addData("topMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchSix.setPosition(neutral6);
            currSix += distance;
        }
    }

    public void moveClutchDown (int clutchNum){
        int distance = -2;
        double move = convertEncoder(Math.abs(distance));

        //moving clutch # 1
        if (clutchNum == 1 ){
            clutchUn.setPosition(close1);
            topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (topSect.isBusy()){
                telemetry.addData("Current pos 1", currUn);
                telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchUn.setPosition(neutral1);
            currUn += distance;
        }

        //moving clutch # 2
        if (clutchNum == 2 ){
            clutchDeux.setPosition(far2);
            topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (topSect.isBusy()){
                telemetry.addData("Current pos 1", currDeux);
                telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchDeux.setPosition(neutral2);
            currDeux += distance;
        }

        //moving clutch # 3
        if (clutchNum == 3 ){
            clutchTrois.setPosition(close3);
            topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (topSect.isBusy()){
                telemetry.addData("Current pos 1", currTrois);
                telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchTrois.setPosition(neutral3);
            currTrois += distance;
        }

        //moving clutch # 4
        if (clutchNum == 4 ){
            clutchQuatre.setPosition(close4);
            bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (bottomSect.isBusy()){
                telemetry.addData("Current pos 1", currQuatre);
                telemetry.addData("topMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchQuatre.setPosition(neutral4);
            currQuatre += distance;
        }

        //moving clutch # 5
        if (clutchNum == 5 ){
            clutchCinq.setPosition(far5);
            bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (topSect.isBusy()){
                telemetry.addData("Current pos 1", currCinq);
                telemetry.addData("topMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchCinq.setPosition(neutral5);
            currCinq += distance;
        }

        //moving clutch # 6
        if (clutchNum == 6 ){
            clutchSix.setPosition(close6);
            bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(move)));

            setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

            sleep(100);
            setPowTop(.3);

            while (topSect.isBusy()){
                telemetry.addData("Current pos 1", currSix);
                telemetry.addData("topMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
                telemetry.update();
            }
            setPowTop(0);
            clutchSix.setPosition(neutral6);
            currSix += distance;
        }
    }

    public void touchFailSafe () {
        if (senseUn.isPressed()){
            clutchUn.setPosition(neutral1);
            check1 = true;
        }
        if (senseDeux.isPressed()){
            clutchDeux.setPosition(neutral2);
            check2 = true;
        }
        if (senseTrois.isPressed()){
            clutchTrois.setPosition(neutral3);
            check3 = true;
        }
        if (senseQuatre.isPressed()){
            clutchQuatre.setPosition(neutral4);
            check4 = true;
        }
        if (senseCinq.isPressed()){
            clutchCinq.setPosition(neutral5);
            check5 = true;
        }
        if (senseSix.isPressed()){
            clutchSix.setPosition(neutral6);
            check6 = true;
        }
    }

    public void neutralAll () {
        clutchUn.setPosition(neutral1);
        clutchDeux.setPosition(neutral2);
        clutchTrois.setPosition(neutral3);

        clutchQuatre.setPosition(neutral4);
        clutchCinq.setPosition(neutral5);
        clutchSix.setPosition(neutral6);
    }

    public void settingServos (int te) {
        //since the gear changes the direction of the following clutch's gears, they need to swap every other.

        if (te > 1) {

            //bottom row
            clutchUn.setPosition(far1);
            clutchDeux.setPosition(close2);
            clutchTrois.setPosition(far3);

            //top row
            clutchQuatre.setPosition(far4);
            clutchCinq.setPosition(close5);
            clutchSix.setPosition(far4);
        }

        if (te < 1){

            //bottom row
            clutchUn.setPosition(close1);
            clutchDeux.setPosition(far2);
            clutchTrois.setPosition(close3);

            //top row
            clutchQuatre.setPosition(close4);
            clutchCinq.setPosition(far5);
            clutchSix.setPosition(close4);
        }

    }

    public void calibrate () {

        double move = convertEncoder(leadLength);

        settingServos(1);

        topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(move)));
        bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(move)));

        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep(100);
        setPow(.5);

        while (topSect.isBusy() && bottomSect.isBusy()){

            touchFailSafe();

            telemetry.addData("topMotor encoder value: ", Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
            telemetry.addData("top MM left: ", (Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()))/TICKS_PER_MM);
            telemetry.addData("bottomMotor encoder value: ", Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));
            telemetry.addData("bottom MM left: ", (Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()))/TICKS_PER_MM);
            telemetry.update();
        }
        setPowTop(0);
        neutralAll();

        sleep(100);
        settingServos(-1);

        topSect.setTargetPosition((topSect.getCurrentPosition() + (int)(move)));
        bottomSect.setTargetPosition((bottomSect.getCurrentPosition() + (int)(move)));

        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep(100);
        setPow(.5);

        while (topSect.isBusy() && bottomSect.isBusy()){
            int distanceTopLeft = (Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()));
            int distanceBotLeft = (Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()));

            telemetry.addData("top MM left: ", (Math.abs(topSect.getCurrentPosition() - topSect.getTargetPosition()))/TICKS_PER_MM);
            telemetry.addData("bottom MM left: ", (Math.abs(bottomSect.getCurrentPosition() - bottomSect.getTargetPosition()))/TICKS_PER_MM);
            telemetry.update();

            // calibrating top clutches attached to the top motor
            if ((distanceTopLeft/TICKS_PER_MM) <= startUn){
                clutchUn.setPosition(neutral1);
            }
            if ((distanceTopLeft/TICKS_PER_MM) <= startDeux){
                clutchDeux.setPosition(neutral2);
            }
            if ((distanceTopLeft/TICKS_PER_MM) <= startTrois){
                clutchTrois.setPosition(neutral3);
            }

            //calibrating bottom clutches attached to the bottom motor
            if ((distanceBotLeft/TICKS_PER_MM) <= startQuatre){
                clutchQuatre.setPosition(neutral4);
            }
            if ((distanceBotLeft/TICKS_PER_MM) <= startCinq){
                clutchCinq.setPosition(neutral5);
            }
            if ((distanceBotLeft/TICKS_PER_MM) <= startSix){
                clutchSix.setPosition(neutral6);
            }
        }

        setPow(0);
        neutralAll();
    }


    // displays the running values of the current positions of each clutch
    public void telemetyDisplay () {


        telemetry.addData("current position 1:", currUn);
        telemetry.addData("current position 2:", currDeux);
        telemetry.addData("current position 3:", currTrois);
        telemetry.addData("current position 4:", currQuatre);
        telemetry.addData("current position 5:", currCinq);
        telemetry.addData("current position 6:", currSix);

    }



}
