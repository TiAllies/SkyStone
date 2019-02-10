package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;

public class Extension {
    private DcMotor Extender;
    private LinearOpMode _linearOpMode;

    public Extension (LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
    }

    public Extension(HardwareMap hardwareMap){
        this (hardwareMap, null);
    }

    public Extension (HardwareMap hardwareMap, LinearOpMode linearOpMode){

        //super(linearOpMode);

        Extender = hardwareMap.dcMotor.get("Extender");
        _linearOpMode = linearOpMode;
    }

    public void extend (double power) {

        Extender.setPower(-power);
    }

    public void retract (double power) {

        Extender.setPower(power);
    }

    public void movement (double power) {
        Extender.setPower(power);
    }

    public void stop () {
        Extender.setPower(0);
    }

    public void extendingPower (double power) {
        Extender.setPower(power*.75);
    }

    public void setZeroPowerBehavior () {
        Extender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }


    public final static int OUT = 1;

    public final static int IN = -1;

    protected final double DIAMETER = 1.91;
    protected final double CIRCUMFERENCE = DIAMETER*3.14159;
    protected final int TICKS_PER_ROTATION = 1100;
    protected final double TICKS_PER_INCH = TICKS_PER_ROTATION/CIRCUMFERENCE;


    public void setMotorMode (DcMotor.RunMode mode) {
        Extender.setMode(mode);
    }
    public void resetEncoders (){
        Extender.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public int convertEncoder (int distance) {
        return (int) (distance*TICKS_PER_INCH);
    }


    public void Extend(int direction, int distance, double power){
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        distance = convertEncoder(distance);
        Extender.setTargetPosition((Extender.getCurrentPosition() - (direction*distance)));

        sleep(100);
        Extender.setPower(power);

        while (Extender.isBusy() && _linearOpMode.opModeIsActive()){

            _linearOpMode.telemetry.addData("Extended distance", Math.abs(Extender.getCurrentPosition() - Extender.getTargetPosition()));
            _linearOpMode.telemetry.update();
            _linearOpMode.idle();
        }
        stop();
    }
}
