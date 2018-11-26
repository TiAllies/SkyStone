package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.tests;
import android.graphics.Paint;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.opencv.core.Mat;

@Disabled
@Autonomous(name ="Test",group="Autonomous" )
public class test2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

    }

   /* // Detector object
    private GoldAlignDetector detector;
    boolean aligned = detector.getAligned();

    MecanumDrive mecanumDrive;
    Mat mat;


    public void initialize() {
        detector = new GoldAlignDetector(); // Create detector
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

        mecanumDrive = new MecanumDrive(hardwareMap);

    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("IsAligned" , detector.getAligned()); // Is the bot aligned with the gold mineral?
            telemetry.update();

            if (detector.getXPosition() > 390) {
                MoveRobot(1350,1000, .2);
            }
            //Center
            else if (detector.getXPosition() < 390 && detector.getXPosition()> 100) {
                MoveRobot(1200,1200, .2);

            }
            //Right
            else if (detector.getXPosition() < 99) {
                MoveRobot(400, 0, .2);

                MoveRobot(1000,1350, .2);

            }

        }
    }
*/
}

