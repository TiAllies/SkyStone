package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

public class mecaTele extends MecanumDrive{
    @Override
    public void runOpMode() {
        setMotorLeftFront(hardwareMap.dcMotor.get("leftFront"));
        setMotorRightFront(hardwareMap.dcMotor.get("rightFront"));
        setMotorLeftBack(hardwareMap.dcMotor.get("leftBack"));
        setMotorRightBack(hardwareMap.dcMotor.get("rightBack"));

        waitForStart();
        while (opModeIsActive()){

        }

    }

}
