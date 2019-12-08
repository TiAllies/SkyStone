package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.redAlliance;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Mandible;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous (name = "Red: Stones", group = "Autonomous")
public class StoneObtain extends LinearOpMode {
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
        meccauto.move(Meccauto.BACKWARDS, 38, 0.4);
        sleep(400);
        mandible.bite();
        sleep(1000);
        meccauto.move(Meccauto.FORWARDS, 7, 0.6);
        meccauto.turn(Meccauto.RIGHT, 35, 0.9);
        sleep(1000);
        mandible.foundLevel();
        meccauto.move(Meccauto.BACKWARDS, 70, 0.9);
        sleep(200);
        mandible.stoneLevel();
        sleep(500);
        mandible.letGo();
        sleep(500);
        mandible.foundLevel();
        sleep(1000);
        meccauto.turn(Meccauto.LEFT, 2, 1);
        sleep(1000);
        meccauto.move(Meccauto.FORWARDS, 30, 1);
    }
}
