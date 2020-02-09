package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.redAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Mandible;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous (name = "Red: F", group = "autonomous")

public class FoundRed extends LinearOpMode {

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
        meccauto.move(Meccauto.FORWARDS, 3, 1);
        meccauto.side(Meccauto.LEFT, 25, .8);
        meccauto.move(Meccauto.FORWARDS, 34, 1);
        sleep(100);
        meccauto.turn(Meccauto.RIGHT, 2, 1);

        // ------------------------------------ //
        // Grabs the foundation and pulls back //
        // ------------------------------------ //
        mandible.stoneLevel();
        sleep(300);
        meccauto.move(Meccauto.BACKWARDS, 52, 1);
        mandible.up();
        sleep(100);
        meccauto.turn(Meccauto.RIGHT, 3, 1);
        sleep(50);
        meccauto.side(Meccauto.RIGHT, 30, .5);
        sleep(50);
        meccauto.turn(Meccauto.RIGHT, 4, 1);
        sleep(50);
        meccauto.side(Meccauto.RIGHT, 40, .5);
        sleep(50);
        meccauto.turn(Meccauto.RIGHT, 4, 1);
        meccauto.move(Meccauto.BACKWARDS, 5, 1);
        sleep(50000);

    }

}

