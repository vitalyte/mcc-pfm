/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
//import ;
package probabilistic;
import java.lang.*;
/**
 *
 * @author Alximik
 */
public class UniformDistribution {


    /*'Purpose:   Calculate value of Uniform percent point function function
*          on the given probability
*/
//'Inputs:
//'   val    probability
//'   a      begin of interval (location parametr)
//'   b      end of interval B-A: scale parametr)
//'Returns:   value of random variable
static double PPF(double p, double a, double b){
    double result=0;
    result = a + (b - a) * p;
    return result;


}

/*Purpose:   Calculate probability of uniform cummulative
            function on the given value  of random variable
*/
//'Inputs:
//'   val    value of random variable
//'   a   begin of interval (location parametr)
//'   b   end of interval B-A:scale parametr
//'Returns:   probabilty
static double CDF(double val, double a, double b){
    double result=0;
    result = (val - a)/(b - a);       
    return result;
}

/*'Purpose:   Calculate value of Uniform distribution function
//'           on the given value of random variable
 */
//'Inputs:
//'   val    probability
//'   a   begin of interval (location parametr)
//'   b   end of interval B-A:scale parametr
//'Returns:   value of function
static double PDF(double val, double a, double b){
    double result=0;
    result=1.0 / (b - a);
    return result;

}
}
