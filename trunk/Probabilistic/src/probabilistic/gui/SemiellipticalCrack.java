/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

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
    public int getCrackLengthB() {
        return lengthB;
    }

    /**
     * Set the value of crackLengthB
     *
     * @param crackLengthB new value of crackLengthB
     */
    public void setCrackLengthB(int crackLengthB) {
        this.lengthB = crackLengthB;
    }


    /**
     * Get the value of crackLength
     *
     * @return the value of crackLength
     */
    public int getCrackLength2a() {
        return length2a;
    }

    /**
     * Set the value of crackLength
     *
     * @param crackLength new value of crackLength
     */
    public void setCrackLength2a(int crackLength) {
        this.length2a = crackLength;
    }


    /**
     * Get the value of crackSiteY
     *
     * @return the value of crackSiteY
     */
    public int getCrackSiteY() {
        return siteY;
    }

    /**
     * Set the value of crackSiteY
     *
     * @param crackSiteY new value of crackSiteY
     */
    public void setCrackSiteY(int crackSiteY) {
        this.siteY = crackSiteY;
    }


    /**
     * Get the value of crackSiteX
     *
     * @return the value of crackSiteX
     */
    public int getCrackSiteX() {
        return siteX;
    }

    /**
     * Set the value of crackSiteX
     *
     * @param crackSiteX new value of crackSiteX
     */
    public void setCrackSiteX(int crackSiteX) {
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
}
