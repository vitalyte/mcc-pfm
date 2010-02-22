/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 * 
 */
package probabilistic;

/**
 *
 * @author Alximik
 */
public class UniformDistribution {

    /**
     *Calculate value of Uniform percent point function function
     * on the given probability
     *
     * @param   val probability
     * @param   a   begin of interval (location parametr)
     * @param   b   end of interval B-A: scale parametr)
     *
     * @return  value of random variable
     */
    public static double PPF(double p, double a, double b) {
        double result = 0;
        result = a + (b - a) * p;
        return result;

    }

    /**
     *Calculate probability of uniform cummulative
     * function on the given value  of random variable
     *
     * @param   val value of random variable
     * @param   a   begin of interval (location parametr)
     * @param   b   end of interval B-A:scale parametr
     *
     * @return  probabilty
     */
    public static double CDF(double val, double a, double b) {
        double result = 0;
        result = (val - a) / (b - a);
        return result;
    }

    /**
     *Calculate value of Uniform distribution function
     * on the given value of random variable
     *
     * @param   val probability
     * @param   a   begin of interval (location parametr)
     * @param   b   end of interval B-A:scale parametr
     *
     * @return  value of function
     */
    public static double PDF(double val, double a, double b) {
        double result = 0;
        result = 1.0 / (b - a);
        return result;

    }
}
