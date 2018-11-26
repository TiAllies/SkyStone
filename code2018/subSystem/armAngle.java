package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class armAngle {

    private DcMotor Theta;

    public armAngle (HardwareMap hardwareMap) {

        Theta = hardwareMap.dcMotor.get("theta");
    }

    public void armPower (double power) {
        Theta.setPower(.75*power);
    }

    public void stop () {
        Theta.setPower(0);
    }

    public void setZeroPowerBehavior () {
        Theta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
}


/*
for future opmode:

double thetaPower;

thetapower = gamepad2.left_stick_y;

if(gamepad2.left_stick_y > .999) {
   gamepad2.left_stick_y = 1;
}
else armAngle.stop;

armAngle.armPower(thetaPower);



 */
