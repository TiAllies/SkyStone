package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.tankdrive;

@TeleOp (name = "tankDriveTest", group = "TeleOp")
public class tankDriveTest extends OpMode {

    tankdrive tankdrive;

    @Override
    public void init() {
        tankdrive = new tankdrive(hardwareMap);
    }

    @Override
    public void loop() {

        if (gamepad1.dpad_up){
            tankdrive.forward(1);
        }
        if (gamepad1.dpad_down){
            tankdrive.backwards(1);
        }
        if (gamepad1.dpad_right){
            tankdrive.right(1);
        }
        if (gamepad1.dpad_left){
            tankdrive.left(1);
        }
        else tankdrive.stop();
    }
}
