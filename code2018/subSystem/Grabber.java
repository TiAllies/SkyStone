package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;
import static java.lang.Math.abs;

public class Grabber {
    public DcMotor flexMotor;





    public Grabber(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
    }

    private LinearOpMode _linearOpMode;

    public Grabber(HardwareMap hardwareMap){
        this (hardwareMap, null);
    }

    public Grabber(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        flexMotor = hardwareMap.dcMotor.get("Grabber");
    }
    public void motorRightFront(double power) {
        flexMotor.setPower(power);
    }

    public void stop () {
        flexMotor.setPower(0);
    }


    public void spin (double power) {
        flexMotor.setPower(power);
    }

    public void setZeroPowerBehavior() {
        flexMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }



    // AUTO STUFF

    public final static double FORWARDS = .1;
    public final static double BACKWARDS = -.1;


    public void setMotorMode (DcMotor.RunMode mode) {
        flexMotor.setMode(mode);
    }

    public void resetCube (){
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (flexMotor.getCurrentPosition()-(flexMotor.getCurrentPosition()-1100) > -21 && flexMotor.getCurrentPosition()-(flexMotor.getCurrentPosition()-1100) < 21){
            flexMotor.setPower(0);
        } else if (flexMotor.getCurrentPosition()-(flexMotor.getCurrentPosition()-1100)< -21){
            flexMotor.setTargetPosition(0);

            sleep(100);

            flexMotor.setPower(FORWARDS);
            stop();
        }else if (flexMotor.getCurrentPosition()-(flexMotor.getCurrentPosition()-1100) > 21) {
            flexMotor.setTargetPosition(0);

            sleep(100);

            flexMotor.setPower(BACKWARDS);
            stop();
        }
    }

    public void resetSphere () {
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);

        if (flexMotor.getCurrentPosition()-(flexMotor.getCurrentPosition()-1100) > 204 && flexMotor.getCurrentPosition()-(flexMotor.getCurrentPosition()-1100) < 246){
            flexMotor.setPower(0);
        } else if (flexMotor.getCurrentPosition()-(flexMotor.getCurrentPosition()-1100) < 204){
            flexMotor.setTargetPosition(225);

            sleep(100);

            flexMotor.setPower(FORWARDS);
            stop();
        } else if (flexMotor.getCurrentPosition()-(flexMotor.getCurrentPosition()-1100) > 246) {
            flexMotor.setTargetPosition(225);

            sleep(100);

            flexMotor.setPower(BACKWARDS);
            stop();
        }
    }

}
