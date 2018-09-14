package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Detection;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Titanium Allies on 6/25/2017.
 */

public class Touch_Sensor {

    private TouchSensor _touchSensor;

    public Touch_Sensor(HardwareMap hardwareMap) {
        _touchSensor = hardwareMap.touchSensor.get("touchThing");
    }

    public boolean isTouched () {
        return _touchSensor.isPressed();
    }

}
