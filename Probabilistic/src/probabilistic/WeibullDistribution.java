/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package probabilistic;

/**
 *
 * @author Alximik
 */
public class WeibullDistribution {
    static double CDF(double val, double mean, double scal, double gamma)
    {
        double result =0;
        result = 1 - Math.exp(-Math.pow((val-mean)/scal,gamma));
        //cdf_Weibull = 1 - Exp(-(((val - mean) / scal) ^ gamma))
        return result;
    }
    static double PPF (double p, double mean, double scal, double gamma){
        double result =0;
        result = mean + scal * Math.pow((-Math.log(1 - p)) , (1 / gamma)) ;
        // mean + scal * (-Log(1 - p)) ^ (1# / gamma))
        return result;
    }
    static double PDF (double val, double mean, double scal, double gamma){
        double result =0;
        double subresult =0;
        if (val == mean){
           if (gamma == 1){
               result = 1;
           }
           else{
               result = 0;
           }
        }
        else{
            subresult = (val-mean)/scal;
            //result = (gamma / scal)*Math.pow(subresult, gamma-1)*Math.exp(-Math.);
        }
        // mean + scal * (-Log(1 - p)) ^ (1# / gamma))
        return result;
    }
    /*
     Function pdf_Weibull(ByVal val As Variant, ByVal mean As Double, ByVal scal As Double, ByVal gamma As Double) As Double
On Error GoTo Err_pdf_Weibull
Dim SubResult As Double
    If val = mean Then
        If gamma = 1 Then
            pdf_Weibull = 1
        Else
            pdf_Weibull = 0
        End If
    Else
        SubResult = (val - mean) / scal
        pdf_Weibull = (gamma / scal) * (SubResult ^ (gamma - 1)) * Exp(-(SubResult ^ gamma))
    End If
Exit Function
Err_pdf_Weibull:
    MsgBox ("Erropr in pdf_Weibull")
End Function

     */
}
