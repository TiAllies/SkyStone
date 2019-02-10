package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.NonLanding;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Extension;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;
@Disabled
@Autonomous (name="NLCorner [P]", group = "Autonomous")
public class NLParkingCorner extends LinearOpMode{
    MecanumDrive mecanumDrive;
  //  Extension extension;
  //  armAngle armAngle;
    public void initialize () {
        mecanumDrive = new MecanumDrive(hardwareMap, this);
     //   armAngle = new armAngle(hardwareMap, this);
      //  extension = new Extension(hardwareMap, this);
    }
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        mecanumDrive.move(mecanumDrive.FORWARDS, 17, .8);
        mecanumDrive.turn(mecanumDrive.LEFT, 14, .6);
        mecanumDrive.move(mecanumDrive.FORWARDS, 40, .8);
        mecanumDrive.turn(mecanumDrive.LEFT, 10,.6);
        mecanumDrive.move(mecanumDrive.FORWARDS, 12, 1);
        //arm stuff
        sleep(20000);

    }
}
