package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Titanium Allies on 1/12/2018.
 */

public class Continuous {
        private final static double OUT = 1.00 ;
        private final static double IN = 0.30 ;
        private final static double NEUTRAL = 0.50 ;
        private double clawPosition;

        private Servo clawTest;
        private double clawTestPower;

        public Continuous(HardwareMap hardwareMap){
            clawTest = hardwareMap.servo.get("clawTest");
            clawTest.setPosition(IN);

        }
        public void close(){
            if (clawPosition > IN){
                clawPosition = clawPosition - 0.05;
            }
            clawTest.setPosition(clawPosition);
        }

        public void open () {
            if (clawPosition < OUT) {
                clawPosition = clawPosition + .05;

            }
            clawTest.setPosition(clawPosition);
        }

        public void release () {
            clawTest.setPosition(OUT);
        }

        public void grip () {
            clawTest.setPosition(IN);
        }


        public void neutral (){
            clawTest.setPosition(NEUTRAL);
        }

    }

