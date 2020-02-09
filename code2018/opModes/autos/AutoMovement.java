package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claw;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Mandible;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccanum;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous (name = "movementTest", group = "Autonomous")
@Disabled
public class AutoMovement extends LinearOpMode {

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
     /*   meccauto.side(meccauto.RIGHT, 13, .6);
        meccauto.move(meccauto.BACKWARDS, 29, .6);*/

        // --------------------------------------------------- //
        // Grabs and moves the foundation into scorable range  //
        // --------------------------------------------------- //
        meccauto.turn(Meccauto.RIGHT,15,.7);
      /*  meccauto.move(Meccauto.FORWARDS, 30, .7);
        claws.autoRelease();

        // ----------------------------------------------------- //
        // parking underneath the sky-bridge (close to the wall) //
        // ----------------------------------------------------- //
        meccauto.side(meccauto.LEFT, 40,.6);*/

        meccauto.stay();

    }
}
