package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.redAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Mandible;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous(name = "2-3: Red", group = "Autonomous")
public class SetDetectionRed extends LinearOpMode {

    Meccauto meccauto;
    Mandible mandible;

    public void initialize () {
        meccauto = new Meccauto(hardwareMap, telemetry);
        mandible = new Mandible(hardwareMap);

    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        mandible.stoneLevel();
        sleep(300);
        meccauto.move(Meccauto.BACKWARDS, 40, .5);
        mandible.biteMore();
        sleep(200);
        meccauto.move(Meccauto.FORWARDS, 20, .7);
        meccauto.turn(Meccauto.RIGHT, 35, .7);
        mandible.foundLevel();
        sleep(100);
        meccauto.move(Meccauto.BACKWARDS, 60, .7);
        mandible.letGo();
        sleep(300);
        meccauto.move(Meccauto.FORWARDS, 50, .8);
        meccauto.turn(Meccauto.LEFT, 35, .8);
        mandible.stoneLevel();
        sleep(200);
        meccauto.move(Meccauto.BACKWARDS, 20, .5);
        mandible.biteMore();
        sleep(300);
        meccauto.move(Meccauto.FORWARDS, 20, .8);
        meccauto.turn(Meccauto.RIGHT, 35, .8);
        mandible.foundLevel();
        sleep(100);
        meccauto.move(Meccauto.BACKWARDS, 50, .8);
        mandible.letGo();
        sleep(300);
        meccauto.move(Meccauto.FORWARDS, 28, 1);
        sleep(10000);
    }
}
