package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.subs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Titanium Allies on 11/3/2017.
 */

public class Rotator {
    private DcMotor _rotateMotor;

    public Rotator(HardwareMap hardwareMap) {
        _rotateMotor = hardwareMap.dcMotor.get("rotateMotor");

        int position = _rotateMotor.getCurrentPosition();
        _rotateMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //Reset
    public void flipNormal(){
        _rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        _rotateMotor.setTargetPosition(_rotateMotor.getCurrentPosition() - 10);
        _rotateMotor.setPower(1);

    }

    //Reverse block arm
    public void flipParallel(){
        _rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        _rotateMotor.setTargetPosition(_rotateMotor.getCurrentPosition() + 10);
        _rotateMotor.setPower(1);
    }

    //Stop motor rotation
    public void stop(){
        _rotateMotor.setPower(0);
        _rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        _rotateMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void move(double power){
        _rotateMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        _rotateMotor.setPower(power);
    }
}