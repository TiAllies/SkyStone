package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class tankdrive {
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;

    public tankdrive(HardwareMap hardwareMap){
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
    }
    public void forward(int power) {
        rightBack.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(power);
        leftFront.setPower(power);
    }

    public void backwards (int power) {
        rightBack.setPower(-power);
        rightFront.setPower(-power);
        leftBack.setPower(-power);
        leftFront.setPower(-power);
    }

    public void right (int power) {
        rightBack.setPower(-power);
        rightFront.setPower(-power);
        leftBack.setPower(power);
        leftFront.setPower(power);
    }

    public void left (int power){
        rightBack.setPower(power);
        rightFront.setPower(power);
        leftBack.setPower(-power);
        leftFront.setPower(-power);
    }

    public void stop (){
        rightBack.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        leftFront.setPower(0);
    }

}
