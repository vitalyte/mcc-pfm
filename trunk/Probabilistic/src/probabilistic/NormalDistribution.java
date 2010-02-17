/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * ^)
 */
//import ;
package probabilistic;
import  java.lang.*;
/**
 *
 * @author Alximik
 */
public class NormalDistribution {
static void PPF(){

}

static double CDF(double val, double mean, double scale){
    double L,k,x;
    final double   a1 = 0.31938153;
    final double a2 = -0.356563782;
    final double a3 = 1.781477937;
    final double a4= -1.821255978;
    final double a5 =  1.330274429;
    x= (val - mean) / scale;
    L= Math.abs(x);
    return 0.0;
    /*
    L= Math.fabs(x);

x = (val - mean) / scal
L = Abs(x)
k = 1 / (1 + 0.2316419 * L)
cdf_Normal = 1 - 1 / Sqr(2 * pi) * Exp(-L ^ 2 / 2) * (a1 * k + a2 * k ^ 2 + a3 * k ^ 3 + a4 * k ^ 4 + a5 * k ^ 5)
If x < 0 Then
    cdf_Normal = 1 - cdf_Normal
End If
End Function*/
}
static void PDF(){
    
}
}
