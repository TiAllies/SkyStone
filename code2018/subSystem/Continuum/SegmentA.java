package org.firstinspires.ftc.teamcode.Ta10272.code2018.subSystem.Continuum;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class SegmentA {

    int links = 3;

    //target string lengths
    double targetL1;
    double targetL2;
    double targetL3;
    double targetL4;

    //current point
    double currentL1;
    double currentL2;
    double currentL3;
    double currentL4;

    //amount link takes up
    double thetaL;

    //angle of the center
    double thetaCenter;

    // previous segment's l, m, n
    double s1H1Pl;
    double s1H1Pm;
    double s1H1Pn;

    // segment's l, m, n
    double s1H1Cl;
    double s1H1Cm;
    double s1H1Cn;

    // segments radii
    double r1H1;
    double r1H2;
    double r1H3;
    double r1H4;

    //base point
    double bX;
    double bY;
    double bZ;

    //target point
    double tX;
    double tY;
    double tZ;

    //base point to target point vector
    double btX;
    double btY;
    double btZ;

    //base plane
    double bpA;
    double bpB;
    double bpC;
    double bpD;

    //base plane point
    double bpp_X;
    double bpp_Y;
    double bpp_Z;

    // new plane
    double npQ;
    double npR;
    double npS;

    //normal vector
    double nvXx;
    double nvXy;
    double nvXz;

    double nvYx;
    double nvYy;
    double nvYz;

    double nvZx;
    double nvZy;
    double nvZz;


    //unit vector's x, y, z
    double uvXx;
    double uvXy;
    double uvXz;

    double uvYx;
    double uvYy;
    double uvYz;

    double uvZx;
    double uvZy;
    double uvZz;

    //segment with holes
    //x, y , z
    double s1H1x;
    double s1H1y;
    double s1H1z;



// -----------VVV Functions VVV-----------------//

    // --------------------------------- //
    // finding the X, Y, Z for BT vector //
    // --------------------------------- //
    public void makebtX () {
        btX = (tX - bX);
    }

    public void makebtY () {
        btY = (tY - bY);
    }

    public void makebtZ () {
        btZ = (tZ - bZ);
    }


    // --------------------------- //
    // finding the Q, R, S for NP  //
    // --------------------------- //
    public void makenpQ () {
        npQ = ((btY * bpC) - (btZ * bpB));
    }

    public void makenpR () {
        npQ = ((btZ * bpA) - (btX * bpC));
    }

    public void makenpS () {
        npQ = ((btX * bpB) - (btY * bpA));
    }


    // ---------------------------- //
    // finding the X, Y, Z for BPP  //
    // ---------------------------- //
    public void makebpp_X () {
        bpp_X = (bX - 1);
    }

    public void makebpp_Y () {
        bpp_Y = (bY + (bpA/bpB));
    }

    public void makebpp_Z () {
        bpp_Z = bZ;
    }


    // ------------------------------------ //
    // finding normal vector's X's x, y, z  //
    // ------------------------------------ //
    public void makenvXx (){
        nvXx = btX;
    }

    public void makenvXy (){
        nvXy = btY;
    }

    public void makenvXz (){
        nvXz = btZ;
    }


    // ------------------------------------ //
    // finding normal vector's Y's x, y, z  //
    // ------------------------------------ //
    public void makenvYx (){
        nvYx = ((btY * npS) - (btZ * npR));
    }

    public void makenvYy (){
        nvYy = ((btZ * npQ) - (btX * npS));
    }

    public void makenvYz (){
        nvYz = ((btX * npR) - (btY * npQ));
    }


    // ------------------------------------ //
    // finding normal vector's Z's x, y, z  //
    // ------------------------------------ //
    public void makenvZx (){
        nvZx = npQ;
    }

    public void makenvZy (){
        nvZy = npR;
    }

    public void makenvZz (){
        nvZz = npS;
    }


    // ---------------------------------- //
    // finding unit vector's X's x, y, z  //
    // ---------------------------------- //
    public void makeuvXx (){
        uvXx = nvXx / (Math.sqrt(Math.pow(nvXx, 2) + Math.pow(nvXy, 2) + Math.pow(nvXz, 2)));
    }

    public void makeuvXy (){
        uvXy = nvXy / (Math.sqrt(Math.pow(nvXx, 2) + Math.pow(nvXy, 2) + Math.pow(nvXz, 2)));
    }

    public void makeuvXz (){
        uvXz = nvXz / (Math.sqrt(Math.pow(nvXx, 2) + Math.pow(nvXy, 2) + Math.pow(nvXz, 2)));
    }


    // ---------------------------------- //
    // finding unit vector's Y's x, y, z  //
    // ---------------------------------- //
    public void makeuvYx (){
        uvYx = nvYx / (Math.sqrt(Math.pow(nvYx, 2) + Math.pow(nvYy, 2) + Math.pow(nvYz, 2)));
    }

    public void makeuvYy (){
        uvYy = nvYy / (Math.sqrt(Math.pow(nvYx, 2) + Math.pow(nvYy, 2) + Math.pow(nvYz, 2)));
    }

    public void makeuvYz (){
        uvYz = nvYz / (Math.sqrt(Math.pow(nvYx, 2) + Math.pow(nvYy, 2) + Math.pow(nvYz, 2)));
    }


    // ---------------------------------- //
    // finding unit vector's Z's x, y, z  //
    // ---------------------------------- //
    public void makeuvZx (){
        uvZx = nvZx / (Math.sqrt(Math.pow(nvZx, 2) + Math.pow(nvZy, 2) + Math.pow(nvZz, 2)));
    }

    public void makeuvZy (){
        uvZy = nvZy / (Math.sqrt(Math.pow(nvZx, 2) + Math.pow(nvZy, 2) + Math.pow(nvZz, 2)));
    }

    public void makeuvZz (){
        uvZz = nvZz / (Math.sqrt(Math.pow(nvZx, 2) + Math.pow(nvZy, 2) + Math.pow(nvZz, 2)));
    }

    // ------------------------------ //
    // in terms of M                  //
    // ------------------------------ //

    public void  convertL (double xValue, double yValue, double mValue) {
        double lValue;
        lValue = (xValue - mValue*uvYx - ( ( (uvXx * uvYy *mValue) + uvXy*(xValue - mValue*uvYx) - yValue*uvXx) / ( (uvXy * uvZx) - (uvXx * uvZy)))) / uvXx;
    }

    public void convertN (double xValue, double yValue, double mValue) {
        double nValue;
        nValue =  ( ( (uvXx * uvYy * mValue) + uvXy*(xValue - uvYx*mValue) - yValue*uvXx)/ ( (uvXy*uvZx) - (uvXx * uvZy) ) );
    }


    // ------------------------------------ //
    // getting the current length for holes //
    // ------------------------------------ //

    public void string1Length () {
        currentL1 = 2*r1H1 * Math.sin(thetaL/2);

    }

    // ------------------- //
    // setting the thetaL  //
    // ------------------- //
    public void makeThetaL () {
        thetaL = thetaCenter/links;
    }


    // ------------------------------ //
    //  equations for solving  L,M,N  //
    // ------------------------------ //

    public void Convert ( double xVal, double yVal, double zVal, double lValue, double mValue, double nValue) {


        double determ = (nvXx * ((nvYy*nvZz)- (nvZy*nvYz))-nvYx*((nvXy*nvZz)-(nvZy*nvXz))+nvZx*((nvXy*nvYz)-(nvYy*nvXz)));

        lValue = (xVal*((nvYy*nvZz)-(nvZy*nvYz))-nvYx*((yVal*nvZz)-(nvZy*zVal))+nvZx*((yVal*nvYz)-(nvYy*zVal))) / determ;
        mValue = (nvXx*((yVal*nvZz)-(nvZy*zVal))-xVal*((nvXy*nvZz)-(nvZy*nvXz))+nvZx*((nvXy*zVal)-(yVal*nvXz))) / determ;
        nValue = (nvXx*((nvYy*zVal)-(yVal*nvYz))-nvYx*((nvXy*zVal)-(yVal*nvXz))+xVal*((nvXy*nvYz)-(nvYy*nvXz))) / determ;

    }




}
