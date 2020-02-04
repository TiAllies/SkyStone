package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.autos.redAlliance;

/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import android.text.method.BaseKeyListener;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Mandible;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

/**
 * This 2019-2020 OpMode illustrates the basics of using the Vuforia localizer to determine
 * positioning and orientation of robot on the SKYSTONE FTC field.
 * The code is structured as a LinearOpMode
 *
 * When images are located, Vuforia is able to determine the position and orientation of the
 * image relative to the camera.  This sample code then combines that information with a
 * knowledge of where the target images are on the field, to determine the location of the camera.
 *
 * From the Audience perspective, the Red Alliance station is on the right and the
 * Blue Alliance Station is on the left.

 * Eight perimeter targets are distributed evenly around the four perimeter walls
 * Four Bridge targets are located on the bridge uprights.
 * Refer to the Field Setup manual for more specific location details
 *
 * A final calculation then uses the location of the camera on the robot to determine the
 * robot's location and orientation on the field.
 *
 * @see VuforiaLocalizer
 * @see VuforiaTrackableDefaultListener
 * see  skystone/doc/tutorial/FTC_FieldCoordinateSystemDefinition.pdf
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */


@Autonomous(name="Scanner: Red|Vertical", group ="Autonomous")
public class RedDetectedPulled extends LinearOpMode {

    //subs
    Meccauto meccauto;
    Mandible mandible;




    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;


    private static final String VUFORIA_KEY =
            "AUHnteP/////AAAAGQiEoKkU5kbNrzEFIAPesbUo+3LDrufWcf8pZMP0MpLubYXJSK9jpf0GtOI1LSPKs4pLKXT0RsnVUWO9rnz8mF3eH/sE7jPe/iCcT/lkynWC/rvX+QN00uZiuSBDOBUDEw01Y9o6qjET3SUNSH7m7lcoiJcqKGX7/dX58vpTwYFJhGPjtBzHH0tL9SIiEPl/NY2G8j5NI6buI6FB4zoFpNpWmEqOJR4ru+slpI2nxD/+NG6AKzHNOAJN50rH4mMHwIefq9PFfCOF3jhYq7NN7TuJY9yg/DvxmyVF9cAKB7kZeoSv3/LwD9GtZqONzeprxnwwpvKjAkCWGgww6BnZv+ZD7oKtu1znbHuLrKHqmVd1";


    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    // Constant for Stone Target
    private static final float stoneZ = 2.00f * mmPerInch;

    // Constants for perimeter targets
    private static final float halfField = 72 * mmPerInch;
    private static final float quadField  = 36 * mmPerInch;

    // Class Members
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate    = 0;
    private float phoneYRotate    = 0;
    private float phoneZRotate    = 0;


    @Override public void runOpMode() {

        meccauto = new Meccauto(hardwareMap, telemetry);
        mandible = new Mandible(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection   = CAMERA_CHOICE;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        VuforiaTrackables targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");

        List<VuforiaTrackable> allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.add(stoneTarget);

        /**
         * In order for localization to work, we need to tell the system where each target is on the field, and
         * where the phone resides on the robot.  These specifications are in the form of <em>transformation matrices.</em>
         * Transformation matrices are a central, important concept in the math here involved in localization.
         * See <a href="https://en.wikipedia.org/wiki/Transformation_matrix">Transformation Matrix</a>
         * for detailed information. Commonly, you'll encounter transformation matrices as instances
         * of the {@link OpenGLMatrix} class.
         *
         * If you are standing in the Red Alliance Station looking towards the center of the field,
         *     - The X axis runs from your left to the right. (positive from the center to the right)
         *     - The Y axis runs from the Red Alliance Station towards the other side of the field
         *       where the Blue Alliance Station is. (Positive is from the center, towards the BlueAlliance station)
         *     - The Z axis runs from the floor, upwards towards the ceiling.  (Positive is above the floor)
         *
         * Before being transformed, each target image is conceptually located at the origin of the field's
         *  coordinate system (the center of the field), facing up.
         */


        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

        // We need to rotate the camera around it's long axis to bring the correct camera forward.
        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        // Rotate the phone vertical about the X axis if it's in portrait mode
        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90 ;
        }

        // Next, translate the camera lens to where it is on the robot.
        // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
        final float CAMERA_FORWARD_DISPLACEMENT  = 4.0f * mmPerInch;   // eg: Camera is 4 Inches in front of robot center
        final float CAMERA_VERTICAL_DISPLACEMENT = 8.0f * mmPerInch;   // eg: Camera is 8 Inches above ground
        final float CAMERA_LEFT_DISPLACEMENT     = 0;     // eg: Camera is ON the robot's center line

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        /**  Let all the trackable listeners know where the phone is.  */
        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }


         waitForStart();
        meccauto.move(Meccauto.FORWARDS, 22, 1);
        meccauto.turn(Meccauto.RIGHT, 2, 1);
        meccauto.side(Meccauto.LEFT, 8, 1);


        targetsSkyStone.activate();
        while (opModeIsActive()) {

            // check all the trackable targets to see which one (if any) is visible.
            targetVisible = false;
            for (VuforiaTrackable trackable : allTrackables) {
                if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                    telemetry.addData("Visible Target", trackable.getName());
                    targetVisible = true;

                    // getUpdatedRobotLocation() will return null if no new information is available since
                    // the last time that call was made, or if the trackable is not currently visible.
                    OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                    if (robotLocationTransform != null) {
                        lastLocation = robotLocationTransform;
                    }
                    break;
                }
            }



            if (targetVisible) {
                // express position (translation) of robot in inches.
                VectorF translation = lastLocation.getTranslation();
                telemetry.addData("Pos (in)", "{X, Y, Z} = %.1f, %.1f, %.1f",
                        translation.get(0) / mmPerInch, translation.get(1) / mmPerInch, translation.get(2) / mmPerInch);

                // express the rotation of the robot in degrees.
                Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
                telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);


                if ((translation.get(1)/mmPerInch) >= 6){
                    telemetry.addData("Stone", 3);
                    // stone position 3 (left of robot)
                    meccauto.side(Meccauto.LEFT, 3, 1);
                    sleep(2000);
                    mandible.stoneLevel();
                    mandible.openWide();
                    sleep(1000);
                    meccauto.move(Meccauto.FORWARDS, 12, .5);
                    mandible.biteMore();
                    sleep(300);
                    meccauto.move(Meccauto.BACKWARDS, 6, 1);
                    sleep(100);
                    meccauto.turn(Meccauto.RIGHT, 30, 1);
                    sleep(2000);
                    mandible.foundLevel();
                    meccauto.move(Meccauto.FORWARDS, 35, 1);
                    meccauto.turn(Meccauto.RIGHT, 3, 1);
                    meccauto.move(Meccauto.FORWARDS, 20, 1);
                    meccauto.turn(Meccauto.RIGHT, 5, 1);
                    sleep(100);
                    meccauto.side(Meccauto.LEFT, 5, 1);
                    meccauto.move(Meccauto.FORWARDS, 11, 1);
                    mandible.letGo();
                    sleep(300);
                    meccauto.move(Meccauto.BACKWARDS, 15, 1);
                    meccauto.side(Meccauto.RIGHT, 5, 1);
                    meccauto.move(Meccauto.BACKWARDS, 12, 1);
                    sleep(500000);

                } else if ((translation.get(1)/mmPerInch) < 6 && (translation.get(1)/mmPerInch) > -2){
                    telemetry.addData("Stone", 2);
                    // stone position 2 (middle, still a bit to the right of robot)
                    meccauto.side(Meccauto.RIGHT, 13, 1);
                    meccauto.turn(Meccauto.RIGHT, 2, 1);
                    sleep(2000);
                    mandible.stoneLevel();
                    mandible.openWide();
                    sleep(1000);
                    meccauto.move(Meccauto.FORWARDS,14,.3);
                    mandible.biteMore();
                    sleep(300);
                    meccauto.move(Meccauto.BACKWARDS, 7, 1);
                    sleep(100);
                    meccauto.turn(Meccauto.RIGHT, 31, 1);
                    sleep(2000);
                    mandible.foundLevel();
                    meccauto.move(Meccauto.FORWARDS, 40, .7);
                    meccauto.turn(Meccauto.RIGHT, 6, 1);
                    meccauto.move(Meccauto.FORWARDS, 25, 1);
                    meccauto.turn(Meccauto.RIGHT, 3, 1);
                    sleep(100);
                    meccauto.side(Meccauto.LEFT, 4, 1);
                    meccauto.move(Meccauto.FORWARDS, 12, 1);
                    mandible.letGo();
                    sleep(300);
                    meccauto.move(Meccauto.BACKWARDS, 10, 1);
                    meccauto.side(Meccauto.RIGHT, 7, 1);
                    meccauto.move(Meccauto.BACKWARDS, 15, 1);
                    sleep(500000);

                } else if ((translation.get(1)/mmPerInch) <= -2){
                    telemetry.addData("Stone", 1);
                    // stone position 1 (left of robot)
                    meccauto.side(Meccauto.RIGHT, 26, 1);
                    meccauto.turn(Meccauto.RIGHT, 4, 1);
                    sleep(2000);
                    mandible.stoneLevel();
                    mandible.openWide();
                    sleep(1000);
                    meccauto.move(Meccauto.FORWARDS,15,.4);
                    mandible.biteMore();
                    sleep(300);
                    meccauto.move(Meccauto.BACKWARDS, 7, 1);
                    meccauto.turn(Meccauto.RIGHT, 31, 1);
                    sleep(2000);
                    mandible.foundLevel();
                    meccauto.move(Meccauto.FORWARDS, 40, .7);
                    meccauto.turn(Meccauto.RIGHT, 5, 1);
                    meccauto.move(Meccauto.FORWARDS, 35, 1);
                    meccauto.turn(Meccauto.RIGHT, 8, 1);
                    sleep(100);
                    meccauto.side(Meccauto.LEFT, 6, 1);
                    meccauto.move(Meccauto.FORWARDS, 6, 1);
                    mandible.letGo();
                    sleep(300);
                    meccauto.move(Meccauto.BACKWARDS, 15, 1);
                    meccauto.side(Meccauto.RIGHT, 3, 1);
                    meccauto.move(Meccauto.BACKWARDS, 10, 1);
                    sleep(500000);



                }

            }

            telemetry.update();
        }
        // Disable Tracking when we are done;
        targetsSkyStone.deactivate();
    }
}

