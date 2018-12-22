package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;

@Autonomous  (name = "turnTest" , group = "Autonomous")
public class turnTest extends LinearOpMode{
    MecanumDrive mecanumDrive;

    public void initialize (){
        mecanumDrive = new MecanumDrive(hardwareMap, this);
    }
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            mecanumDrive.turn(mecanumDrive.RIGHT, 24, .3);
        }
    }
}
