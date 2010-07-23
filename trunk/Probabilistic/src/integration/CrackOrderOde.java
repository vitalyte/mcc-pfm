/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import probabilistic.gui.SemiellipticalCrack;

/**
 *
 * @author Vitaly
 */
public class CrackOrderOde implements FirstOrderDifferentialEquations {

    private static final double E10P_7 = 1.0e-7;
    private static final double E10P_19 = 1.0e-19;
    private static final double E10P_20 = 1.0e-20;
    private static final double E10P_21 = 1.0e-21;
    private static final double E10P10 = 1.0e10;

    private static final double k1_A_b0 = 0.23153;
    private static final double k1_A_b1 = 0.61945;
    private static final double k1_A_b2 = -0.19862;
    private static final double k1_A_b3 = 0.02754;
    private static final double k1_A_b4 = 0.00137;
    private static final double k1_B_b0 = 1.15713;
    private static final double k1_B_b1 = -0.7302;
    private static final double k1_B_b2 = 0.20827;
//    private double[] K1 = new double[2];
    private double k1SCC;
    private double sigma;

    public CrackOrderOde(double k1SCC, SemiellipticalCrack crack) {
//        this.K1[0] = crack.SIF_A();
//        this.K1[1] = crack.SIF_B();
        //this.K1     = c;
        this.k1SCC = k1SCC;
        sigma = crack.getSigma();

    }

    public int getDimension() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return 2;
    }
    /*
     *  y[0] - length2a
     * y[1] - depth
     */

    public void computeDerivatives(double t, double[] y, double[] yDot) throws DerivativeException {
        //throw new UnsupportedOperationException("Not supported yet.");
        //
        yDot[0] = 1.1 * E10P_7
                * Math.pow((2.5 * E10P10
                * Math.exp(
                (-(3 * E10P_19 - 1.5 * E10P_20 * Math.pow((K1array(y)[0] - k1SCC), (1 / 3)))
                / 7.74 * E10P_21))), 0.443);
        yDot[1] = 1.1 * E10P_7
                * Math.pow((2.5 * E10P10
                * Math.exp(
                (-(3 * E10P_19 - 1.5 * E10P_20 * Math.pow((K1array(y)[1] - k1SCC), (1 / 3)))
                / 7.74 * E10P_21))), 0.443);

    }

    private double[] K1array(double[] y) {
        double[] result = {k1_A(y), k1_B(y)};
        return result;
    }

    private double k1_A(double[] y) {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * y[1] / y[0];
//        final double k1_A_b0 = 0.23153;
//        final double k1_A_b1 = 0.61945;
//        final double k1_A_b2 = -0.19862;
//        final double k1_A_b3 = 0.02754;
//        final double k1_A_b4 = 0.00137;
        KIA = k1_A_b0 + k1_A_b1 * lambda + k1_A_b2 * lambda * lambda
                + k1_A_b3 * Math.pow(lambda, 3) + k1_A_b4 * Math.pow(lambda, 4);
        result = sigma * Math.sqrt(Math.PI * y[0] / 2) * KIA;
        return result;
    }

    private double k1_B(double[] y) {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * y[1] / y[0];
//        final double k1_B_b0 = 1.15713;
//        final double k1_B_b1 = -0.7302;
//        final double k1_B_b2 = 0.20827;
        KIB = k1_B_b0 + k1_B_b1 * lambda + k1_B_b2 * lambda * lambda;
        result = sigma * Math.sqrt(Math.PI * y[1]) * KIB;
        return result;
    }

    /**
     * Set the value of k1SCC
     *
     * @param k1SCC new value of k1SCC
     */
    public void setK1SCC(double k1SCC) {
        this.k1SCC = k1SCC;
    }
}
