package code2018.subSystem.Detection;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Titanium Allies on 5/21/2017.
 */
public class Optical_Distance_Sensor {

    private OpticalDistanceSensor oDS;

    private int SCALE = 100;
    private int THRESHOLD = 20; //for white line

    public Optical_Distance_Sensor(HardwareMap hardwareMap) {

        oDS = hardwareMap.opticalDistanceSensor.get("oDS");

    }

    public int getRawValue(){
        return (int)( oDS.getRawLightDetected());
    }

    public int getValue() {

        return (int)(oDS.getRawLightDetected()*SCALE);
    }

    public boolean seesWhite() {
        return getValue() > THRESHOLD;
    }
}
