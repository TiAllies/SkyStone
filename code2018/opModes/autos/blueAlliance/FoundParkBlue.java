package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.blueAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;


@Autonomous(name = "Blue: F + P", group = "Autonomous")
@Disabled
public class FoundParkBlue extends LinearOpMode {

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
        meccauto.move(meccauto.BACKWARDS, 25, .6);
        meccauto.side(meccauto.LEFT, 8, .7);
        meccauto.side(meccauto.LEFT, 8, .7);
        meccauto.side(meccauto.LEFT, 8, .7);
        meccauto.side(meccauto.LEFT, 8, .7);
//        meccauto.move(Meccauto.BACKWARDS, 3,.8);
        sleep(1000);

        // --------------------------------------------------- //
        // Grabs and moves the foundation into scorable range  //
        // --------------------------------------------------- //
        claws.autoGrab();
        sleep(2000);
        meccauto.side(Meccauto.RIGHT, 10, .7);
        meccauto.side(Meccauto.RIGHT, 10, .7);
        meccauto.side(Meccauto.RIGHT, 10, .7);
        meccauto.side(Meccauto.RIGHT, 8, .7);
        meccauto.side(Meccauto.RIGHT, 2, .7);
        sleep(1000);
        claws.autoRelease();
        sleep(1000);
        meccauto.move(Meccauto.FORWARDS, 3, .7);
        sleep(400);

//        meccauto.side(Meccauto.LEFT,15,.7);
        meccauto.move(Meccauto.FORWARDS, 40, .8);
//        meccauto.move(Meccauto.FORWARDS, 3, 1);
        meccauto.move(Meccauto.FORWARDS, 3,.8);

        meccauto.stay();

    }

}
