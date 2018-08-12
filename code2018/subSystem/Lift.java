package Code18.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

/**
 * Created by Titanium Allies on 11/3/2017.
 */

public class Lift {
    private DcMotor _liftMotor;

    public Lift(HardwareMap hardwareMap){
        _liftMotor = hardwareMap.dcMotor.get("liftMotor");
    }

    //Raise lift
    public void upwards(double power){
        _liftMotor.setPower(power);
    }

    public void setZeroLift(){
        _liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //Lower lift
    public void downwards(double power){
        _liftMotor.setPower(-power);
    }
}
