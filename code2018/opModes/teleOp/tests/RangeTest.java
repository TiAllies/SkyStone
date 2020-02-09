package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp.tests;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccauto;


@Autonomous (name = "rangeTest", group = "Autonomous")
@Disabled
public class RangeTest extends LinearOpMode {
    ModernRoboticsI2cRangeSensor range;
    Meccauto meccauto;

    public void initialize () {
        range = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "Range");
        meccauto = new Meccauto(hardwareMap, telemetry);
    }


    public void runOpMode() throws InterruptedException {
        meccauto.movement(Meccauto.FORWARDS, .7);
        while(range.rawUltrasonic() < 60){}
        meccauto.stay();
        sleep(5000);
    }
}
