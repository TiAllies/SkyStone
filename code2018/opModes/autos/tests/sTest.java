package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.tests;

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
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;

import java.util.Locale;

@Disabled
@Autonomous (name = "sTest" , group = "Autonomous")
public class sTest extends LinearOpMode{
    MecanumDrive mecanumDrive;
    BNO055IMU imu;
    Orientation angles;
    Acceleration gravity;
    private GoldAlignDetector detector;

    public void initialize () {
        mecanumDrive = new MecanumDrive(hardwareMap);
        detector = new GoldAlignDetector();


        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        composeTelemetry();

        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        //detector stuff
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance()); // Initialize it with the app context and camera
        detector.useDefaults(); // Set detector to use default settings

        // Optional tuning
        detector.alignSize = 100; // How wide (in pixels) is the range in which the gold object will be aligned. (Represented by green bars in the preview)
        detector.alignPosOffset = 0; // How far from center frame to offset this alignment zone.
        detector.downscale = 0.4; // How much to downscale the input frames

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable(); // Start the detector!

    }

    void composeTelemetry() {
        telemetry.addAction(new Runnable() { @Override public void run()
        {
            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            gravity  = imu.getGravity();
        }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("grvty", new Func<String>() {
                    @Override public String value() {
                        return gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(gravity.xAccel*gravity.xAccel
                                        + gravity.yAccel*gravity.yAccel
                                        + gravity.zAccel*gravity.zAccel));
                    }
                });
    }
    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }
    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }




    public void runOpMode() throws InterruptedException {
        initialize();
        telemetry.update();
        mecanumDrive.setZeroPowerBehavior();
        waitForStart();
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
        while (opModeIsActive()) {
            telemetry.addData("IsAligned", detector.getAligned()); // Is the bot aligned with the gold mineral?
            telemetry.addData("X Pos", detector.getXPosition()); // Gold X position.
            telemetry.update();

            if (angles.firstAngle < -10 || angles.firstAngle > 10) {
                telemetry.update();
                mecanumDrive.turnRight(.25);
                telemetry.update();
                sleep(2000);
                if (detector.getAligned() == true) {
                    mecanumDrive.move(mecanumDrive.FORWARDS, 20, .4);
                    mecanumDrive.stop();
                    sleep(20000);
                } else {
                    mecanumDrive.side(mecanumDrive.LEFT, 10, .4);
                    sleep(2000);
                    if (detector.getAligned() == true) {
                        mecanumDrive.move(mecanumDrive.FORWARDS, 20, .4);
                        mecanumDrive.stop();
                        sleep(20000);
                    } else {
                        mecanumDrive.side(mecanumDrive.RIGHT, 20, .4);
                        sleep(2000);
                        if (detector.getAligned() == true) {
                            mecanumDrive.move(mecanumDrive.FORWARDS, 20, .4);
                            mecanumDrive.stop();
                            sleep(20000);
                        }
                    }
                }
            } else {
                if (detector.getAligned() == true) {
                    mecanumDrive.move(mecanumDrive.FORWARDS, 20, .4);
                    mecanumDrive.stop();
                    sleep(20000);
                } else {
                    mecanumDrive.side(mecanumDrive.LEFT, 10, .4);
                    sleep(2000);
                    if (detector.getAligned() == true) {
                        mecanumDrive.move(mecanumDrive.FORWARDS, 20, .4);
                        mecanumDrive.stop();
                        sleep(20000);
                    } else {
                        mecanumDrive.side(mecanumDrive.RIGHT, 20, .4);
                        sleep(2000);
                        if (detector.getAligned() == true) {
                            mecanumDrive.move(mecanumDrive.FORWARDS, 20, .4);
                            mecanumDrive.stop();
                            sleep(20000);
                            mecanumDrive.stop();
                        }
                    }
                }
            }
        }



        }




    }





