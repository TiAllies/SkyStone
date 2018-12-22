package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;

@Autonomous (name = "MakerTest", group = "Autonomous")
public class markerDropTest extends LinearOpMode{
    armAngle armAngle;

    public void initialize (){
        armAngle = new armAngle(hardwareMap, this);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        armAngle.DROP();
        armAngle.stop();
        sleep(30000);

    }
}
