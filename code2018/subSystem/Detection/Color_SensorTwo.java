package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Titanium Allies on 2/7/2018.
 */

public class Color_SensorTwo {
    private com.qualcomm.robotcore.hardware.ColorSensor _colorSensorTwo;

    public Color_SensorTwo(HardwareMap hardwareMap) {
        _colorSensorTwo = hardwareMap.colorSensor.get("colorSensorTwo");

    }

    public int red(){
        return _colorSensorTwo.red();
    }
    public int blue(){
        return _colorSensorTwo.blue();
    }
    public int green() {return _colorSensorTwo.green();}
}
