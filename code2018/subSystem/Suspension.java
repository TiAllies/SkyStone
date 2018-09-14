package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Titanium Allies on 1/28/2018.
 */

public class Suspension {
    private final static double ON = 0.75;
    private final static double OFF = 0.375;
    private final static double MIDDLE = 0;

    private Servo _suspension;

    public Suspension(HardwareMap hwmap){
        _suspension = hwmap.servo.get("suspension");
        _suspension.setPosition(MIDDLE);
    }
    public void upsieDoodleDoo(){
        _suspension.setPosition(ON);
    }
    public void downsieDaisy(){
        _suspension.setPosition(OFF);
    }
    public void idle(){_suspension.setPosition(MIDDLE);}

}

