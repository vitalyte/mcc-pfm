/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.integration;

import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import probabilistic.Const;
import probabilistic.SemiellipticalCrack;
import probabilistic.Simulation;

/**
 *
 * @author Vitaly
 */
public class CrackOrderOde implements FirstOrderDifferentialEquations {

    private double k1SCC;

    public CrackOrderOde(double k1SCC, SemiellipticalCrack crack) {
        this.k1SCC = k1SCC;

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
        double value = K1array(y)[0] - k1SCC;
        yDot[0] = 1.1 * Const.E10P_7
                * Math.pow((2.5 * Const.E10P10
                * Math.exp(
                (-(3 * Const.E10P_19 - 1.5 * Const.E10P_20 * Math.pow((K1array(y)[0] - k1SCC), (1 / 3)))
                / (7.74 * Const.E10P_21)))), 0.443);
        yDot[1] = 1.1 * Const.E10P_7
                * Math.pow((2.5 * Const.E10P10
                * Math.exp(
                (-(3 * Const.E10P_19 - 1.5 * Const.E10P_20 * Math.pow((K1array(y)[1] - k1SCC), (1 / 3)))
                / (7.74 * Const.E10P_21)))), 0.443);

    }

    private double[] K1array(double[] y) {
        double[] result = {k1_A(y), k1_B(y)};
        return result;
    }

    private double k1_A(double[] y) {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * y[1] / y[0];
        KIA = Const.k1_A_b0 + Const.k1_A_b1 * lambda + Const.k1_A_b2 * lambda * lambda
                + Const.k1_A_b3 * Math.pow(lambda, 3) + Const.k1_A_b4 * Math.pow(lambda, 4);
        result = Simulation.getSigma() * Math.sqrt(Math.PI * y[0] / 2) * KIA;
        return result;
    }

    private double k1_B(double[] y) {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * y[1] / y[0];
        KIB = Const.k1_B_b0 + Const.k1_B_b1 * lambda + Const.k1_B_b2 * lambda * lambda;
        result = Simulation.getSigma() * Math.sqrt(Math.PI * y[1]) * KIB;
        return result;
    }
}
