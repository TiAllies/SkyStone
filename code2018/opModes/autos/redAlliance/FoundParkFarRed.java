package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.redAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;


@Autonomous(name = "Red: F + P(far)", group = "Autonomous")
@Disabled
public class FoundParkFarRed extends LinearOpMode {

    Meccauto meccauto;
    Claws claws;

    public void initialize () {
        meccauto = new Meccauto(hardwareMap, telemetry);
        claws = new Claws(hardwareMap);

    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        // ------------------------------------ //
        // Positions us next to the foundation  //
        // ------------------------------------ //
        meccauto.move(Meccauto.FORWARDS,30,.8);

        // --------------------------------------------------- //
        // Grabs and moves the foundation into scorable range  //
        // --------------------------------------------------- //
        // *** code to grab foundation
        sleep(2000);
        meccauto.turn(Meccauto.RIGHT, 66, .7);
        // *** code to release foundation
        sleep(1000);
        meccauto.move(Meccauto.BACKWARDS, 5, .7);

        // -------------------- //
        // park under skybridge //
        // -------------------- //

        meccauto.turn(meccauto.RIGHT, 33, .8);
        sleep(50);
        meccauto.move(Meccauto.FORWARDS, 35, .8);
        meccauto.stay();

    }

}
