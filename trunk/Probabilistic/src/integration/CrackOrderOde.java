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

    private static final double E10P_7 = 0.0000001;
    private static final double E10P_19 = 0.0000000000000000001;
    private static final double E10P_20 = 0.00000000000000000001;
    private static final double E10P_21 = 0.000000000000000000001;
    private static final double E10P10 = 10000000000.0;
    private double[] K1 = new double[2];
    private double k1SCC = 2;
//            *Math.pow(10, 6);
    private double sigma;

    public CrackOrderOde(double k1SCC, SemiellipticalCrack crack) {
        this.K1[0] = crack.SIF_A();
        this.K1[1] = crack.SIF_B();
        //this.K1     = c;
        this.k1SCC = k1SCC;
        sigma = crack.getSigma();

    }

    public int getDimension() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return 2;
    }

    public void computeDerivatives(double t, double[] y, double[] yDot) throws DerivativeException {
        //throw new UnsupportedOperationException("Not supported yet.");
        //
        yDot[0] = 1.1 * Math.pow(10, -7.0)
                * Math.pow((2.5 * Math.pow(10.0, 10.0)
                * Math.exp(
                (-(3 * Math.pow(10.0, -19.0) - 1.5 * Math.pow(10.0, -20.0) * Math.pow((K1array(y)[0] - k1SCC), (1 / 3)))
                / 7.74 * Math.pow(10.0, -21.0)))), 0.443);
        yDot[1] = 1.1 * Math.pow(10, -7.0)
                * Math.pow((2.5 * Math.pow(10.0, 10.0)
                * Math.exp(
                (-(3 * Math.pow(10.0, -19.0) - 1.5 * Math.pow(10.0, -20.0) * Math.pow((K1array(y)[1] - k1SCC), (1 / 3)))
                / 7.74 * Math.pow(10.0, -21.0)))), 0.443);

    }
    /*
     *  y[0] - length2a
     * y[1] - depth
     */

    private double[] K1array(double[] y) {
        double[] result = {k1_A(y), k1_B(y)};

        return result;
    }

    private double k1_A(double[] y) {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * y[1] / y[0];
        final double b0 = 0.23153;
        final double b1 = 0.61945;
        final double b2 = -0.19862;
        final double b3 = 0.02754;
        final double b4 = 0.00137;
        KIA = b0 + b1 * lambda + b2 * lambda * lambda
                + b3 * Math.pow(lambda, 3) + b4 * Math.pow(lambda, 4);
        result = sigma * Math.sqrt(Math.PI * y[0] / 2) * KIA;
        return result;
    }

    private double k1_B(double[] y) {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * y[1] / y[0];
        final double b0 = 1.15713;
        final double b1 = -0.7302;
        final double b2 = 0.20827;
        KIB = b0 + b1 * lambda + b2 * lambda * lambda;
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
