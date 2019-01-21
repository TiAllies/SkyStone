package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.NonLanding;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Extension;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;

@Autonomous(name="NLCorner [M]", group = "Autonomous")
public class NLMarkerCorner extends LinearOpMode {
    MecanumDrive mecanumDrive;
  //  Extension extension;
  //  armAngle armAngle;
    public void initialize () {
        mecanumDrive = new MecanumDrive(hardwareMap, this);
    //    armAngle = new armAngle(hardwareMap, this);
    //    extension = new Extension(hardwareMap, this);
    }
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        mecanumDrive.move(mecanumDrive.FORWARDS, 20, .8);
        //arm stuff
        sleep(20000);

    }
}
