package code2018.subSystem.Detection;


import com.qualcomm.robotcore.hardware.HardwareMap;


/**
 * Created by Titanium Allies on 5/21/2017.
 */

public class Color_Sensor {

    private com.qualcomm.robotcore.hardware.ColorSensor _colorSensor;

    public Color_Sensor(HardwareMap hardwareMap) {
        _colorSensor = hardwareMap.colorSensor.get("colorSensor");
    }

    public int red(){
        return _colorSensor.red();
    }
    public int blue(){
        return _colorSensor.blue();
    }
    public int green() {return _colorSensor.green();}

}
