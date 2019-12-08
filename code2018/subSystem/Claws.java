package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claws {

    private final static double grabL = 1 ;
    private final static double grabR = .68 ;
    private final static double raisedL = 0.17;
    private final static double raisedR = 0;
    private final static double init = 0.17;
    private final static double init2 = 0;

    private Servo clawL;
    private Servo clawR;

    public Claws(HardwareMap hardwareMap){
        clawL = hardwareMap.servo.get("clawL");
      //  clawR = hardwareMap.servo.get("clawR");
        clawL.setPosition(init);
      //  clawR.setPosition(init2);
    }

    // ------------------------------------------------------------- //
    // Function for the claws to grab and release during the TeleOp  //
    // ------------------------------------------------------------- //
    public void gripL (){
        clawL.setPosition(grabL);
    }
    public void releaseL (){
        clawL.setPosition(raisedL);
    }
    public void gripR (){
        clawR.setPosition(grabR);
    }
    public void releaseR (){
        clawR.setPosition(raisedR);
    }

    // ----------------------------------------------- //
    // Function for the claws to grab during the auto  //
    // ----------------------------------------------- //
    public void autoGrab () {
        clawL.setPosition(grabL);
//        clawR.setPosition(grabR);
    }

    // -------------------------------------------------- //
    // Function for the claws to release during the auto  //
    // -------------------------------------------------- //
    public void autoRelease () {

        clawL.setPosition(raisedL);
//        clawR.setPosition(raisedR);

    }

}
