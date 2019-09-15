package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.auto.Landing;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

@Disabled
@Autonomous (name = "Cardinal Crater(L+S+P)" , group = "Autonomous")
public class SampParkCrater extends LinearOpMode {
    private MecanumDrive mecanumDrive;
     private armAngle armAngle;
     private Lift lift;
    private GoldAlignDetector detector;
    private BNO055IMU imu;
    private Orientation angles;
    private Acceleration gravity;
    double pi = 3.1415926;




    public void initialize() {
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
        sleep(400);






        while (opModeIsActive()) {
            telemetry.addData("IsAligned", detector.getAligned()); // Is the bot aligned with the gold mineral?
            telemetry.addData("X Pos", detector.getXPosition()); // Gold X position.
            telemetry.update();
            // if lift ends up working landing code goes here



            if (detector.getXPosition() > 100 && detector.getXPosition() < 350) {

                mecanumDrive.side(mecanumDrive.LEFT, 28, .5);

                mecanumDrive.side(mecanumDrive.RIGHT, 6, .5);

                mecanumDrive.move(mecanumDrive.FORWARDS, 42, .8);

                mecanumDrive.turn(mecanumDrive.RIGHT, 25, .8);

                mecanumDrive.side(mecanumDrive.RIGHT, 11, .5);

                mecanumDrive.move(mecanumDrive.BACKWARDS, 45, .4);

                armAngle.DROP();

                mecanumDrive.side(mecanumDrive.RIGHT, 3, .5);
                mecanumDrive.move(mecanumDrive.FORWARDS, 61, 1);
                sleep(20000);
            } else if (detector.getXPosition() > 450 && detector.getXPosition() < 650) {

                mecanumDrive.side(mecanumDrive.LEFT, 19, .5);

                mecanumDrive.move(mecanumDrive.FORWARDS, 20, .5);

                mecanumDrive.side(mecanumDrive.LEFT, 11, .5);

                mecanumDrive.side(mecanumDrive.RIGHT, 11, .5);

                mecanumDrive.move(mecanumDrive.FORWARDS, 20, .8);

                mecanumDrive.turn(mecanumDrive.RIGHT, 25, .8);

                mecanumDrive.side(mecanumDrive.RIGHT, 11, .5);

                mecanumDrive.move(mecanumDrive.BACKWARDS, 45, .4);

                armAngle.DROP();

                mecanumDrive.side(mecanumDrive.RIGHT, 3, .5);
                mecanumDrive.move(mecanumDrive.FORWARDS, 61, 1);
                sleep(28000);
            } else if (detector.getXPosition() < 100) {

                mecanumDrive.side(mecanumDrive.LEFT, 19 , .5);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 13, .5);
                mecanumDrive.side(mecanumDrive.LEFT, 11, .5);
                mecanumDrive.side(mecanumDrive.RIGHT, 15, .5);
                mecanumDrive.move(mecanumDrive.FORWARDS, 55, .8);
                mecanumDrive.turn(mecanumDrive.RIGHT, 25, .8);
                mecanumDrive.side(mecanumDrive.RIGHT, 11, .5);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 44, .4);

                armAngle.DROP();

                mecanumDrive.turn(mecanumDrive.LEFT, 2, .5);
                mecanumDrive.move(mecanumDrive.FORWARDS, 61, 1);
                sleep(20000);

            } else {
                mecanumDrive.side(mecanumDrive.LEFT, 19 , .5);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 13, .5);
                mecanumDrive.side(mecanumDrive.LEFT, 11, .5);
                mecanumDrive.side(mecanumDrive.RIGHT, 15, .5);
                mecanumDrive.move(mecanumDrive.FORWARDS, 55, .8);
                mecanumDrive.turn(mecanumDrive.RIGHT, 24, .8);
                mecanumDrive.side(mecanumDrive.RIGHT, 11, .5);
                mecanumDrive.move(mecanumDrive.BACKWARDS, 44, .4);

                armAngle.DROP();

                mecanumDrive.turn(mecanumDrive.LEFT, 2, .5);
                mecanumDrive.move(mecanumDrive.FORWARDS, 61, 1);
                sleep(20000);
            }



        }
        mecanumDrive.stop();
    }
}
