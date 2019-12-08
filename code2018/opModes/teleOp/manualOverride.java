package org.firstinspires.ftc.teamcode.Ta10272.code2018.opModes.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Arm;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Claws;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Craw;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Mandible;
import org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Meccanum;


@TeleOp (name = "manualOverride" , group = "TeleOp")
public class manualOverride extends OpMode{

   Meccanum meccanum;
//   Craw craw;
//   Arm arm;
//   Mandible mandible;



    public void init() {

        meccanum = new Meccanum(hardwareMap, telemetry);
//        craw = new Craw(hardwareMap);
//        arm = new Arm(hardwareMap);
//        mandible = new Mandible(hardwareMap);
    }


    public void loop() {

        telemetry.addData("right joystick X: ", gamepad1.right_stick_x);

        // -------------------------- //
        // Drivetrain code for moving //
        // -------------------------- //
        meccanum.drive(-gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        // ------------------------- //
        // Grabber on the end of arm //
        // ------------------------- //
       /* if (gamepad2.right_trigger > 0.2){
            craw.grab(gamepad2.right_trigger);
        } else if (gamepad2.left_trigger > 0.2) {
            craw.release(gamepad2.left_trigger);
        } else { craw.halt(); }*/




        // front grabber

        /*if (gamepad2.x){
            mandible.stoneLevel();
        }

        if (gamepad2.b) {
            mandible.foundLevel();
        }

        if (gamepad2.y){
            mandible.up();
        }

        if (gamepad2.left_bumper){
            mandible.letGo();
        }

        if (gamepad2.right_bumper){
            mandible.bite();
        }

        if (gamepad2.right_trigger > 0.2){
            mandible.biteMore();
        }

        if (gamepad2.left_stick_y > 0.2) {
            mandible.opening(gamepad2.left_stick_y);
        }*/







        //------------//
        // Arm code   //
        //------------//
        /*// getting the powers that will be assigned to the motors of each segment
        double pow1 = Math.max(Math.abs(gamepad2.left_stick_x), Math.abs(gamepad2.left_stick_y));    // segment 1's motor
        double pow2 = Math.max(Math.abs(gamepad2.right_stick_x), Math.abs(gamepad2.right_stick_y));  // segment 2's motor


        // ------------------------------------------- //
        // Moving the first segment in the X direction //
        // ------------------------------------------- //
        arm.moveSeg1X(gamepad2.left_stick_x, pow1);



        // ------------------------------------------- //
        // Moving the first segment in the y direction //
        // ------------------------------------------- //
        arm.moveSeg1Y(gamepad2.left_stick_y, pow1);



        // --------------------------------------------- //
        // Moving the second segment in the x directions //
        // --------------------------------------------- //
        arm.moveSeg2X(gamepad2.right_stick_x, pow2);



        // --------------------------------------------- //
        // Moving the second segment in the y directions //
        // --------------------------------------------- //

        arm.moveSeg2Y(gamepad2.right_stick_y, pow2);



        // ------------------------------------------------ //
        // If nothing should be moving, stop the arm motors //
        // ------------------------------------------------ //
        if (Math.abs(gamepad2.left_stick_y) < 0.1 && Math.abs(gamepad2.left_stick_x) < 0.1){
            arm.halt1();
         }

        if (Math.abs(gamepad2.right_stick_y) < 0.1 && Math.abs(gamepad2.right_stick_x) < 0.1){
            arm.halt2();
        }*/


    }
}
