package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.blueAlliance;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;


@Autonomous(name = "backward test", group = "Autonomous")
@Disabled
public class FoundParkFarBlue extends LinearOpMode {

    Meccauto meccauto;
    Claws claws;

    public void initialize () {
        meccauto = new Meccauto(hardwareMap, telemetry);
        claws = new Claws(hardwareMap);

    }

    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();

        meccauto.move(Meccauto.BACKWARDS, 30, .7);

        meccauto.stay();

    }

}
