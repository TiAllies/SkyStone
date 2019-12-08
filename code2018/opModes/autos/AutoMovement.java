package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claw;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccanum;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous (name = "movementTest", group = "Autonomous")
public class AutoMovement extends LinearOpMode {

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
     /*   meccauto.side(meccauto.RIGHT, 13, .6);
        meccauto.move(meccauto.BACKWARDS, 29, .6);*/

        // --------------------------------------------------- //
        // Grabs and moves the foundation into scorable range  //
        // --------------------------------------------------- //
        meccauto.move(Meccauto.FORWARDS,20,.7);
      /*  meccauto.move(Meccauto.FORWARDS, 30, .7);
        claws.autoRelease();

        // ----------------------------------------------------- //
        // parking underneath the sky-bridge (close to the wall) //
        // ----------------------------------------------------- //
        meccauto.side(meccauto.LEFT, 40,.6);*/

        meccauto.stay();

    }
}
