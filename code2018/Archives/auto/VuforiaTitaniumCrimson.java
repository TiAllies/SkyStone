/*

*/
/* Copyright (c) 2017 FIRST. All rights reserved.
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
 *//*

package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.MecanumDrive;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.subs.JewelPusher;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection.Color_Sensor;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Suspension;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Continuous;

@Autonomous(name="Red Alliance Far", group ="Autonomous")
@Disabled
public class VuforiaTitaniumCrimson extends LinearOpMode {
    private MecanumDrive mecanumDrive;
    private Color_Sensor colorSensor;
    // private Claw claw;
    private JewelPusher jewelPusher;
    private Suspension suspension;
    private Continuous continuous;

    public static final String TAG = "Vuforia VuMark Sample";

    OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;

    public void initialize() {

        mecanumDrive = new MecanumDrive(hardwareMap, this);
        colorSensor = new Color_Sensor(hardwareMap);
        // claw = new Claw(hardwareMap);
        jewelPusher = new JewelPusher(hardwareMap);
        suspension = new Suspension(hardwareMap);
        continuous = new Continuous(hardwareMap);
    }

    @Override public void runOpMode() throws InterruptedException {


        initialize();


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AUHnteP/////AAAAGQiEoKkU5kbNrzEFIAPesbUo+3LDrufWcf8pZMP0MpLubYXJSK9jpf0GtOI1LSPKs4pLKXT0RsnVUWO9rnz8mF3eH/sE7jPe/iCcT/lkynWC/rvX+QN00uZiuSBDOBUDEw01Y9o6qjET3SUNSH7m7lcoiJcqKGX7/dX58vpTwYFJhGPjtBzHH0tL9SIiEPl/NY2G8j5NI6buI6FB4zoFpNpWmEqOJR4ru+slpI2nxD/+NG6AKzHNOAJN50rH4mMHwIefq9PFfCOF3jhYq7NN7TuJY9yg/DvxmyVF9cAKB7kZeoSv3/LwD9GtZqONzeprxnwwpvKjAkCWGgww6BnZv+ZD7oKtu1znbHuLrKHqmVd1";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        telemetry.update();

        waitForStart();
        suspension.upsieDoodleDoo();

        relicTrackables.activate();
        while (opModeIsActive()) {




            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("VuMark", "%s visible", vuMark);

                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));

                */
/* We further illustrate how to decompose the pose into useful rotational and
                 * translational components *//*

                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extract the X, Y, and Z components of the offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extract the rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;
                }
            }
            else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();

            jewelPusher.LowerOne();
            if (colorSensor.red() > colorSensor.blue() && colorSensor.red() > colorSensor.green()) {
                //jewel phase
                telemetry.addData("color", "red");
                telemetry.update();
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                jewelPusher.RaiseOne();
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                //end of jewel phase
                if (vuMark == vuMark.LEFT) {
                    telemetry.addData("position", "left");
                    mecanumDrive.move(mecanumDrive.FORWARDS, 48, 0.7);
                    mecanumDrive.side(mecanumDrive.LEFT, 20, 1);
                    mecanumDrive.move(mecanumDrive.FORWARDS, 12, 1);
                    continuous.release();
                    sleep(1000);
                    mecanumDrive.move(mecanumDrive.BACKWARDS, 5, -1);
                    mecanumDrive.stop();
                    sleep(20000);

                }else if (vuMark == vuMark.CENTER) {
                    telemetry.addData("position", "center");
                    mecanumDrive.move(mecanumDrive.FORWARDS, 48, 0.7);
                    mecanumDrive.side(mecanumDrive.LEFT, 12, 1);
                    mecanumDrive.move(mecanumDrive.FORWARDS, 12, 1);
                    continuous.release();
                    sleep(1000);
                    mecanumDrive.move(mecanumDrive.BACKWARDS, 5, -1);
                    mecanumDrive.stop();
                    sleep(20000);

                }else if (vuMark == vuMark.RIGHT) {
                    telemetry.addData("position", "center");
                    mecanumDrive.move(mecanumDrive.FORWARDS, 48, 0.7);
                    mecanumDrive.side(mecanumDrive.LEFT, 4, 1);
                    mecanumDrive.move(mecanumDrive.FORWARDS, 12, 1);
                    continuous.release();
                    sleep(1000);
                    mecanumDrive.move(mecanumDrive.BACKWARDS, 5, -1);
                    mecanumDrive.stop();
                    sleep(20000);
                }

            }
            sleep(10);
            if (colorSensor.blue() > colorSensor.red() && colorSensor.blue() > colorSensor.green()) {
                //start of jewel phase
                telemetry.addData("color", "blue");
                mecanumDrive.turn(mecanumDrive.LEFT, 3, 1);
                jewelPusher.RaiseOne();
                mecanumDrive.turn(mecanumDrive.RIGHT, 3, 1);
                //end of jewel phase
                if (vuMark == vuMark.LEFT) {
                    mecanumDrive.move(mecanumDrive.FORWARDS, 37, 0.1);
                    mecanumDrive.side(mecanumDrive.LEFT, 12, 1);
                    mecanumDrive.move(mecanumDrive.FORWARDS, 12, 1);
                    continuous.release();
                    sleep(1000);
                    mecanumDrive.move(mecanumDrive.BACKWARDS, 5, 1);
                    mecanumDrive.stop();
                    sleep(20000);

                }else if (vuMark == vuMark.CENTER){
                    mecanumDrive.move(mecanumDrive.FORWARDS, 29, 0.1);
                    mecanumDrive.side(mecanumDrive.LEFT, 12, 1);
                    mecanumDrive.move(mecanumDrive.FORWARDS, 12, 1);
                    continuous.release();
                    sleep(1000);
                    mecanumDrive.move(mecanumDrive.BACKWARDS, 5, 1);
                    mecanumDrive.stop();
                    sleep(20000);

                }else if (vuMark == vuMark.RIGHT){
                    mecanumDrive.move(mecanumDrive.FORWARDS, 21, 0.1);
                    mecanumDrive.side(mecanumDrive.LEFT, 12, 1);
                    mecanumDrive.move(mecanumDrive.FORWARDS, 12, 1);
                    continuous.release();
                    sleep(1000);
                    mecanumDrive.move(mecanumDrive.BACKWARDS, 5, 1);
                    mecanumDrive.stop();
                    sleep(20000);
                }
            }
        }
    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
*/
