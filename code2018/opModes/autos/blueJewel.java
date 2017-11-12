package code2018.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import code2018.subSystem.Detection.Color_Sensor;
import code2018.subSystem.Detection.JewelPusher;
import code2018.subSystem.MecanumDrive;

/**
 * Created by Titanium Allies on 11/11/2017.
 */
@Autonomous(name ="BlueJewel",group="Autonomous" )
public class blueJewel extends LinearOpMode{
    private Color_Sensor colorSensor;
    private MecanumDrive mecanumDrive;
    private JewelPusher jewelPusher;

    public void initialize() {
        colorSensor = new Color_Sensor(hardwareMap);
        mecanumDrive = new MecanumDrive(hardwareMap, this);
        jewelPusher = new JewelPusher(hardwareMap);
    }
    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        waitForStart();
        jewelPusher.Lower();
        while (opModeIsActive()) {
            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                telemetry.addData("color", "blue");
                telemetry.update();
                jewelPusher.Raise();
                sleep(20000);
            }

            sleep(10);
            if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                telemetry.addData("color", "blue");
                telemetry.update();
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                jewelPusher.Raise();
                sleep(20000);

            }
        }
    }
}
