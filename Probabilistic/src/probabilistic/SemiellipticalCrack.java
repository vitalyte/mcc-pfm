/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

import probabilistic.integration.CrackOrderOde;
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

    private double length2a, initLength2a;
    private double depthB, initDepthB;
    private double aspectRatio;
    private Point crackPoint;
    private Point rightTip;
    private ArrayList<Point> crackTip;
    private Point leftTip;
    private boolean coalescenced = false;
    //list changes of cracks states
    private ArrayList<CrackHistory> historyOfCrack;

    public SemiellipticalCrack() {
        historyOfCrack = new ArrayList<CrackHistory>();
        crackTip = new ArrayList<Point>();
    }

    public SemiellipticalCrack(double pointX, double pointY,
            double length2a, double depthB, int timeIndex) {
        this();
        crackPoint = new Point(pointX, pointY);
        leftTip = new Point((crackPoint.getX() - length2a / 2), crackPoint.getY());
        rightTip = new Point((crackPoint.getX() + length2a / 2), crackPoint.getY());
        crackTip.add(leftTip);
        crackTip.add(rightTip);
        this.depthB = depthB;
        this.length2a = length2a;
        initLength2a = length2a;
        initDepthB = depthB;
        aspectRatio = depthB / length2a / 2;
//        aspectRatio = 1;
        historyOfCrack.add(new CrackHistory(this, timeIndex));
        Simulation.getCracksHistoryList().add(historyOfCrack);

    }

    public SemiellipticalCrack(SemiellipticalCrack obj1, SemiellipticalCrack obj2, int timeIndex) {
        this();
        crackTip = new ArrayList<Point>();
        crackTip.addAll(obj1.getCrackTip());
        crackTip.addAll(obj2.getCrackTip());
        leftTip = crackTip.get(0);
        rightTip = crackTip.get(crackTip.size() - 1);
        this.length2a = crackTip.get(crackTip.size() - 1).getX() - crackTip.get(0).getX();
        this.depthB = Math.max(obj1.getDepthB(), obj2.getDepthB());
        aspectRatio = depthB / length2a / 2;
        coalescenced = true;
        historyOfCrack.add(new CrackHistory(this, timeIndex));
        Simulation.getCracksHistoryList().add(historyOfCrack);
    }

    public SemiellipticalCrack(SemiellipticalCrack obj1) {
        crackPoint = obj1.getCrackPoint();
        this.crackTip = obj1.crackTip;
        this.length2a = obj1.getLength2a();
        initLength2a = obj1.getInitLength2a();
        this.depthB = obj1.getDepthB();
        initDepthB = obj1.getInitDepthB();
        aspectRatio = obj1.getAspectRatio();
        rightTip = obj1.getRightTip();
        leftTip = obj1.leftTip;


    }

    public double SIF_A() {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * depthB / length2a;
        KIA = Const.k1_A_b0 + Const.k1_A_b1 * lambda + Const.k1_A_b2 * lambda * lambda
                + Const.k1_A_b3 * Math.pow(lambda, 3) + Const.k1_A_b4 * Math.pow(lambda, 4);
        result = Simulation.getSigma() * Math.sqrt(Math.PI * length2a / 2) * KIA;
        return result;
    }

    public double SIF_B() {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * depthB / length2a;

        KIB = Const.k1_B_b0 + Const.k1_B_b1 * lambda + Const.k1_B_b2 * lambda * lambda;
        result = Simulation.getSigma() * Math.sqrt(Math.PI * depthB) * KIB;
        return result;
    }

    public double CriticalRadius(SemiellipticalCrack crack2) {
        double RC = 0;
        RC = (Simulation.getParametrK() / Math.PI) * (this.SIF_A() / Simulation.getYieldStress()) * (this.SIF_A() / Simulation.getYieldStress())
                + (Simulation.getParametrK() / Math.PI) * (crack2.SIF_A() / Simulation.getYieldStress()) * (crack2.SIF_A() / Simulation.getYieldStress());
//        System.out.println("\n\nCriticalRadius = " + RC);
//        System.out.println("this.SIF_A() = " + this.SfIF_A());
//        System.out.println("depthB / length2a = " + 2 * depthB / length2a);
//        System.out.println("crack2.SIF_A() = " + crack2.SIF_A());
//        System.out.println("crack2.depthB / crack2.length2a = " + 2 * crack2.depthB / crack2.length2a);
        RC = 1.0e-6;
        return RC;
    }
//визначив чіткий приріст тріщини

    public boolean integrate(int tIndx, double currentTime, double deltaT) throws DerivativeException, IntegratorException {
        boolean result = true;
        double beforeGrowthLength = length2a;
        double beforeGrowthDepth = depthB;
//        double maxStep = deltaT;
//        deltaT = 1.0e-4;

//        FirstOrderIntegrator dp54 = new DormandPrince54Integrator(deltaT, deltaT, 1.0e-10, 1.0e-10);
//        FirstOrderIntegrator dp853 = new DormandPrince54Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
//        FirstOrderDifferentialEquations ode = new CrackOrderOde(Const.k1SCC, this);
        double[] y = new double[]{beforeGrowthLength, beforeGrowthDepth}; // initial state
        double[] afterIntegr = new double[2]; // initial state

        double nextTime = currentTime + deltaT;
//        double stopTime = dp54.integrate(ode, currentTime, y, nextTime, afterIntegr); // now y contains final state at time t=16.0
        double changeofLength = afterIntegr[0] - y[0];
//        System.out.println("\ndeltaT=\t" + deltaT);
//        System.out.println("changeofLength=\t" + changeofLength);


        afterIntegr[0] = beforeGrowthLength + 1.0e-6;
        afterIntegr[1] = beforeGrowthDepth + 1.0e-6;
        double growthX = afterIntegr[0] - y[0];
        double depth = afterIntegr[1];
        double xLeft = crackTip.get(0).getX() - growthX / 2;
        double xRight = crackTip.get(crackTip.size() - 1).getX() + growthX / 2;
        if (xLeft < 0) {
            xLeft = 0;
        }
        if (xRight > Simulation.getSurface().getWidth()) {
            xRight = Simulation.getSurface().getWidth();
        }

        crackTip.get(0).setX(xLeft);
        crackTip.get(crackTip.size() - 1).setX(xRight);
        setDepthB(afterIntegr[1]);

        this.length2a = crackTip.get(crackTip.size() - 1).getX() - crackTip.get(0).getX();
        historyOfCrack.add(new CrackHistory(this, tIndx + 1));






        return result;
    }

    public double da_dtKIrate(double K1) {
        double result = 0;
        result = 1.1 * Math.pow(10, -7.0)
                * Math.pow((2.5 * Math.pow(10.0, 10.0)
                * Math.exp(
                (-(3 * Math.pow(10.0, -19.0) - 1.5 * Math.pow(10.0, -20.0) * Math.pow((K1 - Const.k1SCC), (1 / 3)))
                / 7.74 * Math.pow(10.0, -21.0)))), 0.443);
        return result;
    }

//    public void setLength2a(double crackLength) {
//        this.length2a = crackLength;
////        this.length2a = crackTip.get(crackTip.size()-1).getX() - crackTip.get(0).getX();
////        crackTip.get(0).setX(crackTip.get(0).getX() - y[0]/2);
////        crackTip.get(crackTip.size()-1).setX( + y[0]/2);
//        rightTip = new Point((crackPoint.getX() + length2a / 2), crackPoint.getY());
//        leftTip = new Point((crackPoint.getX() - length2a / 2), crackPoint.getY());
//        historyOfCrack.add(new CrackHistory(this, Simulation.getTimeIndex()));
//    }
//    private ArrayList<Point> coalescPolyline(SemiellipticalCrack obj1_, SemiellipticalCrack obj2_) {
//        ArrayList<Point> polyline = new ArrayList<Point>();
//
////        if (obj1.getLeftTip().getX() <= obj1.getLeftTip().mostLeftP(obj2.getLeftTip()).getX()) {
////            leftCrack = obj1;
////            rigtCrack = obj2;
////        } else {
////            leftCrack = obj2;
////            rigtCrack = obj1;
////        }
//        for (int i = 0; i < obj1_.crackTip.size(); i++) {
//            Point point = obj1_.crackTip.get(i);
//            polyline.add(point);
//        }
//        for (int i = 0; i < obj2_.crackTip.size(); i++) {
//            Point point = obj2_.crackTip.get(i);
//            polyline.add(point);
//        }
//        return polyline;
//    }
//
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
//    public void setSurfaceAreaObj(SurfaceArea surfaceAreaObj) {
//        this.Simulation = surfaceAreaObj;
//    }
//
//    /**
//     * Get the value of surfaceAreaObj
//     *
//     * @return the value of surfaceAreaObj
//     */
//    public SurfaceArea getSurfaceAreaObj() {
//        return Simulation;
//    }
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

    public Point getCrackPoint() {
        return crackPoint;
    }

    public void setCrackPoint(Point crackPoint) {
        this.crackPoint = crackPoint;
    }
//
//    public int getTimeIndex() {
//        return timeIndex;
//    }
//
//    public void setTimeIndex(int timeIndex) {
//        this.timeIndex = timeIndex;
//    }

    public Point getLeftTip() {
        return leftTip;
    }

    public Point getRightTip() {
        return rightTip;
    }
//
//    public double getSigma() {
//        return sigma;
//    }

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

//    public boolean isMaxLength() {
//        return maxLength;
//    }
//
//    public void setMaxLength(boolean maxLength) {
//        this.maxLength = maxLength;
//    }
    public boolean isCoalescenced() {
        return coalescenced;
    }

    public ArrayList<Point> getCrackTip() {
        return crackTip;
    }
}
