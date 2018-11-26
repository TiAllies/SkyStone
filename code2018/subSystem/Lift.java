package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.BasicOpMode_Linear;

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
}
