package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.blueAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous(name = "Blue: P(far)", group = "Autonomous")
public class ParkFarBlue extends LinearOpMode {
    Meccauto meccauto;

    public void initialize() {
        meccauto = new Meccauto(hardwareMap, telemetry);
    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        // ----------------------------------- //
        // 1. driving out to parking distance  //
        // 2. parking under the skybridge(far) //
        // ----------------------------------- //
        meccauto.move(Meccauto.BACKWARDS, 23, .8);
        sleep(50);
        meccauto.turn(Meccauto.LEFT, 32, .8);
        sleep(50);
        meccauto.move(Meccauto.BACKWARDS, 8, .8);
        meccauto.stay();

    }
}
