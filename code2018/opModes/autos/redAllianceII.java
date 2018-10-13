package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claw;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Color_Sensor;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.JewelPusher;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Optical_Distance_Sensor;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
/**
 * Created by Titanium Allies on 11/10/2017.
 */
@Autonomous(name ="RedAllianceFar",group="Autonomous" )
@Disabled
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
        claw.closeOne();
        jewelPusher.LowerOne();
        while (opModeIsActive()) {
            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
                telemetry.addData("color", "red");
                telemetry.update();
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                jewelPusher.RaiseOne();
                mecanumDrive.move(mecanumDrive.FORWARDS, 48, 0.7);
                mecanumDrive.side(mecanumDrive.RIGHT, 11, 1);
                mecanumDrive.move(mecanumDrive.FORWARDS, 18, 1);
                sleep(500);
                claw.closeOne();
                sleep(1000);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 5, -1);
                mecanumDrive.stop();
                sleep(20000);

            }
            sleep(10);
            if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
                telemetry.addData("color", "blue");
                telemetry.update();
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                jewelPusher.RaiseOne();
                mecanumDrive.move(mecanumDrive.FORWARDS, 48, 1);
                mecanumDrive.side(mecanumDrive.RIGHT, 11, 1);
                mecanumDrive.move(mecanumDrive.FORWARDS, 18, 1);
                claw.closeOne();
                sleep(2500);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 5, -1);
                mecanumDrive.stop();
                sleep(20000);

            }
    }
}}
