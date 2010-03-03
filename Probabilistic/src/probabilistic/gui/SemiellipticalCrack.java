/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

import java.util.Random;
import probabilistic.*;

/**
 *
 * @author Vitaly
 */
public class SemiellipticalCrack {

    private int initiationTime;
    private int siteX;
    private int siteY;
    private int length2a;
    private int lengthB;
    private double aspectRatio;
    private static SurfaceArea surfaceAreaObj;


    public SemiellipticalCrack() {


        
    }








    /**
     * Get the value of surfaceAreaObj
     *
     * @return the value of surfaceAreaObj
     */
    public SurfaceArea getSurfaceAreaObj() {
        return surfaceAreaObj;
    }






    /**
     * Get the value of aspectRatio
     *
     * @return the value of aspectRatio
     */
    public double getAspectRatio() {
        return aspectRatio;
    }

    /**
     * Set the value of aspectRatio
     *
     * @param aspectRatio new value of aspectRatio
     */
    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Get the value of crackLengthB
     *
     * @return the value of crackLengthB
     */
    public int getLengthB() {
        return lengthB;
    }

    /**
     * Set the value of crackLengthB
     *
     * @param crackLengthB new value of crackLengthB
     */
    public void setLengthB(int crackLengthB) {
        this.lengthB = crackLengthB;
    }

    /**
     * Get the value of crackLength
     *
     * @return the value of crackLength
     */
    public int getLength2a() {
        return length2a;
    }

    /**
     * Set the value of crackLength
     *
     * @param crackLength new value of crackLength
     */
    public void setLength2a(int crackLength) {
        this.length2a = crackLength;
    }

    /**
     * Get the value of crackSiteY
     *
     * @return the value of crackSiteY
     */
    public int getSiteY() {
        return siteY;
    }

    /**
     * Set the value of crackSiteY
     *
     * @param crackSiteY new value of crackSiteY
     */
    public void setSiteY(int crackSiteY) {
        this.siteY = crackSiteY;
    }

    /**
     * Get the value of crackSiteX
     *
     * @return the value of crackSiteX
     */
    public int getSiteX() {
        return siteX;
    }

    /**
     * Set the value of crackSiteX
     *
     * @param crackSiteX new value of crackSiteX
     */
    public void setSiteX(int crackSiteX) {
        this.siteX = crackSiteX;
    }

    /**
     * Get the value of initiationTime
     *
     * @return the value of initiationTime
     */
    public int getInitiationTime() {
        return initiationTime;
    }

    /**
     * Set the value of initiationTime
     *
     * @param initiationTime new value of initiationTime
     */
    public void setInitiationTime(int initiationTime) {
        this.initiationTime = initiationTime;
    }
    public double SIF_A(double sigma)
    {
        double result = 0;
        double KIA = 0;
        double lambda = 2*lengthB/length2a;
        final double b0=0.23153;
        final double b1=0.61945;
        final double b2=-0.19862;
        final double b3=0.02754;
        final double b4=0.00137;
        KIA = b0 + b1*lambda+b2*lambda*lambda+
        b3*Math.pow(lambda, 3)+b4*Math.pow(lambda, 4);
        result = sigma*Math.sqrt(Math.PI*length2a/2)*KIA;
        return result;
    }

    public double SIF_B(double sigma)
    {
        double result = 0;
        double KIB = 0;
        double lambda = 2*lengthB/length2a;
        final double b0=1.15713;
        final double b1=-0.7302;
        final double b2=0.20827;
        KIB = b0 + b1*lambda+b2*lambda*lambda;
        result = sigma*Math.sqrt(Math.PI*length2a/2)*KIB;
        return result;
    }
}
