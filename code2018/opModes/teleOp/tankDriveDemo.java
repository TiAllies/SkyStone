package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.demoDrive;

@TeleOp (name = "tankDriveDemo", group = "TeleOp")
@Disabled
public class tankDriveDemo extends OpMode{

    demoDrive demoDrive;

    public void init() {
        demoDrive = new demoDrive(hardwareMap);
    }


    public void loop() {

        double left;
        double right;

        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        if (gamepad1.left_stick_y > .999){
            gamepad1.left_stick_y = 1;
        }

        if (gamepad1.right_stick_y > .999){
            gamepad1.right_stick_y = 1;
        }
        else demoDrive.stop();

        demoDrive.leftSide(-left);
        demoDrive.rightSide(right);






    }
}
