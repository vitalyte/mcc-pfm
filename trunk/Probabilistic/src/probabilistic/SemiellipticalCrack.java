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

    private double depthB;
    private double aspectRatio;
    private ArrayList<Point> crackTip;

    /**
     * Constructor for Externalizable
     */
    public SemiellipticalCrack() {
        this(0, 0, new ArrayList<Point>());
    }

    /**
     * Constructor for Externalizable
     */
    public SemiellipticalCrack(double depthB, double aspectRatio, ArrayList<Point> crackTip) {
        this.depthB = depthB;
        this.aspectRatio = aspectRatio;
        this.crackTip = crackTip;
    }
    /**
     * Constructor for new initiated crack
     */
    public SemiellipticalCrack(Point[] lrPoint, double depthB) {
        crackTip = new ArrayList<Point>();
        crackTip.add(lrPoint[0]);
        crackTip.add(lrPoint[1]);
        this.depthB = depthB;
        aspectRatio = depthB / (this.getLength2a() / 2);
    }

    /**
     * Constructor for coalescence
     */
    public SemiellipticalCrack(SemiellipticalCrack obj1, SemiellipticalCrack obj2) {
        crackTip = obj1.getCrackTips();
        crackTip.addAll(obj2.getCrackTips());
        this.depthB = Math.max(obj1.getDepthB(), obj2.getDepthB());
        aspectRatio = depthB / (this.getLength2a() / 2);
        this.checkMaxCondition();

    }

    /**
     * stress intensity factor, K1A
     * @return stress intensity factor, K1A
     */
    public double SIF_A() {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * depthB / this.getLength2a();
        KIA = Const.k1_A_b0 + Const.k1_A_b1 * lambda + Const.k1_A_b2 * lambda * lambda
                + Const.k1_A_b3 * Math.pow(lambda, 3) + Const.k1_A_b4 * Math.pow(lambda, 4);
        result = Simulation.getSigma() * Math.sqrt(Math.PI * this.getLength2a() / 2) * KIA;
        return result;
    }

    /**
     * stress intensity factor, K1B
     * @return stress intensity factor, K1B
     */
    public double SIF_B() {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * depthB / this.getLength2a();
        KIB = Const.k1_B_b0 + Const.k1_B_b1 * lambda + Const.k1_B_b2 * lambda * lambda;
        result = Simulation.getSigma() * Math.sqrt(Math.PI * depthB) * KIB;
        return result;
    }

    /**
     *
     * @param crack2
     * @return critical radius for this pair of cracks (this and crack2)
     */
    public double CriticalRadius(SemiellipticalCrack crack2) {
        double RC = 0;
        RC = (Simulation.getParametrK() / Math.PI)
                * ((this.SIF_A() / Simulation.getYieldStress())
                * (this.SIF_A() / Simulation.getYieldStress())
                + (crack2.SIF_A() / Simulation.getYieldStress())
                * (crack2.SIF_A() / Simulation.getYieldStress()));
        return RC;
    }

    /**
     * Realize growth of crack (If crack satisfy the critical length or critical condition KISCC)
     * @param currentTime
     * @param deltaT
     * @return true if crack Growth
     * @throws DerivativeException
     * @throws IntegratorException
     */
    public boolean integrate(double currentTime, double deltaT) throws DerivativeException, IntegratorException {
        if (SIF_A() >= Const.k1SCC || SIF_B() >= Const.k1SCC) {
            double beforeGrowthLength_a = this.getLength2a() / 2;
            double beforeGrowthDepth = depthB;
            FirstOrderIntegrator dp54 = new DormandPrince54Integrator(deltaT * 0.1, deltaT, 1.0e-10, 1.0e-10);
            FirstOrderDifferentialEquations ode = new CrackOrderOde(Const.k1SCC, this);
            double[] y = new double[]{beforeGrowthLength_a, beforeGrowthDepth}; // initial state
            double[] afterIntegr = new double[2];
            double nextTime = currentTime + deltaT;
            double stopTime = dp54.integrate(ode, currentTime, y, nextTime, afterIntegr); // now y contains final state at time t=16.0
            double changeofLength = afterIntegr[0] - y[0];
            double growthX = (afterIntegr[0] - y[0]) / 2;
            double depth = afterIntegr[1];
            double xLeft = crackTip.get(0).getX() - growthX;
            double xRight = crackTip.get(crackTip.size() - 1).getX() + growthX;
            if (xLeft < 0) {
                xLeft = 0;
            }
            if (xRight > Simulation.getSurface().getWidth()) {
                xRight = Simulation.getSurface().getWidth();
            }
            crackTip.get(0).setLocation(xLeft, crackTip.get(0).getY());
            crackTip.get(crackTip.size() - 1).setLocation(xRight, crackTip.get(crackTip.size() - 1).getY());
            setDepthB(afterIntegr[1]);
            aspectRatio = depthB / (this.getLength2a() / 2);
            this.checkMaxCondition();
            return true;
        }

        return false;
    }

    /**
     *
     * @param visualKValue
     * @return Array of points for drawing Polyline
     */
    public int[][] getArrayPolyline(int visualKValue) {
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
        setDepthB(getAspectRatio() * (getLength2a() / 2));
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

    /**
     *
     * @return Point of left tip
     */
    public Point getLeftTip() {
        return crackTip.get(0);
    }

    /**
     *
     * @return Point of right tip
     */
    public Point getRightTip() {
        return crackTip.get(crackTip.size() - 1);
    }

    /**
     *
     * @return list of crack Points
     */
    public ArrayList<Point> getCrackTips() {
        return crackTip;
    }

    /**
     *
     * Check max length condition
     * used when crack growth and coalescence
     */
    public final boolean checkMaxCondition() {
        if (Simulation.getMaxCrackLength() <= this.getLength2a()) {
            Simulation.setMaxLengthCondition(true);
            return true;
        }
        return false;
    }

    /**
     * realize Externalizable
     * write to file
     * @param oo
     * @throws IOException
     */
    public void writeExternal(ObjectOutput oo) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet.");
        oo.writeDouble(depthB);
        oo.writeDouble(aspectRatio);
        oo.writeInt(crackTip.size());
        for (Externalizable ext : crackTip) {
            ext.writeExternal(oo);
        }

    }

    /**
     * realize Externalizable
     * read to file
     * @param oi
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet.");
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
