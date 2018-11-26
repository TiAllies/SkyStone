package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Color_Sensor;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Color_SensorTwo;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.subs.JewelPusher;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Suspension;

/**
 * Created by Titanium Allies on 11/11/2017.
 */
@Autonomous(name ="RedJewel",group="Autonomous" )
@Disabled
public class redAJewel extends LinearOpMode{
    private Color_Sensor color_sensor;
    private MecanumDrive mecanumDrive;
    private JewelPusher jewelPusher;
    private Color_SensorTwo colorSensor;
    private Suspension suspension;

    public void initialize() {
        color_sensor = new Color_Sensor(hardwareMap);
        mecanumDrive = new MecanumDrive(hardwareMap, this);
        jewelPusher = new JewelPusher(hardwareMap);
        colorSensor = new Color_SensorTwo(hardwareMap);
        suspension = new Suspension(hardwareMap);
    }
    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        waitForStart();
        suspension.upsieDoodleDoo();
        jewelPusher.LowerTwo();
        sleep(3000);
        while (opModeIsActive()) {
            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, .1);
                jewelPusher.initPositionTwo();
                mecanumDrive.turn(mecanumDrive.LEFT, 2, .1);
                telemetry.addData("color", "red");
                telemetry.update();
                sleep(20000);
            }

            sleep(10);
            if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
                mecanumDrive.turn(mecanumDrive.LEFT, 3, .1);
                jewelPusher.initPositionTwo();
                mecanumDrive.turn(mecanumDrive.RIGHT, 2, .1);
                telemetry.addData("color", "blue");
                telemetry.update();
                sleep(20000);

            }
        }
    }
}
