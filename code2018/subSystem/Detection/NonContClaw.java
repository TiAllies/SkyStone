package Code18.code2018.subSystem.Detection;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Titanium Allies on 2/9/2018.
 */

public class NonContClaw {
    private final static double OPEN = .2;
    private final static double CLOSE = 0;

    private Servo _bot;
    private Servo _top;

    //binary methods
    public NonContClaw(HardwareMap hardwareMap){
        _bot = hardwareMap.servo.get("botServo");
        _top = hardwareMap.servo.get("topServo");

        _bot.setPosition(OPEN);
        _top.setPosition(OPEN);
    }

    public void openNCBot(){
        _bot.setPosition(OPEN);
    }

    public void closeNCBot(){
        _bot.setPosition(CLOSE);
    }

    public void openNCTop(){
        _top.setPosition(OPEN);
    }

    public void closeNCTop(){
        _top.setPosition(CLOSE);
    }

    //float methods
    public void openNCBotF(){
        _bot.setPosition(_bot.getPosition()+.05);
    }
    public void closeNCBotF(){
        _bot.setPosition(_bot.getPosition()+.05);
    }
    public void openNCTopF(){
        _top.setPosition(_top.getPosition()+.05);
    }
    public void closeNCTopF(){
        _top.setPosition(_top.getPosition()+.05);
    }
}
