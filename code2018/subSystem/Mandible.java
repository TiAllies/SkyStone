package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Mandible {

    private Servo rightJaw;
    private Servo fang;

    double jInit = 0;
    double foundation = 0.45;
    double stone = 0.75;

    double jInit2 = 0.2;
    double foundation2 = 0.42;
    double stone2 = 1;


    double fInit = 0.8;
    double open = 0.8;
    double close = 0.4;
    double closeMore = 0.25;

    public Mandible (HardwareMap hardwareMap){
//        leftJaw = hardwareMap.servo.get("LeftJaw");
        rightJaw = hardwareMap.servo.get("RightJaw");
        fang = hardwareMap.servo.get("Fang");

//        leftJaw.setPosition(jInit);
        rightJaw.setPosition(jInit2);
        fang.setPosition(fInit);
    }


    public void bite () {
        fang.setPosition(close);
    }

    public void letGo () {
        fang.setPosition(open);
    }

    public void biteMore () {
        fang.setPosition(closeMore);
    }


    public void foundLevel () {
//        leftJaw.setPosition(foundation);
        rightJaw.setPosition(foundation2);
    }

    public void stoneLevel () {
//        leftJaw.setPosition(stone);
        rightJaw.setPosition(stone2);
    }

    public void up () {
//        leftJaw.setPosition(jInit);
        rightJaw.setPosition(jInit2);
    }




}
