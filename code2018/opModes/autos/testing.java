package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

/**
 * Created by Titanium Allies on 2/8/2018.
 */
@Autonomous(name ="Testing",group="Autonomous" )
public class testing extends LinearOpMode {
    private MecanumDrive mecanumDrive;


    public void initialize() {
        mecanumDrive = new MecanumDrive(hardwareMap);


    }



    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        mecanumDrive.forwards(1);
        sleep(5000);
        mecanumDrive.move(mecanumDrive.BACKWARDS, 20, .5);
    }
}
