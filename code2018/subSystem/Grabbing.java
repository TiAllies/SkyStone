package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Grabbing {
    private DcMotor Grabber;

    public Grabbing(HardwareMap hardwareMap) {
        Grabber = hardwareMap.dcMotor.get("grabber");
    }

    public void grabPower (double power) {
        Grabber.setPower(.3*power);
    }

    public void Grab (double power) { Grabber.setPower(.3*power);
    }

    public void Release (double power) {
        Grabber.setPower(.3*-power);
    }

    public void stop (double power) {
        Grabber.setPower(0);
    }

    public void setZeroPowerBehavior () {
        Grabber.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}
