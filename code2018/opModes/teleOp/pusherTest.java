package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.JewelPusher;

/**
 * Created by Titanium Allies on 2/4/2018.
 */
@TeleOp(name = "PusherTest",group = "TeleOp")
@Disabled
public class pusherTest extends OpMode {
    JewelPusher jewelPusher;

    @Override
    public void init() {
        jewelPusher = new JewelPusher(hardwareMap);

    }

    @Override
    public void loop() {

        if (gamepad2.left_bumper){
            jewelPusher.LowerOne();
        }else if (gamepad2.right_bumper){
            jewelPusher.RaiseOne();
        }

        if (gamepad1.left_bumper){
            jewelPusher.RaiseTwo();
        }else if (gamepad1.right_bumper){
            jewelPusher.LowerTwo();
        }

    }
}
