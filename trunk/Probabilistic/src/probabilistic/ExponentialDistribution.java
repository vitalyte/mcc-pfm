/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 * 
 */
//import ;
package probabilistic;

/**
 *
 * @author Alximik
 */
public class ExponentialDistribution {

    /**
     * Calculate value of Exponential percent point function function
     * on the given probability
     *
     * @param val probability
     * @param mean average (location parametr)
     * @param scal deviation (scale parametr)
     *
     * @return value of random variable
     */
    static double PPF(double p, double mean, double scal) {
        double result = 0;
        result = mean - scal * Math.log(1 - p);
        return result;


    }

    /**
     * Calculate probability of Exponential cummulative
     * function on the given value  of random variable
     *
     * @param val value of random variable
     * @param mean average (location parametr)
     * @param scal deviation (scale parametr)
     *
     * @return probabilty
     */
    static double CDF(double val, double mean, double scal) {
        double result = 0;
        result = 1 - Math.exp(-(val - mean) / scal);
        return result;
    }

    /**
     * Calculate value of Exponential distribution function
     * on the given value of random variable
     *
     * @param val probability
     * @param mean average (location parametr)
     * @param scal deviation (scale parametr)
     *
     * @return the value of Exponential distribution function
     */
    static double PDF(double val, double mean, double scal) {
        double result = 0;
        result = (1 / scal) * Math.exp(-1 * (val - mean) / scal);
        return result;

    }
}
