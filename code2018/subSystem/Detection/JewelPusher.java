package code2018.subSystem.Detection;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Titanium Allies on 10/22/2017.
 */

public class JewelPusher {
    public final static double DOWN = 0.04 ;
    public final static double UP = 1 ;
    public final static double NEUTRAL = 1 ;

    private Servo _jewelPush;
    private double _jewelPushPosition;

    public JewelPusher(HardwareMap hardwareMap){
        _jewelPush = hardwareMap.servo.get("jewelPusher");
        _jewelPush.setPosition(UP);
       /* _jewelPushPosition = NEUTRAL;
        _jewelPush.setPosition(_jewelPushPosition);*/
    }
    public void Lower(){
        _jewelPush.setPosition(DOWN);
    }
    public void Raise () {
        _jewelPush.setPosition(UP);
    }
    public void neutral (){
        _jewelPush.setPosition(NEUTRAL);
    }

}
