package Code18.code2018.subSystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Titanium Allies on 10/22/2017.
 */

public class JewelPusher {
    private final static double DOWN = .666 ;
    private final static double UP = 0 ;
    private final static double Init2 = 1 ;
    private final static double Init1 = 0;

    private Servo _jewelPushOne;
    private Servo _jewelPushTwo;

    public JewelPusher(HardwareMap hardwareMap){
        _jewelPushOne = hardwareMap.servo.get("jewelPusher1");
        _jewelPushTwo = hardwareMap.servo.get("jewelPusher2");
        _jewelPushOne.setPosition(Init1);
        _jewelPushTwo.setPosition(Init2);

    }
    public void LowerOne(){
        _jewelPushOne.setPosition(DOWN);
    }
    public void LowerTwo() {_jewelPushTwo.setPosition(UP);}
    public void RaiseOne() {
        _jewelPushOne.setPosition(UP);
    }
    public void RaiseTwo() {_jewelPushTwo.setPosition(DOWN);}
    public void initPositionOne() {_jewelPushOne.setPosition(Init1);}
    public void initPositionTwo () {_jewelPushTwo.setPosition(Init2);}

}
