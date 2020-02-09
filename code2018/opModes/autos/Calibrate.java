package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.stackClutch;

@Autonomous(name = "Calibrate", group = "Autonomous")
public class Calibrate extends LinearOpMode {
//    stackClutch stackClutch;

    public void initialize() {
//        stackClutch = new stackClutch(hardwareMap, telemetry);
    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();


        while (opModeIsActive()) {
            double time = getRuntime();
            telemetry.addData("time check?", time);

        }
//        stackClutch.calibrate();
    }
}
