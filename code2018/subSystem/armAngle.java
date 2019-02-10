package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;

public class armAngle {

    private DcMotor Theta;
    private LinearOpMode _linearOpMode;


    public armAngle(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
    }

    public armAngle(HardwareMap hardwareMap){
        this (hardwareMap, null);
    }

    public armAngle(HardwareMap hardwareMap, LinearOpMode linearOpMode){

        //super(linearOpMode);

        Theta = hardwareMap.dcMotor.get("theta");
        _linearOpMode = linearOpMode;
    }

    public void armPower (double power) {
        Theta.setPower(.7*-power);
    }

    public void stop () {
        Theta.setPower(0);
    }

    public void setZeroPowerBehavior () {
        Theta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void DROP () {
        Theta.setPower(-.8);
        sleep(500);
        Theta.setPower(.4);
        sleep(300);
        Theta.setPower(0);
        stop();
    }


    //autonomous



    public final static int FORWARDS = -1;
    public final static int BACKWARDS = 1;

    protected final double DIAMETER = 1.5;
    protected final double CIRCUMFERENCE = DIAMETER*3.14159;
    protected final int TICKS_PER_ROTATION = 1100;
    protected final double TICKS_PER_INCH = TICKS_PER_ROTATION/CIRCUMFERENCE;

    public void setMotorMode (DcMotor.RunMode mode) {
        Theta.setMode(mode);

    }
    public void resetEncoders (){
        Theta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public int convertEncoder (double distance) {
        return (int) (distance*TICKS_PER_INCH);
    }

    //Error somewhere in the following code
    public void tilt(int direction, int distance, double power){
        setMotorMode(DcMotor.RunMode.RUN_TO_POSITION);
        distance = convertEncoder(distance);
        Theta.setTargetPosition((Theta.getCurrentPosition() - (direction*distance)));

        sleep(100);
        Theta.setPower(power);


        while (Theta.isBusy() && _linearOpMode.opModeIsActive()){


            _linearOpMode.telemetry.addData("Right Back Distance", Math.abs(Theta.getCurrentPosition() - Theta.getTargetPosition()));
            _linearOpMode.telemetry.update();
            _linearOpMode.idle();
        }
        stop();
    }

}


/*
for future opmode:

double thetaPower;

thetapower = gamepad2.left_stick_y;

if(gamepad2.left_stick_y > .999) {
   gamepad2.left_stick_y = 1;
}
else armAngle.stop;

armAngle.armPower(thetaPower);



 */
