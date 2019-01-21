package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Linear;

import static android.os.SystemClock.sleep;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

/**
 * Created by Titanium Allies on 11/3/2017.
 */

public class Lift {
    private DcMotor _liftMotor;

    public Lift(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
    }

    private LinearOpMode _linearOpMode;

    public Lift(HardwareMap hardwareMap){
        this (hardwareMap, null);
    }
    public Lift(HardwareMap hardwareMap, LinearOpMode linearOpMode){

        //super(linearOpMode);

        _liftMotor = hardwareMap.dcMotor.get("liftMotor");
        _linearOpMode = linearOpMode;
    }

    //Raise lift
    public void upwards(double power){
        _liftMotor.setPower(power);
    }

    public void setZeroLift(){
        _liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //Lower lift
    public void downwards(double power){
        _liftMotor.setPower(-power);
    }

    public void liftingPower (double power) {
        _liftMotor.setPower(-power);
    }

    public void stop () {
        _liftMotor.setPower(0);
    }

    public void lifing (double power) {
        _liftMotor.setPower(power);
    }


    public void landing () {
        _liftMotor.setPower(1);
        sleep(3800);
        _liftMotor.setPower(0);
    }


    public final static int UP = -1;

    public final static int DOWN = 1;

    protected final double DIAMETER = 0.375; //going to eventually be AxleDiameter + StringWidth
    protected final double AxleDiameter = 0.375;
    protected final double StringWidth = 0.1;
    protected final double CIRCUMFERENCE = DIAMETER*3.14159;
    protected final int TICKS_PER_ROTATION = 1100;
    protected final double TICKS_PER_INCH = TICKS_PER_ROTATION/CIRCUMFERENCE;


    public void setMotorMode (DcMotor.RunMode mode) {
        _liftMotor.setMode(mode);
    }
    public void resetEncoders (){
        _liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public int convertEncoder (int distance) {
        return (int) (distance*TICKS_PER_INCH);
    }


    public void LIFT(int direction, int distance, double power){
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        distance = convertEncoder(distance);
        _liftMotor.setTargetPosition((_liftMotor.getCurrentPosition() - (direction*distance)));

        sleep(100);
        _liftMotor.setPower(power);

        while (_liftMotor.isBusy() && _linearOpMode.opModeIsActive()){

            _linearOpMode.telemetry.addData("Lift distance", Math.abs(_liftMotor.getCurrentPosition() - _liftMotor.getTargetPosition()));
            _linearOpMode.telemetry.update();
            _linearOpMode.idle();
        }
        stop();
    }





}
