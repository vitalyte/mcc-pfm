/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * ^)
 */
//import ;
package probabilistic;
import java.lang.*;
/**
 *
 * @author Alximik
 */
public class NormalDistribution {
static void PPF(){

}

static double CDF(double val, double mean, double scale){
    double L,k,x;
    final double pi = 4*Math.atan(1);
    final double a1 = 0.31938153;
    final double a2 = -0.356563782;
    final double a3 = 1.781477937;
    final double a4= -1.821255978;
    final double a5 =  1.330274429;
    x= (val - mean) / scale;
    L= Math.abs(x);
    k = 1 / (1 + 0.2316419 * L);
    double result=0;
    result = 1-1/Math.sqrt(2*pi)*Math.exp(-L*L/2)* (a1 * k + a2 * Math.pow(k,2) + a3 * Math.pow(k,3) + a4 * Math.pow(k,4) + a5 * Math.pow(k,5));
    if (x<0)
        result = 1- result;
    return result;
}
static void PDF(){
    
}
}
