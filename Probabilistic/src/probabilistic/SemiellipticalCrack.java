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

//private static final long serialVersionUID;
    private double length2a;
    private double depthB;
    private double aspectRatio;
    private ArrayList<Point> crackTip;
    private boolean coalescenced = false;
    private boolean maxLength = false;



    public SemiellipticalCrack() {
        this(0, 0, 0, new ArrayList<Point>());
    }

    public SemiellipticalCrack(double length2a, double depthB, double aspectRatio, ArrayList<Point> crackTip) {
        this.length2a = length2a;
        this.depthB = depthB;
        this.aspectRatio = aspectRatio;
        this.crackTip = crackTip;
    }

    public SemiellipticalCrack(double pointX, double pointY,
            double length2a, double depthB, int timeIndex) {
        crackTip = new ArrayList<Point>();

        Point crackPoint = new Point(pointX, pointY);
        crackTip.add(new Point((crackPoint.getX() - length2a / 2), crackPoint.getY()));
        crackTip.add(new Point((crackPoint.getX() + length2a / 2), crackPoint.getY()));
//        crackTip.add(leftTip);
//        crackTip.add(rightTip);
        this.depthB = depthB;
        this.length2a = length2a;
        aspectRatio = depthB / length2a / 2;
    }

    public SemiellipticalCrack(SemiellipticalCrack obj1, SemiellipticalCrack obj2, int timeIndex) {

        crackTip = obj1.getCrackTip();
        crackTip.addAll(obj2.getCrackTip());
        this.length2a = crackTip.get(crackTip.size() - 1).getX() - crackTip.get(0).getX();
        this.depthB = Math.max(obj1.getDepthB(), obj2.getDepthB());
        aspectRatio = depthB / length2a / 2;
        coalescenced = true;

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
//        RC = 1.0e-4;
        return RC;
    }
//визначив чіткий приріст тріщини

    public boolean integrate(int tIndx, double currentTime, double deltaT) throws DerivativeException, IntegratorException {
        boolean result = true;
        double beforeGrowthLength = length2a;
        double beforeGrowthDepth = depthB;
//        double maxStep = deltaT;
//        deltaT = 1.0e-4;

        FirstOrderIntegrator dp54 = new DormandPrince54Integrator(deltaT * 0.1, deltaT, 1.0e-10, 1.0e-10);
//        FirstOrderIntegrator dp853 = new DormandPrince54Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        FirstOrderDifferentialEquations ode = new CrackOrderOde(Const.k1SCC, this);
        double[] y = new double[]{beforeGrowthLength, beforeGrowthDepth}; // initial state
        double[] afterIntegr = new double[2]; // initial state

        double nextTime = currentTime + deltaT;
        double stopTime = dp54.integrate(ode, currentTime, y, nextTime, afterIntegr); // now y contains final state at time t=16.0
        double changeofLength = afterIntegr[0] - y[0];
//        System.out.println("\ndeltaT=\t" + deltaT);
//        System.out.println("changeofLength=\t" + changeofLength);

//        afterIntegr[0] = beforeGrowthLength + 1.0e-6;
//        afterIntegr[1] = beforeGrowthDepth + 1.0e-6;
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

    public Point getLeftTip() {
        return crackTip.get(0);
    }

    public Point getRightTip() {
        return crackTip.get(crackTip.size() - 1);
    }

    public boolean isCoalescenced() {
        return coalescenced;
    }

    public ArrayList<Point> getCrackTip() {
        return crackTip;
    }

    public boolean isMaxLength() {
        return maxLength;
    }

    public void setMaxLength(boolean maxLength) {
        this.maxLength = maxLength;
    }


    public void writeExternal(ObjectOutput oo) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet.");
        oo.writeDouble(length2a);
        oo.writeDouble(depthB);
        oo.writeDouble(aspectRatio);
        oo.writeInt(crackTip.size());
        oo.writeBoolean(coalescenced);
        oo.writeBoolean(maxLength);

        for (Externalizable ext : crackTip) {
            ext.writeExternal(oo);
        }
        
    }

    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet.");
        length2a = oi.readDouble();
        depthB = oi.readDouble();
        aspectRatio = oi.readDouble();
        int count = oi.readInt();
        coalescenced = oi.readBoolean();
        maxLength = oi.readBoolean();

        for (int i = 0; i < count; i++) {
            Point ext = new Point();
            ext.readExternal(oi);
            crackTip.add(ext);
        }
    }
}
