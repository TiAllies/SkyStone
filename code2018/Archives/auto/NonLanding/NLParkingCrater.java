package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.auto.NonLanding;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Extension;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;
@Disabled
@Autonomous (name="NLCrater [P]", group = "Autonomous")
public class NLParkingCrater extends LinearOpMode {
    MecanumDrive mecanumDrive;
    Extension extension;
    armAngle armAngle;
    public void initialize () {
        //mecanumDrive = new MecanumDrive(hardwareMap);
        armAngle = new armAngle(hardwareMap, this);
        extension = new Extension(hardwareMap, this);
    }
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        extension.Extend(Extension.OUT, 12, .7);
      // mecanumDrive.move(mecanumDrive.FORWARDS, 20, .8);
       //arm stuff
        sleep(20000);

    }
}
