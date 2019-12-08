package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import static android.os.SystemClock.sleep;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Meccauto  {
    private DcMotor motorRightFront;
    private DcMotor motorRightBack;
    private DcMotor motorLeftFront;
    private DcMotor motorLeftBack;
    private Telemetry telemetry;

    double leftFrontVariance = 1;
    double rightFrontVariance = 1;
    double leftBackVariance = 1;
    double rightBackVariance = 1;

    public final static int FORWARDS = 1;
    public final static int BACKWARDS = -1;
    public final static int RIGHT = 1;
    public final static int LEFT = -1;

    private final int DIAMETER = 4;
    private final double CIRCUMFERENCE = DIAMETER*Math.PI;
    private final int TICKS_PER_ROTATION = 370;

    // motors are geared so that is why the ticks are about 1/3 of the normal ticks

    private final double TICKS_PER_INCH = TICKS_PER_ROTATION/CIRCUMFERENCE;

    public Meccauto (HardwareMap hardwareMap, Telemetry telemetry){

        motorLeftFront = hardwareMap.dcMotor.get("leftFront");
        motorRightFront = hardwareMap.dcMotor.get("rightFront");
        motorLeftBack = hardwareMap.dcMotor.get("leftBack");
        motorRightBack = hardwareMap.dcMotor.get("rightBack");

        this.telemetry = telemetry;

        motorRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        setZeroPowerBehavior();
        setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    // ----------------------------------------------------------------------- //
    //  This will set the motors to brake instead of coasting to a stop        //
    // ----------------------------------------------------------------------- //
    private void setZeroPowerBehavior() {
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void stay(){
        motorRightBack.setPower(0);
        motorLeftBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);
    }

    public void setMotorMode (DcMotor.RunMode mode) {
        motorLeftBack.setMode(mode);
        motorLeftFront.setMode(mode);
        motorRightFront.setMode(mode);
        motorRightBack.setMode(mode);
    }

    public void movement (int direction, double power) {
        motorLeftBack.setPower(power * direction);
        motorRightFront.setPower(power * direction);
        motorRightBack.setPower(power * direction);
        motorLeftFront.setPower(power * direction);
    }

    public void sideways (int direction, double power) {
        motorLeftFront.setPower(power * direction);
        motorRightFront.setPower(-power * direction);
        motorLeftBack.setPower(-power * direction);
        motorRightBack.setPower(power * direction);
    }

    public void turning (int direction, double power) {
        motorLeftFront.setPower(power * direction);
        motorRightFront.setPower(-power * direction);
        motorLeftBack.setPower(power * direction);
        motorRightBack.setPower(-power * direction);
    }


    public double convertEncoder (double distance) {
        return (distance*TICKS_PER_INCH);
    }

    public boolean isBusy () {
        return (motorLeftFront.isBusy() && motorLeftBack.isBusy() && motorRightBack.isBusy() && motorRightFront.isBusy());
    }

    public void powerSetting (double power){
        motorLeftBack.setPower(power * leftBackVariance);
        motorRightFront.setPower(power * rightFrontVariance);
        motorRightBack.setPower(power * rightBackVariance);
        motorLeftFront.setPower(power * leftFrontVariance);

        /*motorLeftBack.setPower(power);
        motorRightFront.setPower(power);
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);*/
    }

    public void move (int direction, double distance, double power){
        distance = convertEncoder(distance);
        motorLeftFront.setTargetPosition((motorLeftFront.getCurrentPosition() + (int)(direction*distance)));
        motorRightFront.setTargetPosition((motorRightFront.getCurrentPosition() + (int)(direction*distance)));
        motorLeftBack.setTargetPosition((motorLeftBack.getCurrentPosition() + (int)(direction*distance)));
        motorRightBack.setTargetPosition((motorRightBack.getCurrentPosition() + (int)(direction*distance)));

        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep(100);
        powerSetting(power);

        while (motorLeftFront.isBusy() && motorLeftBack.isBusy() && motorRightBack.isBusy() && motorRightFront.isBusy()){

            telemetry.addData("Left Front Distance", Math.abs(motorLeftFront.getCurrentPosition() - motorLeftFront.getTargetPosition()));
            telemetry.addData("Left Back Distance", Math.abs(motorLeftBack.getCurrentPosition() - motorLeftBack.getTargetPosition()));
            telemetry.addData("Right Front Distance", Math.abs(motorRightFront.getCurrentPosition() - motorRightFront.getTargetPosition()));
            telemetry.addData("Right Back Distance", Math.abs(motorRightBack.getCurrentPosition() - motorRightBack.getTargetPosition()));
            telemetry.update();

        }
        stay();
    }


    public void side (int direction, double distance, double power){
        distance = convertEncoder(distance);
        motorRightBack.setTargetPosition((int)(motorRightBack.getCurrentPosition() - (direction*distance)));
        motorLeftBack.setTargetPosition((int)(motorLeftBack.getCurrentPosition() + (direction*distance)));
        motorRightFront.setTargetPosition((int)(motorRightFront.getCurrentPosition() + (direction*distance)));
        motorLeftFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() - (direction*distance)));


        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);


        sleep(100);
        powerSetting(power);

        while (motorLeftFront.isBusy() && motorLeftBack.isBusy() && motorRightBack.isBusy() && motorRightFront.isBusy()){

            telemetry.addData("Left Front Distance", Math.abs(motorLeftFront.getCurrentPosition() - motorLeftFront.getTargetPosition()));
            telemetry.addData("Left Back Distance", Math.abs(motorLeftBack.getCurrentPosition() - motorLeftBack.getTargetPosition()));
            telemetry.addData("Right Front Distance", Math.abs(motorRightFront.getCurrentPosition() - motorRightFront.getTargetPosition()));
            telemetry.addData("Right Back Distance", Math.abs(motorRightBack.getCurrentPosition() - motorRightBack.getTargetPosition()));
            telemetry.update();
        }
        stay();
    }

    public void turn (int direction, double distance, double power){
        distance = convertEncoder(distance);
        motorRightBack.setTargetPosition((int)(motorRightBack.getCurrentPosition() - (direction*distance)));
        motorLeftBack.setTargetPosition((int)(motorLeftBack.getCurrentPosition() + (direction*distance)));
        motorRightFront.setTargetPosition((int)(motorRightFront.getCurrentPosition() - (direction*distance)));
        motorLeftFront.setTargetPosition((int)(motorLeftFront.getCurrentPosition() + (direction*distance)));


        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);


        sleep(100);
        powerSetting(power);

        while (isBusy()){

            telemetry.addData("Left Front Distance", Math.abs(motorLeftFront.getCurrentPosition() - motorLeftFront.getTargetPosition()));
            telemetry.addData("Left Back Distance", Math.abs(motorLeftBack.getCurrentPosition() - motorLeftBack.getTargetPosition()));
            telemetry.addData("Right Front Distance", Math.abs(motorRightFront.getCurrentPosition() - motorRightFront.getTargetPosition()));
            telemetry.addData("Right Back Distance", Math.abs(motorRightBack.getCurrentPosition() - motorRightBack.getTargetPosition()));
            telemetry.update();
        }
        stay();
    }





}
