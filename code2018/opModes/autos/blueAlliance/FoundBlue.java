package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.blueAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;


@Autonomous (name = "Blue: F", group = "autonomous")
@Disabled
public class FoundBlue extends LinearOpMode {

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
        meccauto.move(Meccauto.FORWARDS, 30, .7);

        // ------------------------------------ //
        // Grabs the foundation and rotates 180 //
        // ------------------------------------ //
        // *** code to grab ***
        meccauto.turn(Meccauto.LEFT, 66, .8);
        sleep(50);
        meccauto.move(Meccauto.FORWARDS, 30, .8);
        // *** code to release grip ***

/*        // --------------------------- //
        // parks under the skybridge   //
        // --------------------------- //
        meccauto.move(Meccauto.BACKWARDS, 8, 1);
        sleep(50);
        meccauto.turn(Meccauto.LEFT, 32, .8);
        sleep(50);
        meccauto.move(Meccauto.FORWARDS, 30, .8);*/

        meccauto.stay();

    }

}
