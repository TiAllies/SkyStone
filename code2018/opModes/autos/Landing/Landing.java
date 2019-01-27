package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.Landing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Lift;

@Autonomous (name = "Landing" , group = "Autonomous")
public class Landing extends LinearOpMode{
    Lift lift;
    public void Initialize () {
        lift = new Lift(hardwareMap, this);
        lift.setZeroLift();
    }
    public void runOpMode() throws InterruptedException {
        Initialize();
        waitForStart();
        lift.LIFT(Lift.DOWN, 9, 1);
        sleep(20000);
    }
}
