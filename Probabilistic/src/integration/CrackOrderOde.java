/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;

/**
 *
 * @author Vitaly
 */
public class CrackOrderOde implements FirstOrderDifferentialEquations {

    private double[] k1;
    private double k1SCC;

    public CrackOrderOde(double[] k1, double k1SCC) {
        this.k1 = k1;
        this.k1SCC = k1SCC;

    }

    public int getDimension() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return 2;
    }

    public void computeDerivatives(double t, double[] y, double[] yDot) throws DerivativeException {
        //throw new UnsupportedOperationException("Not supported yet.");
        yDot[0] = 1.1 * Math.pow(10, -7.0)
                * Math.pow((2.5 * Math.pow(10.0, 10.0)
                * Math.exp(
                (-(3 * Math.pow(10.0, -19.0) - 1.5 * Math.pow(10.0, -20.0) * Math.pow((k1[0] - k1SCC), (1 / 3)))
                / 7.74 * Math.pow(10.0, -21.0)))), 0.443);
        yDot[1] = 1.1 * Math.pow(10, -7.0)
                * Math.pow((2.5 * Math.pow(10.0, 10.0)
                * Math.exp(
                (-(3 * Math.pow(10.0, -19.0) - 1.5 * Math.pow(10.0, -20.0) * Math.pow((k1[1] - k1SCC), (1 / 3)))
                / 7.74 * Math.pow(10.0, -21.0)))), 0.443);

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
