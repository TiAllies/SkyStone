package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Titanium Allies on 11/3/2017.
 */

public class Claw{
    private final static double OPEN = 1;
    private final static double CLOSE = 0;
    private final static double STOP = .5;
    private final static double STOP1 = .55;

    private Servo _contTop;
    private Servo _contBot;

    public Claw (HardwareMap hardwareMap){
        _contTop = hardwareMap.servo.get("contTop");
        _contBot = hardwareMap.servo.get("contBot");

        _contBot.setPosition(STOP);
        /*for(int i = 0; i < 500; i++){
            _contTop.setPosition(OPEN);
            //_contBot.setPosition(CLOSE);
        }*/
        _contTop.setPosition(STOP);
        }

    public void openOne() {
        _contBot.setPosition(OPEN);
    }

    public void closeOne(){
        _contBot.setPosition(CLOSE);
    }

    public void stopOne(){
        _contBot.setPosition(STOP);
    }


    public void openTwo(){
        _contTop.setPosition(OPEN);
    }

    public void closeTwo(){
        _contTop.setPosition(CLOSE);
    }

    public void stopTwo(){
        _contTop.setPosition(STOP);
    }


}
    /*private final static double OPENI = 1;
    private final static double OPENII = 0;
   // private final static double CLOSE = .4;
    private final static double INITING = .23;
    private final static double INITINGII = .77;

    private Servo _topRightClaw;
    private Servo _botRightClaw;
    private Servo _topLeftClaw;
    private Servo _botLeftClaw;

    private double _rightTopClawPosition = INITINGII;
    private double _leftTopClawPosition = INITING;
    private double _rightBotClawPosition = INITING;
    private double _leftBotClawPosition = INITINGII;
    private double _leftBottomClawPosition = OPENI;
    private double _RightBottomClawPosition = OPENII;


    public Claw (HardwareMap hardwareMap){
        _topRightClaw = hardwareMap.servo.get("topRightClaw");
        _topLeftClaw = hardwareMap.servo.get("topLeftClaw");
        _botRightClaw = hardwareMap.servo.get("botRightClaw");
        _botLeftClaw = hardwareMap.servo.get("botLeftClaw");

        _topRightClaw.setPosition(OPENI);
        _topLeftClaw.setPosition(OPENII);
        _botRightClaw.setPosition(OPENII);
        _botLeftClaw.setPosition(OPENI);

    }


    public void open (){
        _botLeftClaw.setPosition(OPENI);
        _botRightClaw.setPosition(OPENII);

    }

    public void close () {
        _botLeftClaw.setPosition(INITINGII);
        _botRightClaw.setPosition(INITING);
    }

    public void closeTop(){
        if (_rightTopClawPosition >=.713) {
            _rightTopClawPosition -= .02;
            _topRightClaw.setPosition(_rightTopClawPosition);
        }
        if (_leftTopClawPosition <=.287) {
            _leftTopClawPosition += .02;
            _topLeftClaw.setPosition(_leftTopClawPosition);
        }
    }

    public void openTop(){
        if (_rightTopClawPosition >= 0){
        _rightTopClawPosition += .02;
        _topRightClaw.setPosition(_rightTopClawPosition);
        }
        if (_leftTopClawPosition <= 1){
        _leftTopClawPosition -= .02;
        _topLeftClaw.setPosition(_leftTopClawPosition);
        }
    }

    public void closeBot(){
        if (_rightBotClawPosition < .287){
        _rightBotClawPosition += .02;
        _botRightClaw.setPosition(_rightBotClawPosition);
        }
        if (_leftBotClawPosition > .713) {
        _leftBotClawPosition -= .02;
        _botLeftClaw.setPosition(_leftBotClawPosition);
        }
    }

    public void openBot(){
        if (_rightBotClawPosition < 1) {
            _rightBotClawPosition -= .02;
            _botRightClaw.setPosition(_rightBotClawPosition);
        }
        if (_leftBotClawPosition > 0) {
            _leftBotClawPosition += .02;
            _botLeftClaw.setPosition(_leftBotClawPosition);
        }
    }

    public void stopTop(){
        _rightTopClawPosition += 0;
        _leftTopClawPosition += 0;    }

    public void stopBot(){
        _rightBotClawPosition += 0;
        _leftBotClawPosition += 0;
    }
}*/
