package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.auto.NonLanding;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;

@Disabled
@Autonomous(name="NLCrater [M + P]", group = "Autonomous")
public class NLMarkParkCrater extends LinearOpMode {
    MecanumDrive mecanumDrive;
  //  Extension extension;
  //  armAngle armAngle;
    public void initialize () {
        mecanumDrive = new MecanumDrive(hardwareMap, this);
      //  armAngle = new armAngle(hardwareMap, this);
     //   extension = new Extension(hardwareMap, this);
    }
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        mecanumDrive.move(mecanumDrive.FORWARDS, 17, .8);
        mecanumDrive.turn(mecanumDrive.LEFT, 14, .6);
        mecanumDrive.move(mecanumDrive.FORWARDS, 40, .8);
        mecanumDrive.turn(mecanumDrive.LEFT, 10,.6);
        mecanumDrive.move(mecanumDrive.FORWARDS, 21, 1);
        //arm stuff
        mecanumDrive.move(mecanumDrive.BACKWARDS, 35, .8);
        //arm stuff
        sleep(20000);

    }
}
