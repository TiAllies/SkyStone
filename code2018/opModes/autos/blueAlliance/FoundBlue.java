package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.blueAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Mandible;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;


@Autonomous (name = "Blue: F", group = "autonomous")

public class FoundBlue extends LinearOpMode {

    Meccauto meccauto;
    Mandible mandible;

    public void initialize () {
        meccauto = new Meccauto(hardwareMap, telemetry);
        mandible = new Mandible(hardwareMap);

    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        // ------------------------------------ //
        // Positions us next to the foundation  //
        // ------------------------------------ //
        meccauto.side(Meccauto.RIGHT, 17, .5);
        sleep(100);
        meccauto.move(Meccauto.FORWARDS, 30, 1);
        sleep(100);
        meccauto.turn(Meccauto.RIGHT, 6, 1);
        sleep(100);
        meccauto.move(Meccauto.FORWARDS, 7, 1);
        // ------------------------------------ //
        // Grabs the foundation and pulls back //
        // ------------------------------------ //
        mandible.stoneLevel();
        sleep(1000);
        meccauto.move(Meccauto.BACKWARDS, 49, .4);
        mandible.up();
        sleep(1000);
        meccauto.turn(Meccauto.LEFT, 4, 1);
        meccauto.side(Meccauto.LEFT, 80, .9);
        sleep(50000);

    }

}
