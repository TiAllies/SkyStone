package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Extension {
    private DcMotor Extender;

    public Extension (HardwareMap hardwareMap) {

        Extender = hardwareMap.dcMotor.get("extend");
    }

    public void extend (double power) {

        Extender.setPower(-power);
    }

    public void retract (double power) {

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
}
