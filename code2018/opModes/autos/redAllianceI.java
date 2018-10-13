package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claw;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.JewelPusher;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Color_Sensor;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Optical_Distance_Sensor;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;

/**
 * Created by Titanium Allies on 5/21/2017.
 */
@Autonomous(name ="RedAllianceClose",group="Autonomous" )
@Disabled
public class redAllianceI extends LinearOpMode {

    private Color_Sensor colorSensor;
    private MecanumDrive mecanumDrive;
    private Optical_Distance_Sensor oDS;
    private Claw claw;
    private JewelPusher jewelPusher;

    public void initialize() {
        colorSensor = new Color_Sensor(hardwareMap);
        mecanumDrive = new MecanumDrive(hardwareMap, this);
      //  oDS = new Optical_Distance_Sensor(hardwareMap);
        claw = new Claw(hardwareMap);
          jewelPusher = new JewelPusher(hardwareMap);

    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();
        claw.openOne();
        jewelPusher.LowerOne();
        while (opModeIsActive()) {
            //jewel phase
            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
                telemetry.addData("color", "red");
                telemetry.update();
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                jewelPusher.RaiseOne();
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                //end of jewel phase
                mecanumDrive.move(mecanumDrive.FORWARDS, 29, 0.1);
                mecanumDrive.turn(mecanumDrive.RIGHT, 11, 0.5);
                mecanumDrive.move(mecanumDrive.FORWARDS, 9, 1);
                claw.closeOne();
                sleep(1000);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 5, 1);
               /* mecanumDrive.turn(mecanumDrive.RIGHT, 22, .7);
                mecanumDrive.move(mecanumDrive.FORWARDS,56, 0.6 );
                claw.close();
                sleep(500);
                mecanumDrive.turn(mecanumDrive.LEFT, 22, .7);
                mecanumDrive.move(mecanumDrive.FORWARDS, 60, .9);
                sleep(500);
                claw.open();
                sleep(1000);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 4, 1);*/
                mecanumDrive.stop();
                sleep(20000);
            }
            sleep(10);
        if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
            telemetry.addData("color", "blue");
            mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
            sleep(40);
            jewelPusher.RaiseOne();
            mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
            sleep(30);
            mecanumDrive.move(mecanumDrive.FORWARDS, 29, 0.1);
            sleep(10);
            mecanumDrive.turn(mecanumDrive.RIGHT, 11, 1);
            sleep(30);
            mecanumDrive.move(mecanumDrive.FORWARDS, 9, 1);
            sleep(500);
            claw.closeOne();
            sleep(1000);
            mecanumDrive.move(mecanumDrive.BACKWARDS, 5, 1);
           /* mecanumDrive.turn(mecanumDrive.RIGHT, 22, .7);
            mecanumDrive.move(mecanumDrive.FORWARDS,56, 0.6 );
            claw.close();
            sleep(500);
            mecanumDrive.turn(mecanumDrive.LEFT, 22, .7);
            mecanumDrive.move(mecanumDrive.FORWARDS, 60, .9);
            sleep(500);
            claw.open();
            sleep(1000);
            mecanumDrive.move(mecanumDrive.BACKWARDS, 4, 1);*/
            mecanumDrive.stop();
            sleep(20000);

        }



    }}}

