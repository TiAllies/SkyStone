package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;

public class armAngle {

    private DcMotor Theta;
    private LinearOpMode _linearOpMode;


    public armAngle(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
    }

    public armAngle(HardwareMap hardwareMap){
        this (hardwareMap, null);
    }

    public armAngle(HardwareMap hardwareMap, LinearOpMode linearOpMode){

        //super(linearOpMode);

        Theta = hardwareMap.dcMotor.get("theta");
        _linearOpMode = linearOpMode;
    }

    public void armPower (double power) {
        Theta.setPower(.40*power);
    }

    public void stop () {
        Theta.setPower(0);
    }

    public void setZeroPowerBehavior () {
        Theta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void DROP () {
        Theta.setPower(-.8);
        sleep(500);
        Theta.setPower(.4);
        sleep(300);
        Theta.setPower(0);
        stop();
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
