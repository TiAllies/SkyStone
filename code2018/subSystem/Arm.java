package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

    // each segment has a servo that allows it to move in the X-plane and a servo to move it in the Y-plane.
    //This allows the segment to move in the Z-plane for 3D motion

    private Servo serSeg1x;
    private Servo serSeg1y;
    private Servo serSeg2x;
    private Servo serSeg2y;
/*    private Servo serSeg3x;
    private Servo serSeg3y;
    private Servo serSeg4x;
    private Servo serSeg4y;*/

    private DcMotor mot1;
    private DcMotor mot2;
//    private DcMotor mot3;


    //Refers to the direction the cables are being directed.
    //Init is the neutral position where cables are not being moved.

    public double pull = 0.6;
    public double init = 0.5;
    public double push = 0.4;


    public Arm (HardwareMap hardwareMap){

        serSeg1x = hardwareMap.servo.get("Seg1X");
        serSeg1x.setPosition(init-0.14);
        serSeg1y = hardwareMap.servo.get("Seg1Y");
        serSeg1y.setPosition(init);
//
        serSeg2x = hardwareMap.servo.get("Seg2X");
        serSeg2x.setPosition(init);
        serSeg2y = hardwareMap.servo.get("Seg2Y");
        serSeg2y.setPosition(init);

        /*serSeg3x = hardwareMap.servo.get("Seg3X");
        serSeg3x.setPosition(init);
        serSeg3y = hardwareMap.servo.get("Seg3Y");
        serSeg3y.setPosition(init);

        serSeg4x = hardwareMap.servo.get("Seg4X");
        serSeg4x.setPosition(init);
        serSeg4y = hardwareMap.servo.get("Seg4Y");
        serSeg4y.setPosition(init);*/

        mot1 = hardwareMap.dcMotor.get("mot1");
        mot2 = hardwareMap.dcMotor.get("mot2");
//        mot3 = hardwareMap.dcMotor.get("mot3");
    }


    //positions for moving the first segment
    public void seg1LeftX () {
        serSeg1x.setPosition(push-0.1);
    }
    public void seg1RightX () {
        serSeg1x.setPosition(pull);
    }
    public void seg1ForY () {
        serSeg1y.setPosition(pull);
    }
    public void seg1BackY () {
        serSeg1y.setPosition(push);
    }

    //positions for moving the second segment
    public void seg2LeftX () {
        serSeg2x.setPosition(push-0.1);
    }
    public void seg2RightX () {
        serSeg2x.setPosition(pull+0.1);
    }
    public void seg2ForY () {
        serSeg2y.setPosition(pull+0.1);
    }
    public void seg2BackY () {
        serSeg2y.setPosition(push-0.3);
    }

   /* //positions for moving the third segment
    public void seg3LeftX () {
        serSeg3x.setPosition(push);
    }
    public void seg3RightX () {
        serSeg3x.setPosition(pull);
    }
    public void seg3ForY () {
        serSeg3y.setPosition(pull);
    }
    public void seg3BackY () {
        serSeg3y.setPosition(push);
    }

    //positions for moving the fourth segment
    public void seg4LeftX () {
        serSeg4x.setPosition(push);
    }
    public void seg4RightX () {
        serSeg4x.setPosition(pull);
    }
    public void seg4ForY () {
        serSeg4y.setPosition(pull);
    }
    public void seg4BackY () {
        serSeg4y.setPosition(push);
    }*/


    // neutral positions for each arms planes
    public void seg1YHalt () {
        serSeg1y.setPosition(init);
    }
    public void seg1XHalt () {
        serSeg1x.setPosition(init);
    }

    public void seg2YHalt () {
        serSeg2y.setPosition(init);
    }
    public void seg2XHalt () {
        serSeg2x.setPosition(init);
    }

    /*public void seg3YHalt () {
        serSeg3y.setPosition(init);
    }
    public void seg3XHalt () {
        serSeg3x.setPosition(init);
    }

    public void stop4XEnd (){
        serSeg4x.setPosition(init);
    }
    public void stop4YEnd () {
        serSeg4y.setPosition(init);
    }*/


    //setting the power of all motors
    public void movement (double power) {
        mot1.setPower(power);
        mot2.setPower(power);
//        mot3.setPower(power);
    }

    public void halt (){
        mot1.setPower(0);
        mot2.setPower(0);
//        mot3.setPower(0);
    }

    public void halt1 () {
        mot1.setPower(0);
    }

    public void halt2 () {
        mot2.setPower(0);
    }

    //sets the power of the motor moving the first segment.
    public void seg1Move (double power) {
        mot1.setPower(power);
    }

    //sets the power of the motor moving the second segment.
    public void seg2Move (double power) {
        mot2.setPower(power);
    }

    // -------------------------------------------------- //
    // methods for moving it during Teleop (Qualifier #2) //
    // -------------------------------------------------- //
    public void moveSeg1X (double xAxis, double power) {
        if (Math.abs(xAxis) > 0.1){
            seg1Move(power);

            if (xAxis > 0.1){
                seg1RightX();

            } else if (xAxis < -0.1){
                seg1LeftX();
            }

        } else if (Math.abs(xAxis) < 0.1){
            seg1XHalt();
        }

    }

    public void moveSeg1Y (double yAxis, double power) {
        if (Math.abs(yAxis) > 0.1){
            seg1Move(-power);

            if (yAxis > 0.1){
                seg1ForY();

            } else if (yAxis < -0.1){
                seg1BackY();

            }
        } else if (Math.abs(yAxis) < 0.1){
            seg1YHalt();
        }
    }

    public void moveSeg2X (double xAxis, double power) {
        if (Math.abs(xAxis) > 0.1){
            if (xAxis > 0.1){
                seg2RightX();

            } else if (xAxis < -0.1){
                seg2LeftX();
            }
            seg2Move(power);
        } else if (Math.abs(xAxis) < 0.1){
            seg2XHalt();
        }
    }

    public void moveSeg2Y (double yAxis, double power) {
        if (Math.abs(yAxis) > 0.1){
            seg2Move(-power);

            if (yAxis > 0.1){
                seg2ForY();

            } else if (yAxis < -0.1){
                seg2BackY();
            }

        } else if (Math.abs(yAxis) < 0.1){
            seg2YHalt();
        }
    }







}
