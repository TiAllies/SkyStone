package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.redAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous(name = "Red: P(far)", group = "Autonomous")
@Disabled
public class ParkFarRed extends LinearOpMode {
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
        sleep(1000);
        meccauto.move(Meccauto.FORWARDS, 35, .7);
        sleep(50);
        meccauto.turn(meccauto.RIGHT, 33, .7);
        sleep(50);
        meccauto.move(Meccauto.FORWARDS, 17, .7);



        meccauto.stay();

    }
}
