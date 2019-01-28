package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.Landing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Lift;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;

import java.nio.charset.MalformedInputException;

@Autonomous (name = "Landing" , group = "Autonomous")
public class Landing extends LinearOpMode{
    Lift lift;
    MecanumDrive mecanumDrive;
    public void Initialize () {
        lift = new Lift(hardwareMap, this);
        lift.setZeroLift();

        mecanumDrive = new MecanumDrive(hardwareMap, this);
        mecanumDrive.setZeroPowerBehavior();
    }
    public void runOpMode() throws InterruptedException {
        Initialize();
        waitForStart();
        lift.LIFT(Lift.DOWN, 14, 1);
        sleep(1000);
        mecanumDrive.move(mecanumDrive.BACKWARDS, 3, .4);
        mecanumDrive.side(mecanumDrive.RIGHT, 3, .4);
        mecanumDrive.move(mecanumDrive.FORWARDS, 3, .4);
        sleep(20000);
    }
}
