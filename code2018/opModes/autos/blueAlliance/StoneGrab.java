package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.blueAlliance;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Mandible;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous(name = "Blue: Stones", group = "Autonomous")
public class StoneGrab extends LinearOpMode {
        Meccauto meccauto;
        Mandible mandible;

    public void initialize () {
        meccauto = new Meccauto(hardwareMap, telemetry);
        mandible = new Mandible(hardwareMap);
    }


    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        sleep(1000);
        mandible.stoneLevel();
        meccauto.move(Meccauto.BACKWARDS, 38, 0.6);
        mandible.biteMore();
        sleep(500);
        meccauto.move(Meccauto.FORWARDS, 6, .8);
        meccauto.turn(Meccauto.LEFT, 38, 0.9);
        sleep(500);
        mandible.foundLevel();
        meccauto.move(Meccauto.BACKWARDS, 72, 0.8);
        sleep(200);
        mandible.stoneLevel();
        mandible.letGo();
        mandible.foundLevel();
        sleep(500);
        meccauto.turn(Meccauto.LEFT, 4, .8);
        meccauto.move(Meccauto.FORWARDS, 30, 0.8);
        /*sleep(500);
        mandible.stoneLevel();
       meccauto.turn(Meccauto.RIGHT,38, 0.9);
       sleep(400);
       meccauto.move(Meccauto.BACKWARDS, 13, 0.7);
        mandible.biteMore();
        sleep(500);
        meccauto.move(Meccauto.FORWARDS, 5, 0.9);
        sleep(200);
        meccauto.turn(Meccauto.LEFT, 33, 1);
        sleep(800);
        mandible.foundLevel();
        meccauto.move(Meccauto.BACKWARDS, 50, 1);
        sleep(500);
        meccauto.turn(Meccauto.LEFT, 3,1);
        sleep(500);
        meccauto.move(Meccauto.BACKWARDS, 41, 1);
        sleep(800);
        mandible.letGo();
        sleep(800);
        meccauto.move(Meccauto.FORWARDS, 33, 1);*/

    }
}
