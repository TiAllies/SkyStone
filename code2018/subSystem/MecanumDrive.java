package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;

/**
 * Created by Titanium Allies on 12/28/2016.
 */


public abstract class MecanumDrive extends LinearOpMode{
    private DcMotor motorRightFront;
    private DcMotor motorRightBack;
    private DcMotor motorLeftFront;
    private DcMotor motorLeftBack;

    abstract public void runOpMode();

    public void setMotorRightFront(DcMotor motorRightFront) {
        this.motorRightFront = motorRightFront;
    }

    public void setMotorRightBack(DcMotor motorRightBack) {
        this.motorRightBack = motorRightBack;
    }

    public void setMotorLeftFront(DcMotor motorLeftFront) {
        this.motorLeftFront = motorLeftFront;
    }

    public void setMotorLeftBack(DcMotor motorLeftBack) {
        this.motorLeftBack = motorLeftBack;
    }

    public void motorRightFront(double power) {
        motorRightFront.setPower(power);
            }

    public void motorLeftFront(double power)  {
        motorLeftFront.setPower(power);
        }

    public void motorRightBack (double power) {
        motorRightBack.setPower(power);
    }

    public void motorLeftBack (double power) {
        motorLeftBack.setPower(power);
    }

    public void halt (){
        motorRightBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
        motorLeftBack.setPower(0);

//        sleep(300);
    }
    public void vector (double power) {
        motorLeftBack.setPower(power);
        motorLeftFront.setPower(power);
        motorRightFront.setPower(power);
        motorRightBack.setPower(power);
    }


    public void angling (double power){
        motorLeftBack.setPower(-power);
        motorLeftFront.setPower(-power);
        motorRightFront.setPower(power);
        motorRightBack.setPower(power);
    }



    public void strafe (double power) {
        motorLeftBack.setPower(power);
        motorLeftFront.setPower(-power);
        motorRightFront.setPower(power);
        motorRightBack.setPower(-power);
    }


    public boolean isBusy () {
       return motorRightFront.isBusy() &&
        motorLeftFront.isBusy() &&
        motorRightBack.isBusy() &&
        motorLeftBack.isBusy();
    }


    public void setZeroPowerBehavior() {
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


//    //autonomous
//
//
//
//    public final static int FORWARDS = 1;
//    public final static int BACKWARDS = -1;
//    public final static int RIGHT = 1;
//    public final static int LEFT = -1;
//
//    protected final int DIAMETER = 4;
//    protected final double CIRCUMFERENCE = DIAMETER*3.14159;
//    protected final int TICKS_PER_ROTATION = 1100;
//    protected final double TICKS_PER_INCH = TICKS_PER_ROTATION/CIRCUMFERENCE;
//
//    public void setMotorMode (DcMotor.RunMode mode) {
//        motorLeftBack.setMode(mode);
//        motorLeftFront.setMode(mode);
//        motorRightFront.setMode(mode);
//        motorRightBack.setMode(mode);
//    }
//    public void resetEncoders (){
//        motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motorLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motorRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//    }
//
//    public int convertEncoder (double distance) {
//        return (int) (distance*TICKS_PER_INCH);
//    }
//
//    //Error somewhere in the following code
//    public void move(int direction, int distance, double power){
//        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
//        distance = convertEncoder(distance);
//        motorLeftBack.setTargetPosition((motorLeftBack.getCurrentPosition() - (direction*distance)));
//        motorLeftFront.setTargetPosition((motorLeftFront.getCurrentPosition() - (direction*distance)));
//        // Right Back is the messed up wheel, fixed with switching encoder cords
//        motorRightBack.setTargetPosition((motorRightBack.getCurrentPosition() - (direction*distance)));
//        motorRightFront.setTargetPosition((motorRightFront.getCurrentPosition() - (direction*distance)));
//
//        sleep(50);
//        motorLeftBack.setPower(power);
//        motorRightFront.setPower(power);
//        motorRightBack.setPower(power);
//        motorLeftFront.setPower(power);
//
//        while (motorLeftFront.isBusy() && motorLeftBack.isBusy() && motorRightBack.isBusy() && motorRightFront.isBusy() && _linearOpMode.opModeIsActive()){
//
//            _linearOpMode.telemetry.addData("Left Front Distance", Math.abs(motorLeftFront.getCurrentPosition() - motorLeftFront.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Left Back Distance", Math.abs(motorLeftBack.getCurrentPosition() - motorLeftBack.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Right Front Distance", Math.abs(motorRightFront.getCurrentPosition() - motorRightFront.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Right Back Distance", Math.abs(motorRightBack.getCurrentPosition() - motorRightBack.getTargetPosition()));
//            _linearOpMode.telemetry.update();
//            _linearOpMode.idle();
//        }
//        stop();
//    }
//
//
//    public void side(int direction, int distance, double power){
//        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
//        distance = convertEncoder(distance);
//        motorLeftBack.setTargetPosition((int)(motorLeftBack.getCurrentPosition() - (direction*distance)));
//        motorLeftFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() + (direction*distance)));
//        motorRightBack.setTargetPosition((int)(motorRightBack.getCurrentPosition() + (direction*distance)));
//        motorRightFront.setTargetPosition((int)(motorRightFront.getCurrentPosition() - (direction*distance)));
//
//        sleep(50);
//        motorLeftBack.setPower(power);
//        motorRightFront.setPower(power);
//        motorRightBack.setPower(power);
//        motorLeftFront.setPower(power);
//
//        while (motorLeftFront.isBusy() && motorLeftBack.isBusy() && motorRightBack.isBusy() && motorRightFront.isBusy() && _linearOpMode.opModeIsActive()){
//
//            _linearOpMode.telemetry.addData("Left Front Distance", Math.abs(motorLeftFront.getCurrentPosition() - motorLeftFront.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Left Back Distance", Math.abs(motorLeftBack.getCurrentPosition() - motorLeftBack.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Right Front Distance", Math.abs(motorRightFront.getCurrentPosition() - motorRightFront.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Right Back Distance", Math.abs(motorRightBack.getCurrentPosition() - motorRightBack.getTargetPosition()));
//            _linearOpMode.telemetry.update();
//            _linearOpMode.idle();
//        }
//        stop();
//    }
//
//
//    public void turn(int direction, int distance, double power){
//        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
//        distance = convertEncoder(distance);
//        motorLeftBack.setTargetPosition((int)(motorLeftBack.getCurrentPosition() - (direction*distance)));
//        motorLeftFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() - (direction*distance)));
//        motorRightBack.setTargetPosition((int)(motorRightBack.getCurrentPosition() + (direction*distance)));
//        motorRightFront.setTargetPosition((int)(motorRightFront.getCurrentPosition() + (direction*distance)));
//
//        sleep(50);
//        motorLeftBack.setPower(power);
//        motorRightFront.setPower(power);
//        motorRightBack.setPower(power);
//        motorLeftFront.setPower(power);
//
//        while (motorLeftFront.isBusy() && motorLeftBack.isBusy() && motorRightBack.isBusy() && motorRightFront.isBusy() && _linearOpMode.opModeIsActive()){
//
//            _linearOpMode.telemetry.addData("Left Front Distance", Math.abs(motorLeftFront.getCurrentPosition() - motorLeftFront.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Left Back Distance", Math.abs(motorLeftBack.getCurrentPosition() - motorLeftBack.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Right Front Distance", Math.abs(motorRightFront.getCurrentPosition() - motorRightFront.getTargetPosition()));
//            _linearOpMode.telemetry.addData("Right Back Distance", Math.abs(motorRightBack.getCurrentPosition() - motorRightBack.getTargetPosition()));
//            _linearOpMode.telemetry.update();
//            _linearOpMode.idle();
//        }
//        stop();
//    }
//     public void diagonal(int dr, int rl, double distance, double power){
//         setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
//         distance = convertEncoder(distance);
//
//
//
//         if (dr == FORWARDS){
//             if (rl == RIGHT){
//
//                 motorLeftFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() + (distance)));
//                 motorRightBack.setTargetPosition((int)(motorRightBack.getCurrentPosition() + (distance)));
//                 motorRightFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() - (distance)));
//                 motorLeftBack.setTargetPosition((int)(motorLeftBack.getCurrentPosition() + (distance)));
//
//                 sleep(100);
//                 motorLeftBack.setPower(power);
//                 motorRightFront.setPower(power);
//                 motorRightBack.setPower(power);
//                 motorLeftFront.setPower(power);
//
//         }
//             else if (rl == LEFT){
//
//                 motorLeftFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() - (distance)));
//                 motorRightBack.setTargetPosition((int)(motorRightBack.getCurrentPosition() + (distance)));
//                 motorRightFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() + (distance)));
//                 motorLeftBack.setTargetPosition((int)(motorLeftBack.getCurrentPosition() + (distance)));
//
//                 sleep(100);
//                 motorLeftBack.setPower(power);
//                 motorRightFront.setPower(power);
//                 motorRightBack.setPower(power);
//                 motorLeftFront.setPower(power);
//
//             }
//         }
//         else if (dr == BACKWARDS){
//             if (rl == RIGHT){
//
//                 motorLeftFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() - (distance)));
//                 motorRightBack.setTargetPosition((int)(motorRightBack.getCurrentPosition() + (distance)));
//                 motorRightFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() - (distance)));
//                 motorLeftBack.setTargetPosition((int)(motorLeftBack.getCurrentPosition() - (distance)));
//
//                 sleep(100);
//                 motorLeftBack.setPower(power);
//                 motorRightFront.setPower(power);
//                 motorRightBack.setPower(power);
//                 motorLeftFront.setPower(power);
//
//
//             }
//             else if (rl == LEFT){
//
//                 motorLeftFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() - (distance)));
//                 motorRightBack.setTargetPosition((int)(motorRightBack.getCurrentPosition() - (distance)));
//                 motorRightFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() - (distance)));
//                 motorLeftBack.setTargetPosition((int)(motorLeftBack.getCurrentPosition() + (distance)));
//
//                 sleep(100);
//                 motorLeftBack.setPower(power);
//                 motorRightFront.setPower(power);
//                 motorRightBack.setPower(power);
//                 motorLeftFront.setPower(power);
//
//             }
//
//
//         }
//         sleep(420);
//
//     }

}
