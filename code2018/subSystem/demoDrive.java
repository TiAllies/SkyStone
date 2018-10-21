package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class demoDrive {

    private DcMotor leftDrive;
    private DcMotor rightDrive;


    public demoDrive (HardwareMap hardwareMap) {

        leftDrive = hardwareMap.dcMotor.get("leftMotor");
        rightDrive = hardwareMap.dcMotor.get("rightMotor");
    }

    public void leftSide (double power) {
        leftDrive.setPower(power);
    }

    public void rightSide (double power) {
        rightDrive.setPower(power);
    }

    public void stop () {
        rightDrive.setPower(0);
        leftDrive.setPower(0);
    }
}
