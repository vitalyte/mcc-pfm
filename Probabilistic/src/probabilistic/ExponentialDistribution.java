/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 * 
 */
//import ;
package probabilistic;
import java.lang.*;
/**
 *
 * @author Alximik
 */
public class ExponentialDistribution {


//'Purpose:   Calculate value of Exponential percent point function function
//'           on the given probability
//'Inputs:
//'   val:    probability
//'   mean:   average (location parametr)
//'   scal:   deviation (scale parametr)
//'Returns:   value of random variable
static double PPF(double p, double mean, double scal){
    double result=0;
    result = mean - scal * Math.log(1 - p);
    return result;


}

//'*****************************************************
//'Purpose:   Calculate probability of Exponential cummulative
//'            function on the given value  of random variable
//'
//'Inputs:
//'   val:    value of random variable
//'   mean:   average (location parametr)
//'   scal:   deviation (scale parametr)
//'Returns:   probabilty
//'*****************************************************
static double CDF(double val, double mean, double scal){
    double result=0;
    result = 1 - Math.exp(-(val - mean) / scal);
    return result;
}

//'*****************************************************
//'Purpose:   Calculate value of Exponential distribution function
//'           on the given value of random variable
//'Inputs:
//'   val:    probability
//'   mean:   average (location parametr)
//'   scal:   deviation (scale parametr)
//'Returns:   value of function
static double PDF(double val, double mean, double scal){
    double result=0;
    result=(1 / scal) * Math.exp(-1 * (val - mean) / scal);
    return result;

}
}
