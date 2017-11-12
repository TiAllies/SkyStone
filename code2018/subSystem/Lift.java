package code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
        _liftMotor.setPower(-power);
    }

    //Lower lift
    public void downwards(double power){
        _liftMotor.setPower(power);
    }
}
