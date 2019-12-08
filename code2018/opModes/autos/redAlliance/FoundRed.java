package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.redAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

@Autonomous (name = "Red: F", group = "autonomous")
@Disabled
public class FoundRed extends LinearOpMode {

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
        meccauto.turn(Meccauto.RIGHT, 66, .7);
        // *** code to release foundation
        meccauto.move(Meccauto.BACKWARDS, 5, .7);


        meccauto.stay();

    }

}

