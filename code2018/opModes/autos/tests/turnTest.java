package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;
@Disabled
@Autonomous  (name = "turnTest" , group = "Autonomous")
public class turnTest extends LinearOpMode{
    armAngle Armangle;

    public void initialize (){
        Armangle = new armAngle(hardwareMap, this);
    }
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        Armangle.tilt(armAngle.FORWARDS, 6, .7);


    }
}
