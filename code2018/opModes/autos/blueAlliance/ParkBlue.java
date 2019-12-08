package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.blueAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous(name = "Blue: P", group = "Autonomous")
public class ParkBlue extends LinearOpMode {
    Meccauto meccauto;

    public void initialize() {
        meccauto = new Meccauto(hardwareMap, telemetry);
    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        // ----------------------------------------------------- //
        // 1. parking underneath the sky-bridge (close to the wall) //
        // ----------------------------------------------------- //
        sleep(2000);
        meccauto.move(meccauto.BACKWARDS, 7, .7);

        meccauto.stay();

    }
}
