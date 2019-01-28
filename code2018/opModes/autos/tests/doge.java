package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.tests;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;



@Autonomous (name = "doge" , group = "Autonomous")
public class doge extends LinearOpMode{

    MecanumDrive mecanumDrive;
    GoldAlignDetector detector;
    /*double xPos;
    double alignX    = (100);
    double alignSize = (100);
    double alignXMin = alignX - (alignSize / 2);
    double alignXMax = alignX +(alignSize / 2);*/


    public void initialize (){
        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();


        detector.alignSize = 100;
        detector.alignPosOffset = 0;
        detector.downscale = 0.4;

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        detector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable(); // Start the detector!
    }
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("IsAligned" , detector.getAligned()); // Is the bot aligned with the gold mineral?
            telemetry.addData("X Pos" , detector.getXPosition()); // Gold X position.

            if (detector.getAligned()) {
                mecanumDrive.move(mecanumDrive.FORWARDS, 8, .3);
                sleep(2000);
                mecanumDrive.side(mecanumDrive.RIGHT, 5, .4);
            }else mecanumDrive.stop();

        }



    }
}


