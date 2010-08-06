/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
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
public class SemiellipticalCrack implements Externalizable {
//    private int timeIndx;

    private double depthB;
    private double aspectRatio;
    private ArrayList<Point> crackTip;

    public SemiellipticalCrack() {
        this(0, 0, new ArrayList<Point>());
    }

    public SemiellipticalCrack(double depthB, double aspectRatio, ArrayList<Point> crackTip) {
        this.depthB = depthB;
        this.aspectRatio = aspectRatio;
        this.crackTip = crackTip;
    }

    public SemiellipticalCrack(Point[] lrPoint, double depthB, int timeIndex) {
//        this.timeIndx = timeIndex;
        crackTip = new ArrayList<Point>();
        crackTip.add(lrPoint[0]);
        crackTip.add(lrPoint[1]);
        this.depthB = depthB;
        aspectRatio = depthB / (this.getLength2a() / 2);
    }

    public SemiellipticalCrack(SemiellipticalCrack obj1, SemiellipticalCrack obj2, int timeIndex) {
//        this.timeIndx = timeIndex;
        crackTip = obj1.getCrackTip();
        crackTip.addAll(obj2.getCrackTip());
        this.depthB = Math.max(obj1.getDepthB(), obj2.getDepthB());
        aspectRatio = depthB / (this.getLength2a() / 2);
        this.checkMaxCondition();

    }

    public double SIF_A() {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * depthB / this.getLength2a();
        KIA = Const.k1_A_b0 + Const.k1_A_b1 * lambda + Const.k1_A_b2 * lambda * lambda
                + Const.k1_A_b3 * Math.pow(lambda, 3) + Const.k1_A_b4 * Math.pow(lambda, 4);
        result = Simulation.getSigma() * Math.sqrt(Math.PI * this.getLength2a() / 2) * KIA;
        return result;
    }

    public double SIF_B() {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * depthB / this.getLength2a();
        KIB = Const.k1_B_b0 + Const.k1_B_b1 * lambda + Const.k1_B_b2 * lambda * lambda;
        result = Simulation.getSigma() * Math.sqrt(Math.PI * depthB) * KIB;
        return result;
    }

    public double CriticalRadius(SemiellipticalCrack crack2) {
        double RC = 0;
        RC = (Simulation.getParametrK() / Math.PI) *
                (
                (this.SIF_A() / Simulation.getYieldStress()) *
                (this.SIF_A() / Simulation.getYieldStress()) +
                (crack2.SIF_A() / Simulation.getYieldStress()) *
                (crack2.SIF_A() / Simulation.getYieldStress())
                );
//        System.out.println("\n\nCriticalRadius = " + RC);
//        System.out.println("this.SIF_A() = " + this.SfIF_A());
//        System.out.println("depthB / length2a = " + 2 * depthB / length2a);
//        System.out.println("crack2.SIF_A() = " + crack2.SIF_A());
//        System.out.println("crack2.depthB / crack2.length2a = " + 2 * crack2.depthB / crack2.length2a);
//        RC = 1.0e-4;
        return RC;
    }
//визначив чіткий приріст тріщини

    public boolean integrate(int tIndx, double currentTime, double deltaT) throws DerivativeException, IntegratorException {
//        this.timeIndx = tIndx;
        boolean result = true;
        double beforeGrowthLength_a = this.getLength2a()/2;
        double beforeGrowthDepth = depthB;
//        double maxStep = deltaT;
//        deltaT = 1.0e-4;

        FirstOrderIntegrator dp54 = new DormandPrince54Integrator(deltaT * 0.1, deltaT, 1.0e-10, 1.0e-10);
//        FirstOrderIntegrator dp853 = new DormandPrince54Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        FirstOrderDifferentialEquations ode = new CrackOrderOde(Const.k1SCC, this);
        double[] y = new double[]{beforeGrowthLength_a, beforeGrowthDepth}; // initial state
        double[] afterIntegr = new double[2]; // initial state

        double nextTime = currentTime + deltaT;
        double stopTime = dp54.integrate(ode, currentTime, y, nextTime, afterIntegr); // now y contains final state at time t=16.0
        double changeofLength = afterIntegr[0] - y[0];
//        System.out.println("\ndeltaT=\t" + deltaT);
//        System.out.println("changeofLength=\t" + changeofLength);

//        afterIntegr[0] = beforeGrowthLength + 1.0e-6;
//        afterIntegr[1] = beforeGrowthDepth + 1.0e-6;
        double growthX = (afterIntegr[0] - y[0])/2;
        double depth = afterIntegr[1];
        double xLeft = crackTip.get(0).getX() - growthX;
        double xRight = crackTip.get(crackTip.size() - 1).getX() + growthX;
        if (xLeft < 0) {
            xLeft = 0;
        }
        if (xRight > Simulation.getSurface().getWidth()) {
            xRight = Simulation.getSurface().getWidth();
        }

        crackTip.get(0).setX(xLeft);
        crackTip.get(crackTip.size() - 1).setX(xRight);
        setDepthB(afterIntegr[1]);
        aspectRatio = depthB / (this.getLength2a() / 2);


        this.checkMaxCondition();
        return result;
    }

    public int[][] getArrayPololine(int visualKValue) {
        int ks = crackTip.size();
        int[][] result = new int[2][ks];

        for (int j = 0; j < crackTip.size(); j++) {
            Point point = crackTip.get(j);
            result[0][j] = (int) (point.getX() * visualKValue);
            result[1][j] = (int) (point.getY() * visualKValue);
        }
        return result;
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
        setDepthB(getAspectRatio()*(getLength2a()/2));
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
        aspectRatio = depthB / (this.getLength2a() / 2);
    }

    /**
     * Get the value of crackLength
     *
     * @return the value of crackLength
     */
    public final double getLength2a() {
//        crackTip.get(crackTip.size()-1).getX() - crackTip.get(0).getX()
        return crackTip.get(crackTip.size() - 1).getX() - crackTip.get(0).getX();
    }

    public Point getLeftTip() {
        return crackTip.get(0);
    }

    public Point getRightTip() {
        return crackTip.get(crackTip.size() - 1);
    }

    public ArrayList<Point> getCrackTip() {
        return crackTip;
    }

//    public int getTimeIndx() {
//        return timeIndx;
//    }
    /**
     *
     * Check max length condition
     * used when crack growth and coalescence
     */
    public final boolean checkMaxCondition() {
//        boolean result = false;
        if (Simulation.getMaxCrackLength() <= this.getLength2a()) {
            Simulation.setMaxLengthCondition(true);
            return true;
        }
        return false;
    }

    public void writeExternal(ObjectOutput oo) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet.");
//        oo.writeInt(timeIndx);
        oo.writeDouble(depthB);
        oo.writeDouble(aspectRatio);
        oo.writeInt(crackTip.size());

        for (Externalizable ext : crackTip) {
            ext.writeExternal(oo);
        }

    }

    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet.");
//        timeIndx = oi.readInt();
        depthB = oi.readDouble();
        aspectRatio = oi.readDouble();
        int count = oi.readInt();

        for (int i = 0; i < count; i++) {
            Point ext = new Point();
            ext.readExternal(oi);
            crackTip.add(ext);
        }
    }
}
