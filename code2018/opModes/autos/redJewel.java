package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Color_Sensor;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Color_SensorTwo;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.JewelPusher;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Suspension;

/**
 * Created by Titanium Allies on 11/11/2017.
 */
@Disabled
@Autonomous(name ="RedJewel",group="Autonomous" )
    public class redJewel extends LinearOpMode{
    private Color_Sensor colorSensor;
    private JewelPusher jewelPusher;
    private MecanumDrive mecanumDrive;
    private Suspension suspension;
    private Color_SensorTwo colorSensorTwo;

    public void initialize() {
        colorSensorTwo = new Color_SensorTwo(hardwareMap);
        mecanumDrive = new MecanumDrive(hardwareMap, this);
        jewelPusher = new JewelPusher(hardwareMap);
        suspension = new Suspension(hardwareMap);
    }


    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        waitForStart();
        suspension.upsieDoodleDoo();
        jewelPusher.LowerOne();
        sleep(3000);
        while (opModeIsActive()) {
            if (colorSensorTwo.red() > colorSensorTwo.blue() && colorSensorTwo.red() > colorSensorTwo.green()) {
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, .1);
                jewelPusher.initPositionOne();
            }}}}
