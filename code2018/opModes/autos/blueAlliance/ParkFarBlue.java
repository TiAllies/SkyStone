package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.blueAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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
        meccauto.side(Meccauto.LEFT, 12, .8);
        sleep(100);
        meccauto.move(Meccauto.FORWARDS, 35, .3);
        sleep(50);
        meccauto.stay();

    }
}
