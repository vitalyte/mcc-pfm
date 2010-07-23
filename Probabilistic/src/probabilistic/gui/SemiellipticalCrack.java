/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

import integration.CrackOrderOde;
import java.util.ArrayList;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math.ode.FirstOrderIntegrator;
import org.apache.commons.math.ode.IntegratorException;
import org.apache.commons.math.ode.nonstiff.DormandPrince54Integrator;

/**
 *
 * @author Vitaly
 */
public class SemiellipticalCrack {

    private static final double E10P6 = 1.0e6;
    private static final double k1SCC = 2.0e6;
    private double initiationTimeObj;
//    private double siteXPoint;
//    private double siteYPoint;
    private double length2a, initLength2a;
    private double depthB, initDepthB;
    private double aspectRatio;
    private SurfaceArea surfaceAreaObj;
    private Point crackPoint;
    private Point rightTip;
    private ArrayList<Point> crackTip;
    private Point leftTip;
    int timeIndex;
    private double sigma, sigmaYS, k;
    private boolean maxLength = false;
    //list changes of cracks states
    private ArrayList<CrackHistory> historyOfCrack;
    private boolean coalescenced = false;

    public SemiellipticalCrack() {
        historyOfCrack = new ArrayList<CrackHistory>();
        crackTip = new ArrayList<Point>();
    }

    public SemiellipticalCrack(SurfaceArea surface, double pointX, double pointY,
            double length2a, double depthB, int timeIndex) {
        this();
        surfaceAreaObj = surface;
        crackPoint = new Point(pointX, pointY);
        leftTip = new Point((crackPoint.getX() - length2a / 2), crackPoint.getY());
        rightTip = new Point((crackPoint.getX() + length2a / 2), crackPoint.getY());
        crackTip.add(leftTip);
        crackTip.add(rightTip);
        this.depthB = depthB;
        this.length2a = length2a;
//        this.length2a = crackTip.get(crackTip.size()-1).getX() - crackTip.get(0).getX();
//        setLength2a(length2a);
        //for integrate
        initLength2a = length2a;
        initDepthB = depthB;

        aspectRatio = depthB / length2a / 2;
//        aspectRatio = 1;
        this.timeIndex = timeIndex;

//        rightTip = new Point((crackPoint.getX() + length2a / 2), crackPoint.getY());
//        leftTip = new Point((crackPoint.getX() - length2a / 2), crackPoint.getY());
        sigma = surfaceAreaObj.getSigma();
        sigmaYS = surfaceAreaObj.getYieldStress();
        k = surfaceAreaObj.getParametrK();
        initiationTimeObj = this.surfaceAreaObj.getTimeObj().getInitTime().get(timeIndex);
//        initiationTimeObj = surfaceAreaObj.getTimeIndex();
        historyOfCrack.add(new CrackHistory(this, surfaceAreaObj.getTimeIndex()));
        surfaceAreaObj.getCracksHistoryList().add(historyOfCrack);
    }

    public SemiellipticalCrack(SemiellipticalCrack obj1, SemiellipticalCrack obj2, int timeIndex) {
        this();
        surfaceAreaObj = obj1.getSurfaceAreaObj();
        crackTip = coalescPolyline(obj1, obj2);
        leftTip = crackTip.get(0);
        rightTip = crackTip.get(crackTip.size() - 1);
        this.length2a = crackTip.get(crackTip.size() - 1).getX() - crackTip.get(0).getX();
        this.depthB = Math.max(obj1.getDepthB(), obj2.getDepthB());
        aspectRatio = depthB / length2a / 2;
        this.timeIndex = timeIndex;
        sigma = surfaceAreaObj.getSigma();
        sigmaYS = surfaceAreaObj.getYieldStress();
        k = surfaceAreaObj.getParametrK();
        initiationTimeObj = obj1.getSurfaceAreaObj().getTimeObj().getInitTime().get(timeIndex);
        coalescenced = true;
        surfaceAreaObj.getCracksHistoryList().add(historyOfCrack);
        crackTip = coalescPolyline(obj1, obj2);
        historyOfCrack.add(new CrackHistory(this, surfaceAreaObj.getTimeIndex()));
        surfaceAreaObj.getCracksHistoryList().add(historyOfCrack);
    }

    public SemiellipticalCrack(SemiellipticalCrack obj1) {
        surfaceAreaObj = obj1.getSurfaceAreaObj();
        crackPoint = obj1.getCrackPoint();
        this.crackTip = obj1.crackTip;
        this.length2a = obj1.getLength2a();
        initLength2a = obj1.getInitLength2a();
        this.depthB = obj1.getDepthB();
        initDepthB = obj1.getInitDepthB();
        aspectRatio = obj1.getAspectRatio();
        this.timeIndex = obj1.getTimeIndex();
        rightTip = obj1.getRightTip();
        leftTip = obj1.leftTip;
        sigma = obj1.getSurfaceAreaObj().getSigma();
        sigmaYS = obj1.getSurfaceAreaObj().getYieldStress();
        k = obj1.getSurfaceAreaObj().getParametrK();
        initiationTimeObj = obj1.getSurfaceAreaObj().getTimeObj().getInitTime().get(timeIndex);

    }

    public double SIF_A() {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * depthB / length2a;
        final double b0 = 0.23153;
        final double b1 = 0.61945;
        final double b2 = -0.19862;
        final double b3 = 0.02754;
        final double b4 = 0.00137;
        KIA = b0 + b1 * lambda + b2 * lambda * lambda
                + b3 * Math.pow(lambda, 3) + b4 * Math.pow(lambda, 4);
        result = sigma * Math.sqrt(Math.PI * length2a / 2) * KIA;
        return result;
    }

    public double SIF_B() {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * depthB / length2a;
        final double b0 = 1.15713;
        final double b1 = -0.7302;
        final double b2 = 0.20827;
        KIB = b0 + b1 * lambda + b2 * lambda * lambda;
        result = sigma * Math.sqrt(Math.PI * depthB) * KIB;
        return result;
    }

    public double CriticalRadius(SemiellipticalCrack crack2) {
        double RC = 0;
        RC = (k / Math.PI) * Math.pow(this.SIF_A() / sigmaYS, 2)
                + (k / Math.PI) * Math.pow(crack2.SIF_A() / sigmaYS, 2);
//        System.out.println("\n\nCriticalRadius = " + RC);
//        System.out.println("this.SIF_A() = " + this.SIF_A());
//        System.out.println("depthB / length2a = " + 2 * depthB / length2a);
//        System.out.println("crack2.SIF_A() = " + crack2.SIF_A());
//        System.out.println("crack2.depthB / crack2.length2a = " + 2 * crack2.depthB / crack2.length2a);
        RC = 0.0001;
        return RC;
    }
//визначив чіткий приріст тріщини

    public boolean integrate(double currentTime, double deltaT) throws DerivativeException, IntegratorException {
        boolean result = true;
        double beforeGrowthLength = length2a;
        double beforeGrowthDepth = depthB;
//        FirstOrderIntegrator dp853 = new DormandPrince54Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
//        FirstOrderIntegrator dp853 = new DormandPrince54Integrator(1.0e-18, 1.0, 1.0e-20, 1.0e-20);
//        FirstOrderDifferentialEquations ode = new CrackOrderOde(k1SCC, this);
        double[] y = new double[]{beforeGrowthLength, beforeGrowthDepth}; // initial state
        double[] afterIntegr = new double[2]; // initial state
        double nextTime = currentTime + deltaT;
//        double stopTime = dp853.integrate(ode, currentTime, y, nextTime, afterIntegr); // now y contains final state at time t=16.0


        afterIntegr[0] = beforeGrowthLength + 2.0e-6;
        afterIntegr[1] = beforeGrowthDepth + 1.0e-6;
        crackTip.get(0).setX(crackTip.get(0).getX() - (afterIntegr[0] - y[0]) / 2);
        crackTip.get(crackTip.size() - 1).setX(crackTip.get(crackTip.size() - 1).getX() + (afterIntegr[0] - y[0]) / 2);
        setDepthB(afterIntegr[1]);

//        crackTip.get(0).setX(crackTip.get(0).getX());
//        crackTip.get(crackTip.size() - 1).setX(crackTip.get(crackTip.size() - 1).getX());
//        setDepthB(y[1]);

        this.length2a = crackTip.get(crackTip.size() - 1).getX() - crackTip.get(0).getX();
        timeIndex = timeIndex+1;
        historyOfCrack.add(new CrackHistory(this, surfaceAreaObj.getTimeIndex()));


//        System.out.println("\n\nObject=\t" + this.toString());
//        System.out.println("TimeIndex=\t" + this.getTimeIndex());
//        System.out.println("currentTime=\t" + currentTime);
//        System.out.println("deltaT=\t" + deltaT);
//        System.out.println("підростання довжини = " + (y[0]));
//        System.out.println("підростання глибини = " + (y[1]));



        return result;
    }

    public double da_dtKIrate(double K1) {
        double result = 0;
        result = 1.1 * Math.pow(10, -7.0)
                * Math.pow((2.5 * Math.pow(10.0, 10.0)
                * Math.exp(
                (-(3 * Math.pow(10.0, -19.0) - 1.5 * Math.pow(10.0, -20.0) * Math.pow((K1 - k1SCC), (1 / 3)))
                / 7.74 * Math.pow(10.0, -21.0)))), 0.443);
        return result;
    }

    public void setLength2a(double crackLength) {
        this.length2a = crackLength;
//        this.length2a = crackTip.get(crackTip.size()-1).getX() - crackTip.get(0).getX();
//        crackTip.get(0).setX(crackTip.get(0).getX() - y[0]/2);
//        crackTip.get(crackTip.size()-1).setX( + y[0]/2);
        rightTip = new Point((crackPoint.getX() + length2a / 2), crackPoint.getY());
        leftTip = new Point((crackPoint.getX() - length2a / 2), crackPoint.getY());
        historyOfCrack.add(new CrackHistory(this, surfaceAreaObj.getTimeIndex()));
    }

    private ArrayList<Point> coalescPolyline(SemiellipticalCrack obj1, SemiellipticalCrack obj2) {
        ArrayList<Point> polyline = new ArrayList<Point>();
        SemiellipticalCrack leftCrack;
        SemiellipticalCrack rigtCrack;
        if (obj1.getLeftTip().getX() <= obj1.getLeftTip().mostLeftP(obj2.getLeftTip()).getX()) {
            leftCrack = obj1;
            rigtCrack = obj2;
        } else {
            leftCrack = obj2;
            rigtCrack = obj1;
        }
        for (int i = 0; i < leftCrack.crackTip.size(); i++) {
            Point point = leftCrack.crackTip.get(i);
            polyline.add(point);
        }
        for (int i = 0; i < rigtCrack.crackTip.size(); i++) {
            Point point = rigtCrack.crackTip.get(i);
            polyline.add(point);
        }
        return polyline;
    }

    public int[][] getArrayPololine(int visualKValue) {
        int ks = crackTip.size();
        int[][] result = new int[2][ks];
//        int i = 0;
        for (int j = 0; j < crackTip.size(); j++) {
            Point point = crackTip.get(j);
            result[0][j] = (int) (point.getX() * visualKValue);
            result[1][j] = (int) (point.getY() * visualKValue);
        }
        return result;
    }

    public void setSurfaceAreaObj(SurfaceArea surfaceAreaObj) {
        this.surfaceAreaObj = surfaceAreaObj;
    }

    /**
     * Get the value of surfaceAreaObj
     *
     * @return the value of surfaceAreaObj
     */
    public SurfaceArea getSurfaceAreaObj() {
        return surfaceAreaObj;
    }

    /**
     * Get the value of aspectRatio
     *
     * @return the value of aspectRatio
     */
    public double getAspectRatio() {
        return aspectRatio;
    }

    /**
     * Set the value of aspectRatio
     *
     * @param aspectRatio new value of aspectRatio
     */
    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Get the value of crackLengthB
     *
     * @return the value of crackLengthB
     */
    public double getDepthB() {
        return depthB;
    }

    /**
     * Set the value of crackLengthB
     *
     * @param crackLengthB new value of crackLengthB
     */
    public void setDepthB(double crackDepthB) {
        this.depthB = crackDepthB;
    }

    /**
     * Get the value of crackLength
     *
     * @return the value of crackLength
     */
    public double getLength2a() {
//        crackTip.get(crackTip.size()-1).getX() - crackTip.get(0).getX()
        return length2a;
    }

    /**
     * Set the value of crackLength
     *
     * @param crackLength new value of crackLength
     */
    /**
     * Get the value of crackSiteX
     *
     * @return the value of crackSiteX
     */
    /**
     * Get the value of initiationTime
     *
     * @return the value of initiationTime
     */
    public double getInitiationTime() {
        return initiationTimeObj;
    }

    /**
     * Set the value of initiationTime
     *
     * @param initiationTime new value of initiationTime
     */
    public void setInitiationTime(double initiationTimeObj) {
        this.initiationTimeObj = initiationTimeObj;
    }

    public Point getCrackPoint() {
        return crackPoint;
    }

    public void setCrackPoint(Point crackPoint) {
        this.crackPoint = crackPoint;
    }

    public int getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(int timeIndex) {
        this.timeIndex = timeIndex;
    }

    public Point getLeftTip() {
        return leftTip;
    }

    public Point getRightTip() {
        return rightTip;
    }

    public double getSigma() {
        return sigma;
    }

//    public static ArrayList getHistoryOfCrack() {
//        return historyOfCrack;
//    }
//
//    public static void setHistoryOfCrack(ArrayList historyOfCrack) {
//        SemiellipticalCrack.historyOfCrack = historyOfCrack;
//    }
    public double getInitDepthB() {
        return initDepthB;
    }

    public void setInitDepthB(double initDepthB) {
        this.initDepthB = initDepthB;
    }

    public double getInitLength2a() {
        return initLength2a;
    }

    public void setInitLength2a(double initLength2a) {
        this.initLength2a = initLength2a;
    }

    public boolean isMaxLength() {
        return maxLength;
    }

    public void setMaxLength(boolean maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isCoalescenced() {
        return coalescenced;
    }

    public ArrayList<Point> getCrackTip() {
        return crackTip;
    }
}
