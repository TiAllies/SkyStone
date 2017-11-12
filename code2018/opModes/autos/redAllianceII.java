package code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import code2018.subSystem.Claw;
import code2018.subSystem.Detection.Color_Sensor;
import code2018.subSystem.Detection.JewelPusher;
import code2018.subSystem.Detection.Optical_Distance_Sensor;
import code2018.subSystem.MecanumDrive;
/**
 * Created by Titanium Allies on 11/10/2017.
 */
@Autonomous(name ="RedAllianceFar",group="Autonomous" )

public class redAllianceII extends LinearOpMode{
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
        jewelPusher.Lower();
        while (opModeIsActive()) {
            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
                telemetry.addData("color", "red");
                telemetry.update();
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                jewelPusher.Raise();
                mecanumDrive.move(mecanumDrive.FORWARDS, 24, 1);
                mecanumDrive.side(mecanumDrive.RIGHT, 12, 1);
                mecanumDrive.move(mecanumDrive.FORWARDS, 18, 1);
                sleep(500);
                claw.open();
                sleep(1000);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 5, -1);
                mecanumDrive.stop();
                sleep(20000);

            }
            sleep(10);
            if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
                telemetry.addData("color", "red");
                telemetry.update();
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                jewelPusher.Raise();
                mecanumDrive.move(mecanumDrive.FORWARDS, 24, 1);
                mecanumDrive.side(mecanumDrive.RIGHT, 12, 1);
                mecanumDrive.move(mecanumDrive.FORWARDS, 18, 1);
                claw.open();
                sleep(2500);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 5, -1);
                mecanumDrive.stop();
                sleep(20000);

            }
    }
}}
