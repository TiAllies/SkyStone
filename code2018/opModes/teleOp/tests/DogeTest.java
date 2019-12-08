package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp.tests;

import com.disnodeteam.dogecv.detectors.skystone.SkystoneDetector;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.Locale;

/*
 * Thanks to EasyOpenCV for the great API (and most of the example)
 *
 * Original Work Copright(c) 2019 OpenFTC Team
 * Derived Work Copyright(c) 2019 DogeDevs
 */
@TeleOp(name = "Skystone Detector OpMode", group="DogeCV")

public class DogeTest extends LinearOpMode {
    private OpenCvCamera phoneCam;
    private SkystoneDetector skyStoneDetector;

    @Override
    public void runOpMode() {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        phoneCam.openCameraDevice();

        skyStoneDetector = new SkystoneDetector();

        phoneCam.setPipeline(skyStoneDetector);

        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);

        waitForStart();

        while (opModeIsActive())
        {

            telemetry.addData("Stone Position X", skyStoneDetector.getScreenPosition().x);
            telemetry.addData("Stone Position Y", skyStoneDetector.getScreenPosition().y);
            telemetry.addData("Frame Count", phoneCam.getFrameCount());
            telemetry.addData("FPS", String.format(Locale.US, "%.2f", phoneCam.getFps()));
            telemetry.addData("Total frame time ms", phoneCam.getTotalFrameTimeMs());
            telemetry.addData("Pipeline time ms", phoneCam.getPipelineTimeMs());
            telemetry.addData("Overhead time ms", phoneCam.getOverheadTimeMs());
            telemetry.addData("Theoretical max FPS", phoneCam.getCurrentPipelineMaxFps());
            telemetry.addData("thing 1", skyStoneDetector.perfectAreaScorer.perfectArea);
            telemetry.update();


            if(gamepad1.a)
            {

                phoneCam.stopStreaming();
                //webcam.closeCameraDevice();
            }

            else if(gamepad1.x) {
                phoneCam.pauseViewport();
            }
            else if(gamepad1.y) {
                phoneCam.resumeViewport();
            }
        }
    }
}
