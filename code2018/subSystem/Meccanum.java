package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Meccanum{

    private DcMotor motorRightFront;
    private DcMotor motorRightBack;
    private DcMotor motorLeftFront;
    private DcMotor motorLeftBack;
    private Telemetry telemetry;


    //variables to be declared for motor powers
    double leftFrontVariance = 1;
    double rightFrontVariance = 1;
    double leftBackVariance = 1;
    double rightBackVariance = 1;

    double leftFront ;
    double rightFront ;
    double leftBack ;
    double rightBack ;

    boolean executed;

    public Meccanum(HardwareMap hardwareMap, Telemetry telemetry) {

        motorLeftFront = hardwareMap.dcMotor.get("leftFront");
        motorRightFront = hardwareMap.dcMotor.get("rightFront");
        motorLeftBack = hardwareMap.dcMotor.get("leftBack");
        motorRightBack = hardwareMap.dcMotor.get("rightBack");

        this.telemetry = telemetry;

        motorRightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        setZeroPowerBehavior();
    }

    // ----------------------------------------------------------------------- //
    //  This will set the motors to brake instead of coasting to a stop        //
    // ----------------------------------------------------------------------- //

    private void setZeroPowerBehavior() {
        motorLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    // -------------------------------------------------------------------------- //
    //  The overall method that will declare a final power to each of the motors  //
    // -------------------------------------------------------------------------- //

    public void drive (double xMotorPower, double yMotorPower, double turnMotorPower){



        yAxis(yMotorPower);
        strafe(xMotorPower);
        turn(turnMotorPower);

        adjustment(xMotorPower, yMotorPower, turnMotorPower);

        telemetry.addData("FL MotorPower", leftFront);
        telemetry.addData("FR MotorPower", rightFront);
        telemetry.addData("BL MotorPower", leftBack);
        telemetry.addData("BR MotorPower", rightBack);


        motorLeftFront.setPower(leftFront * leftFrontVariance);
        motorRightFront.setPower(rightFront * rightFrontVariance);
        motorLeftBack.setPower(leftBack * leftBackVariance);
        motorRightBack.setPower(rightBack * rightBackVariance);

        leftFront = 0.0;
        rightFront = 0.0;
        leftBack = 0.0;
        rightBack = 0.0;
    }


    // ---------------------------------------------------------------------------------------------------------------------- //
    //  Adds the power value that would be assigned to each motor for driving forwards or backwards to the final power value  //
    // ---------------------------------------------------------------------------------------------------------------------- //

    private void yAxis (double power){

        leftFront += power;
        rightFront += power;
        leftBack += power;
        rightBack += power;
    }


    // ------------------------------------------------------------------------------------------------- //
    //  Adds the power value that would be assigned to each motor for strafing to the final power value  //
    // ------------------------------------------------------------------------------------------------- //

    private void strafe (double power) {

        // This is set for going to the right, if it is negative this will go to the left and vice versa.

        leftFront -= power;
        rightFront += power;
        leftBack += power;
        rightBack -= power;

    }


    // ------------------------------------------------------------------------------------------------ //
    //  Adds the power value that would be assigned to each motor for turning to the final power value  //
    // ------------------------------------------------------------------------------------------------ //

    private void turn (double power) {

        // This is set for turning to the right, if it is negative this will go to the left and vice versa.

        leftFront += power;
        rightFront -= power;
        leftBack += power;
        rightBack -= power;

    }


    // ---------------------------------------------------------------------- //
    //  Adjusts the final power for each motor to the range of [1,-1]
    //  it then scales the power based on the joystick with the most throttle
    // ---------------------------------------------------------------------- //

    private void adjustment (double xMotorPower, double yMotorPower, double turnMotorPower){

        double hypo = Math.sqrt((Math.pow(xMotorPower, 2))+(Math.pow(yMotorPower, 2)));
        double hypoPow;
        double turnpow = turnMotorPower;
        if (Math.abs(turnMotorPower) < .05) {
            turnpow = 0;
        } else {
            turnpow = 1/turnMotorPower;
        }


        if (hypo < .05) {
            hypoPow = 0;
        } else{
            hypoPow = 1/hypo;
        }
        double maxPow = Math.max(Math.max(Math.abs(leftFront), Math.abs(rightFront)), Math.max(Math.abs(leftBack), Math.abs(rightBack)));
        double powScale = Math.max(hypoPow, Math.abs(turnpow));
        if(Math.abs(maxPow) < .1)
        {
            leftFront = 0.0;
            rightFront = 0.0;
            leftBack = 0.0;
            rightBack = 0.0;
        }else {
            leftFront /= (maxPow * powScale);
            rightFront /= (maxPow * powScale);
            leftBack /= (maxPow * powScale);
            rightBack /= (maxPow * powScale);
        }




    }


    // ------------------------------------------------------------------------ //
    //  For testing the config of the wheels if they are in the right position  //
    // ------------------------------------------------------------------------ //

    /*public void testLeftFrontForward(){
        motorLeftFront.setPower(.2);
    }
    public void testRightFrontForward(){
        motorRightFront.setPower(.2);
    }
    public void testLeftBackForward(){
        motorLeftBack.setPower(.2);
    }
    public void testRightBackForward(){
        motorRightBack.setPower(.2);
    }*/

    // ----------------------------------------------------------------------- //
    //  For testing the power needed to match all motors to the same speed     //
    // ----------------------------------------------------------------------- //

    public void increaseLeftFront () {
        leftFrontVariance += .05;
    }
    public void increaseRightFront () {
        rightFrontVariance += .05;
    }
    public void increaseLeftBack () {
        leftBackVariance += .05;
    }
    public void increaseRightBack () {
        rightBackVariance += .05;
    }


    public void decreaseLeftFront () {
        leftFrontVariance -= .05;
    }
    public void decreaseRightFront () {
        rightFrontVariance -= .05;
    }
    public void decreaseLeftBack () {
        leftBackVariance -= .05;
    }
    public void decreaseRightBack () {
        rightBackVariance -= .05;
    }


    // ----------------------------------------------------------------------- //
    //  general stop function to set all the motors to a rest power of 0       //
    // ----------------------------------------------------------------------- //
    public void stay(){
        motorRightBack.setPower(0);
        motorLeftBack.setPower(0);
        motorRightFront.setPower(0);
        motorLeftFront.setPower(0);


        leftFront = 0;
        rightFront = 0;
        leftBack = 0;
        rightBack = 0;
    }


    public void powerScalingTest () {
        motorLeftFront.setPower(1 * leftFrontVariance);
        motorRightFront.setPower(1 * rightFrontVariance);
        motorLeftBack.setPower(1 * leftBackVariance);
        motorRightBack.setPower(1 * rightBackVariance);
        }

    public void powerScalingReset () {
        motorLeftFront.setPower(-1 * leftFrontVariance);
        motorRightFront.setPower(-1 * rightFrontVariance);
        motorLeftBack.setPower(-1 * leftBackVariance);
        motorRightBack.setPower(-1 * rightBackVariance);
    }

    public boolean executed () {
        return executed;
    }




    /*public void forward(double power){
        motorRightFront.setPower(power);
        motorLeftBack.setPower(power);
        motorRightBack.setPower(power);
        motorLeftFront.setPower(power);
    }


    public double anglePowerX (double y_value, double x_value){
        this.xMotorPower = Math.tan(Math.atan(Math.abs(y_value/x_value)));
        return xMotorPower;
    }

    public double anglePowerY (double y_value, double x_value) {
        this.yMotorPower = Math.tan(Math.atan(Math.abs(y_value/x_value))- 45);
        return yMotorPower;
    }

    public double normPower (double y_value, double x_value){
         this.motorPower = Math.sqrt((Math.pow(y_value, 2) + Math.pow(x_value, 2)));
         return motorPower;

    }*/



}
