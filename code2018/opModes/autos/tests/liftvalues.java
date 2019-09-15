package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.tests;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Lift;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
@Disabled
@Autonomous (name = "liftTesting" , group = "Autonomous")
public class liftvalues extends LinearOpMode{
    Lift lift;
    MecanumDrive mecanumDrive;



    public void initialize () {
        lift = new Lift(hardwareMap, this);
        mecanumDrive = new MecanumDrive(hardwareMap, this);

        lift.setZeroLift();
        mecanumDrive.setZeroPowerBehavior();
    }


    public void runOpMode() {
            initialize();
            waitForStart();
            lift.LIFT(Lift.DOWN, 4, .8);

    }
}
