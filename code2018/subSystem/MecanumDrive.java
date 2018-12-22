package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;

/**
 * Created by Titanium Allies on 12/28/2016.
 */


public class MecanumDrive {
    public DcMotor _motorRightFront;
    private DcMotor _motorRightBack;
    private DcMotor _motorLeftFront;
    private DcMotor _motorLeftBack;




    public MecanumDrive(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
    }

    private LinearOpMode _linearOpMode;

    public MecanumDrive(HardwareMap hardwareMap){
        this (hardwareMap, null);
    }

    public MecanumDrive(HardwareMap hardwareMap, LinearOpMode linearOpMode){

        //super(linearOpMode);

        _motorRightFront = hardwareMap.dcMotor.get("rightFront");
        _motorRightBack = hardwareMap.dcMotor.get("rightBack");
        _motorLeftFront = hardwareMap.dcMotor.get("leftFront");
        _motorLeftBack = hardwareMap.dcMotor.get("leftBack");

        //_motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
        //_motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        _motorRightBack.setDirection(DcMotor.Direction.REVERSE);
        _motorRightFront.setDirection(DcMotor.Direction.REVERSE);
        _linearOpMode = linearOpMode;
    }

    public void motorRightFront(double power) {
        _motorRightFront.setPower(power);
            }

    public void motorLeftFront(double power)  {
        _motorLeftFront.setPower(power);
        }

    public void motorRightBack (double power) {
        _motorRightBack.setPower(power);
    }

    public void motorLeftBack (double power) {
        _motorLeftBack.setPower(power);
    }

    public void stop (){
        _motorRightBack.setPower(0);
        _motorRightFront.setPower(0);
        _motorLeftFront.setPower(0);
        _motorLeftBack.setPower(0);

        sleep(300);
    }
    public void forwards (double power) {
        _motorLeftBack.setPower(-power);
        _motorLeftFront.setPower(-power);
        _motorRightFront.setPower(-power);
        _motorRightBack.setPower(-power);
    }

    public void backwards (double power) {
        _motorLeftBack.setPower(power);
        _motorLeftFront.setPower(power);
        _motorRightFront.setPower(power);
        _motorRightBack.setPower(power);
    }

    public void turnLeft (double power){
        _motorLeftBack.setPower(power);
        _motorLeftFront.setPower(power);
        _motorRightFront.setPower(-power);
        _motorRightBack.setPower(-power);
    }

    public void isBusy () {
        _motorRightFront.isBusy();
        _motorLeftFront.isBusy();
        _motorRightBack.isBusy();
        _motorLeftBack.isBusy();
    }


    public void turnRight (double power) {
        _motorLeftFront.setPower(-power);
        _motorRightFront.setPower(power);
        _motorLeftBack.setPower(-power);
        _motorRightBack.setPower(power);
    }

    public void setZeroPowerBehavior() {
        _motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    //autonomous



    public final static int FORWARDS = 1;
    public final static int BACKWARDS = -1;
    public final static int RIGHT = 1;
    public final static int LEFT = -1;

    protected final int DIAMETER = 4;
    protected final double CIRCUMFERENCE = DIAMETER*3.14159;
    protected final int TICKS_PER_ROTATION = 1100;
    protected final double TICKS_PER_INCH = TICKS_PER_ROTATION/CIRCUMFERENCE;

    public void setMotorMode (DcMotor.RunMode mode) {
        _motorLeftBack.setMode(mode);
        _motorLeftFront.setMode(mode);
        _motorRightFront.setMode(mode);
        _motorRightBack.setMode(mode);
    }
    public void resetEncoders (){
        _motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _motorLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _motorRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public int convertEncoder (double distance) {
        return (int) (distance*TICKS_PER_INCH);
    }

    //Error somewhere in the following code
    public void move(int direction, int distance, double power){
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        distance = convertEncoder(distance);
        _motorLeftBack.setTargetPosition((_motorLeftBack.getCurrentPosition() - (direction*distance)));
        _motorLeftFront.setTargetPosition((_motorLeftFront.getCurrentPosition() - (direction*distance)));
        // Right Back is the messed up wheel, fixed with switching encoder cords
        _motorRightBack.setTargetPosition((_motorRightBack.getCurrentPosition() - (direction*distance)));
        _motorRightFront.setTargetPosition((_motorRightFront.getCurrentPosition() - (direction*distance)));

        sleep(100);
        _motorLeftBack.setPower(power);
        _motorRightFront.setPower(power);
        _motorRightBack.setPower(power);
        _motorLeftFront.setPower(power);

        while (_motorLeftFront.isBusy() && _motorLeftBack.isBusy() && _motorRightBack.isBusy() && _motorRightFront.isBusy() && _linearOpMode.opModeIsActive()){

            _linearOpMode.telemetry.addData("Left Front Distance", Math.abs(_motorLeftFront.getCurrentPosition() - _motorLeftFront.getTargetPosition()));
            _linearOpMode.telemetry.addData("Left Back Distance", Math.abs(_motorLeftBack.getCurrentPosition() - _motorLeftBack.getTargetPosition()));
            _linearOpMode.telemetry.addData("Right Front Distance", Math.abs(_motorRightFront.getCurrentPosition() - _motorRightFront.getTargetPosition()));
            _linearOpMode.telemetry.addData("Right Back Distance", Math.abs(_motorRightBack.getCurrentPosition() - _motorRightBack.getTargetPosition()));
            _linearOpMode.telemetry.update();
            _linearOpMode.idle();
        }
        stop();
    }


    public void side(int direction, int distance, double power){
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        distance = convertEncoder(distance);
        _motorLeftBack.setTargetPosition((int)(_motorLeftBack.getCurrentPosition() - (direction*distance)));
        _motorLeftFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() + (direction*distance)));
        _motorRightBack.setTargetPosition((int)(_motorRightBack.getCurrentPosition() + (direction*distance)));
        _motorRightFront.setTargetPosition((int)(_motorRightFront.getCurrentPosition() - (direction*distance)));

        sleep(100);
        _motorLeftBack.setPower(power);
        _motorRightFront.setPower(power);
        _motorRightBack.setPower(power);
        _motorLeftFront.setPower(power);

        while (_motorLeftFront.isBusy() && _motorLeftBack.isBusy() && _motorRightBack.isBusy() && _motorRightFront.isBusy() && _linearOpMode.opModeIsActive()){

            _linearOpMode.telemetry.addData("Left Front Distance", Math.abs(_motorLeftFront.getCurrentPosition() - _motorLeftFront.getTargetPosition()));
            _linearOpMode.telemetry.addData("Left Back Distance", Math.abs(_motorLeftBack.getCurrentPosition() - _motorLeftBack.getTargetPosition()));
            _linearOpMode.telemetry.addData("Right Front Distance", Math.abs(_motorRightFront.getCurrentPosition() - _motorRightFront.getTargetPosition()));
            _linearOpMode.telemetry.addData("Right Back Distance", Math.abs(_motorRightBack.getCurrentPosition() - _motorRightBack.getTargetPosition()));
            _linearOpMode.telemetry.update();
            _linearOpMode.idle();
        }
        stop();
    }


    public void turn(int direction, int distance, double power){
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        distance = convertEncoder(distance);
        _motorLeftBack.setTargetPosition((int)(_motorLeftBack.getCurrentPosition() - (direction*distance)));
        _motorLeftFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() - (direction*distance)));
        _motorRightBack.setTargetPosition((int)(_motorRightBack.getCurrentPosition() + (direction*distance)));
        _motorRightFront.setTargetPosition((int)(_motorRightFront.getCurrentPosition() + (direction*distance)));

        sleep(100);
        _motorLeftBack.setPower(power);
        _motorRightFront.setPower(power);
        _motorRightBack.setPower(power);
        _motorLeftFront.setPower(power);

        while (_motorLeftFront.isBusy() && _motorLeftBack.isBusy() && _motorRightBack.isBusy() && _motorRightFront.isBusy() && _linearOpMode.opModeIsActive()){

            _linearOpMode.telemetry.addData("Left Front Distance", Math.abs(_motorLeftFront.getCurrentPosition() - _motorLeftFront.getTargetPosition()));
            _linearOpMode.telemetry.addData("Left Back Distance", Math.abs(_motorLeftBack.getCurrentPosition() - _motorLeftBack.getTargetPosition()));
            _linearOpMode.telemetry.addData("Right Front Distance", Math.abs(_motorRightFront.getCurrentPosition() - _motorRightFront.getTargetPosition()));
            _linearOpMode.telemetry.addData("Right Back Distance", Math.abs(_motorRightBack.getCurrentPosition() - _motorRightBack.getTargetPosition()));
            _linearOpMode.telemetry.update();
            _linearOpMode.idle();
        }
        stop();
    }
     public void diagonal(int dr, int rl, double distance, double power){
         setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
         distance = convertEncoder(distance);



         if (dr == FORWARDS){
             if (rl == RIGHT){

                 _motorLeftFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() + (distance)));
                 _motorRightBack.setTargetPosition((int)(_motorRightBack.getCurrentPosition() + (distance)));
                 _motorRightFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() - (distance)));
                 _motorLeftBack.setTargetPosition((int)(_motorLeftBack.getCurrentPosition() + (distance)));

                 sleep(100);
                 _motorLeftBack.setPower(power);
                 _motorRightFront.setPower(power);
                 _motorRightBack.setPower(power);
                 _motorLeftFront.setPower(power);

         }
             else if (rl == LEFT){

                 _motorLeftFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() - (distance)));
                 _motorRightBack.setTargetPosition((int)(_motorRightBack.getCurrentPosition() + (distance)));
                 _motorRightFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() + (distance)));
                 _motorLeftBack.setTargetPosition((int)(_motorLeftBack.getCurrentPosition() + (distance)));

                 sleep(100);
                 _motorLeftBack.setPower(power);
                 _motorRightFront.setPower(power);
                 _motorRightBack.setPower(power);
                 _motorLeftFront.setPower(power);

             }
         }
         else if (dr == BACKWARDS){
             if (rl == RIGHT){

                 _motorLeftFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() - (distance)));
                 _motorRightBack.setTargetPosition((int)(_motorRightBack.getCurrentPosition() + (distance)));
                 _motorRightFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() - (distance)));
                 _motorLeftBack.setTargetPosition((int)(_motorLeftBack.getCurrentPosition() - (distance)));

                 sleep(100);
                 _motorLeftBack.setPower(power);
                 _motorRightFront.setPower(power);
                 _motorRightBack.setPower(power);
                 _motorLeftFront.setPower(power);


             }
             else if (rl == LEFT){

                 _motorLeftFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() - (distance)));
                 _motorRightBack.setTargetPosition((int)(_motorRightBack.getCurrentPosition() - (distance)));
                 _motorRightFront.setTargetPosition((int)(_motorLeftFront.getCurrentPosition() - (distance)));
                 _motorLeftBack.setTargetPosition((int)(_motorLeftBack.getCurrentPosition() + (distance)));

                 sleep(100);
                 _motorLeftBack.setPower(power);
                 _motorRightFront.setPower(power);
                 _motorRightBack.setPower(power);
                 _motorLeftFront.setPower(power);

             }


         }
         sleep(420);

     }

}
