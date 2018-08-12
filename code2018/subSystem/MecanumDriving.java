package Code18.code2018.subSystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static android.os.SystemClock.sleep;

/**
 * Created by Titanium Allies on 12/3/2017.
 */

public class MecanumDriving {
    private DcMotor _motorRightFront;
    private DcMotor _motorRightBack;
    private DcMotor _motorLeftFront;
    private DcMotor _motorLeftBack;

    public MecanumDriving(LinearOpMode linearOpMode) {
        _linearOpMode = linearOpMode;
    }

    private LinearOpMode _linearOpMode;

    public MecanumDriving(HardwareMap hardwareMap) {
        this(hardwareMap, null);
    }

    public MecanumDriving(HardwareMap hardwareMap, LinearOpMode linearOpMode) {

        //super(linearOpMode);

        _motorRightFront = hardwareMap.dcMotor.get("rightFront");
        _motorRightBack = hardwareMap.dcMotor.get("rightBack");
        _motorLeftFront = hardwareMap.dcMotor.get("leftFront");
        _motorLeftBack = hardwareMap.dcMotor.get("leftBack");

        _motorLeftBack.setDirection(DcMotor.Direction.REVERSE);
        _motorLeftFront.setDirection(DcMotor.Direction.REVERSE);
        _linearOpMode = linearOpMode;
    }

    public void motorRightFront(double power) {
        _motorRightFront.setPower(power);
    }
    public void motorLeftFront(double power) {
        _motorLeftFront.setPower(power);
    }
    public void motorRightBack(double power) {
        _motorRightBack.setPower(power);
    }
    public void motorLeftBack(double power) {
        _motorLeftBack.setPower(power);
    }

    public void stop() {
        _motorRightBack.setPower(0);
        _motorRightFront.setPower(0);
        _motorLeftFront.setPower(0);
        _motorLeftBack.setPower(0);

        sleep(300);
    }

    public final static int Forwards = 1;
    public final static int Backwards = -1;
    public final static int Right = 1;
    public final static int Left = -1;

    protected final int DIAMETER = 4;
    protected final double CIRCUMFERENCE = DIAMETER * 3.14159;
    protected final int TICKS_PER_ROTATION = 1100;
    protected final double TICKS_PER_INCH = TICKS_PER_ROTATION / CIRCUMFERENCE;

    public void setMotorMode(DcMotor.RunMode mode) {
        _motorLeftBack.setMode(mode);
        _motorLeftFront.setMode(mode);
        _motorRightFront.setMode(mode);
        _motorRightBack.setMode(mode);
    }

    public void resetEncoders() {
        _motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _motorLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _motorRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public int convertEncoder(int distance) {
        return (int) (distance * TICKS_PER_INCH);
    }

    /*public void moving (int direction, int distance, double power){
        _motorLeftBack.
                //Is this unfinished?
    }*/


}