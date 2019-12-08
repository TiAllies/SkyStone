package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Craw {

    private DcMotor grabMotor;

    public Craw (HardwareMap hardwareMap) {

        grabMotor = hardwareMap.dcMotor.get("grabber");

        setZeroPowerBehavior();

    }

    private void setZeroPowerBehavior() {
        grabMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void grab (double power) {
        grabMotor.setPower(power);
    }

    public void release (double power) {
        grabMotor.setPower(-power);
    }

    public void halt () {
        grabMotor.setPower(0);
    }

}
