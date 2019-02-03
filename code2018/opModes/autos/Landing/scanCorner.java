package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.Landing;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Lift;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle;

import java.util.Locale;

@Autonomous(name = "Scanning[L + S]" , group = "Autonomous")
public class scanCorner extends LinearOpMode {
    private MecanumDrive mecanumDrive;
    private org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.armAngle armAngle;
    private Lift lift;
    private GoldAlignDetector detector;
    private BNO055IMU imu;
    private Orientation angles;
    private Acceleration gravity;
    double pi = 3.1415926;


    public void initialize () {
        mecanumDrive = new MecanumDrive(hardwareMap, this);
        //mecanumDrive.setZeroPowerBehavior();
        armAngle = new armAngle(hardwareMap);
        armAngle.setZeroPowerBehavior();
        lift = new Lift (hardwareMap);
        lift.setZeroLift();

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        composeTelemetry();

        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");


        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        // Optional tuning
        detector.alignSize = 100;
        detector.alignPosOffset = 0;
        detector.downscale = 0.4;

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;
        detector.maxAreaScorer.weight = 0.005;

        detector.ratioScorer.weight = 5;
        detector.ratioScorer.perfectRatio = 1.0;

        detector.enable();

    }

    public void composeTelemetry() {
        telemetry.addAction(new Runnable() {
            @Override
            public void run() {
                angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
                gravity = imu.getGravity();
            }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override
                    public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override
                    public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override
                    public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override
                    public String value() {
                        return gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override
                    public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(gravity.xAccel * gravity.xAccel
                                        + gravity.yAccel * gravity.yAccel
                                        + gravity.zAccel * gravity.zAccel));
                    }
                });
    }

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees) {
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }

    @Override
    public void runOpMode() {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("IsAligned", detector.getAligned()); // Is the bot aligned with the gold mineral?
            telemetry.addData("X Pos", detector.getXPosition()); // Gold X position.
            telemetry.update();

            lift.LIFT(Lift.DOWN, 13, 1);
            sleep(700);
            mecanumDrive.move(mecanumDrive.BACKWARDS, 5, .6);
            mecanumDrive.side(mecanumDrive.RIGHT, 3, 1);
            mecanumDrive.move(mecanumDrive.FORWARDS, 5, 1);

            sleep(700);
            if (detector.getXPosition() >= 1 && detector.getXPosition() <= 300) {

                mecanumDrive.move(mecanumDrive.FORWARDS, 3, 1);
                mecanumDrive.side(mecanumDrive.RIGHT, 25, .7);


                sleep(28000);
            } else if (detector.getXPosition() >= 350 && detector.getXPosition() <= 650) {


                mecanumDrive.move(mecanumDrive.FORWARDS, 7, .7);
                mecanumDrive.turn(mecanumDrive.LEFT, 6, 1);
                mecanumDrive.side(mecanumDrive.RIGHT, 25, .7);

                sleep(28000);
            } else {
                mecanumDrive.move(mecanumDrive.BACKWARDS, 10, .7);
                mecanumDrive.side(mecanumDrive.RIGHT, 25, .7);

                sleep(28000);
            }
        }
        mecanumDrive.stop();
    }
}
