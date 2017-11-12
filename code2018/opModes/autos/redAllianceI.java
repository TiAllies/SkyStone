package code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import code2018.subSystem.Claw;
import code2018.subSystem.Detection.JewelPusher;
import code2018.subSystem.MecanumDrive;
import code2018.subSystem.Detection.Color_Sensor;
import code2018.subSystem.Detection.Optical_Distance_Sensor;

/**
 * Created by Titanium Allies on 5/21/2017.
 */
@Autonomous(name ="RedAllianceClose",group="Autonomous" )
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
          jewelPusher.Raise();
    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();
        jewelPusher.Lower();
        while (opModeIsActive()) {
            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
                telemetry.addData("color", "red");
                telemetry.update();
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                sleep(20);
                jewelPusher.Raise();
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                sleep(30);
                mecanumDrive.move(mecanumDrive.FORWARDS, 48, 0.7);
                sleep(20);
                mecanumDrive.turn(mecanumDrive.RIGHT, 11, 0.5);
                sleep(30);
                mecanumDrive.move(mecanumDrive.FORWARDS, 18, 1);
                sleep(500);
                claw.open();
                sleep(1000);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 5, 1);
                mecanumDrive.turn(mecanumDrive.RIGHT, 22, .7);
                mecanumDrive.move(mecanumDrive.FORWARDS,56, 0.6 );
                claw.close();
                sleep(500);
                mecanumDrive.turn(mecanumDrive.LEFT, 22, .7);
                mecanumDrive.move(mecanumDrive.FORWARDS, 60, .9);
                sleep(500);
                claw.open();
                sleep(1000);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 4, 1);
                mecanumDrive.stop();
                sleep(20000);
            }
            sleep(10);
        if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
            telemetry.addData("color", "blue");
            mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
            sleep(40);
            jewelPusher.Raise();
            mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
            sleep(30);
            mecanumDrive.move(mecanumDrive.FORWARDS, 48, 1);
            sleep(10);
            mecanumDrive.turn(mecanumDrive.RIGHT, 11, 1);
            sleep(30);
            mecanumDrive.move(mecanumDrive.FORWARDS, 18, 1);
            sleep(500);
            claw.open();
            sleep(1000);
            mecanumDrive.move(mecanumDrive.BACKWARDS, 5, 1);
            mecanumDrive.turn(mecanumDrive.RIGHT, 22, .7);
            mecanumDrive.move(mecanumDrive.FORWARDS,56, 0.6 );
            claw.close();
            sleep(500);
            mecanumDrive.turn(mecanumDrive.LEFT, 22, .7);
            mecanumDrive.move(mecanumDrive.FORWARDS, 60, .9);
            sleep(500);
            claw.open();
            sleep(1000);
            mecanumDrive.move(mecanumDrive.BACKWARDS, 4, 1);
            mecanumDrive.stop();
            sleep(20000);

        }



    }}}

