package org.firstinspires.ftc.teamcode.Ta10272.code2018.Archives.auto;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Titanium Allies on 6/10/2018.
 */
@TeleOp(name = "Titanium Gyro", group = "Sensor")
@Disabled
public class gyroFun extends LinearOpMode {

    IntegratingGyroscope gyro;
    ModernRoboticsI2cGyro TitaniumI2c;

    ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() {

        boolean lastResetState = false;
        boolean curResetState  = false;

        TitaniumI2c = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope) TitaniumI2c;

        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        TitaniumI2c.calibrate();

        timer.reset();
        while (!isStopRequested() && TitaniumI2c.isCalibrating())  {
            telemetry.addData("calibrating", "%s", Math.round(timer.seconds())%2==0 ? "|.." : "..|");
            telemetry.update();
            sleep(50);
        }

        telemetry.log().clear(); telemetry.log().add("Gyro Calibrated. Press Start.");
        telemetry.clear(); telemetry.update();

        waitForStart();
        telemetry.log().clear();
        telemetry.log().add("Press A & B to reset heading");

        while (opModeIsActive())  {

            curResetState = (gamepad1.a && gamepad1.b);
            if (curResetState && !lastResetState) {
                TitaniumI2c.resetZAxisIntegrator();
            }
            lastResetState = curResetState;

            int rawX = TitaniumI2c.rawX();
            int rawY = TitaniumI2c.rawY();
            int rawZ = TitaniumI2c.rawZ();
            int heading = TitaniumI2c.getHeading();
            int integratedZ = TitaniumI2c.getIntegratedZValue();

            AngularVelocity rates = gyro.getAngularVelocity(AngleUnit.DEGREES);
            float zAngle = gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

            int zAxisOffset = TitaniumI2c.getZAxisOffset();
            int zAxisScalingCoefficient = TitaniumI2c.getZAxisScalingCoefficient();

            telemetry.addLine()
                    .addData("dx", formatRate(rates.xRotationRate))
                    .addData("dy", formatRate(rates.yRotationRate))
                    .addData("dz", "%s deg/s", formatRate(rates.zRotationRate));
            telemetry.addData("angle", "%s deg", formatFloat(zAngle));
            telemetry.addData("heading", "%3d deg", heading);
            telemetry.addData("integrated Z", "%3d", integratedZ);
            telemetry.addLine()
                    .addData("rawX", formatRaw(rawX))
                    .addData("rawY", formatRaw(rawY))
                    .addData("rawZ", formatRaw(rawZ));
            telemetry.addLine().addData("z offset", zAxisOffset).addData("z coeff", zAxisScalingCoefficient);
            telemetry.update();
        }
    }

    String formatRaw(int rawValue) {
        return String.format("%d", rawValue);
    }

    String formatRate(float rate) {
        return String.format("%.3f", rate);
    }

    String formatFloat(float rate) {
        return String.format("%.3f", rate);
    }
}
