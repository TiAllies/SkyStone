package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Continuum;

public class Conversion {

    double lValue;
    double mValue;
    double nValue;

    public void Convert (double normVecXx, double normVecYx, double normVecZx, double normVecXy, double normVecYy, double normVecZy, double normVecXz, double normVecYz, double normVecZz, double xVal, double yVal, double zVal) {

        double determ = (normVecXx * ((normVecYy*normVecZz)- (normVecZy*normVecYz))-normVecYx*((normVecXy*normVecZz)-(normVecZy*normVecXz))+normVecZx*((normVecXy*normVecYz)-(normVecYy*normVecXz)));

        lValue = (xVal*((normVecYy*normVecZz)-(normVecZy*normVecYz))-normVecYx*((yVal*normVecZz)-(normVecZy*zVal))+normVecZx*((yVal*normVecYz)-(normVecYy*zVal))) / determ;
        mValue = (normVecXx* ((yVal*normVecZz)-(normVecZy*zVal))-xVal*((normVecXy*normVecZz)-(normVecZy*normVecXz))+normVecZx*((normVecXy*zVal)-(yVal*normVecXz)))/ determ;

    }





}
