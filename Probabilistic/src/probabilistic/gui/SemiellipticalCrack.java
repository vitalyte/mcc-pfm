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
    private double siteXPoint;
    private double siteYpoint;
    private double length2a = 10;
    private double lengthB;
    private double aspectRatio;
    private static SurfaceArea surfaceAreaObj;
    private Point crackPoint;

    public SemiellipticalCrack(SurfaceArea surface) {
        surfaceAreaObj = surface;
//        crackPoint = new Point(surfaceAreaObj.getMatPointsX(), surfaceAreaObj.getMatPointsY());
    }

    public SemiellipticalCrack() {
        this(surfaceAreaObj);
    }

    public SemiellipticalCrack(Point pointObj) {
        siteXPoint = pointObj.getX();
    }

    public static void setSurfaceAreaObj(SurfaceArea surfaceAreaObj) {
        SemiellipticalCrack.surfaceAreaObj = surfaceAreaObj;
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
    public double getLengthB() {
        return lengthB;
    }

    /**
     * Set the value of crackLengthB
     *
     * @param crackLengthB new value of crackLengthB
     */
    public void setLengthB(double crackLengthB) {
        this.lengthB = crackLengthB;
    }

    /**
     * Get the value of crackLength
     *
     * @return the value of crackLength
     */
    public double getLength2a() {
        return length2a;
    }

    /**
     * Set the value of crackLength
     *
     * @param crackLength new value of crackLength
     */
    public void setLength2a(double crackLength) {
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

    public double SIF_A(double sigma) {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * lengthB / length2a;
        final double b0 = 0.23153;
        final double b1 = 0.61945;
        final double b2 = -0.19862;
        final double b3 = 0.02754;
        final double b4 = 0.00137;
        KIA = b0 + b1 * lambda + b2 * lambda * lambda
                + b3 * Math.pow(lambda, 3) + b4 * Math.pow(lambda, 4);
        result = sigma * Math.sqrt(Math.PI * length2a / 2) * KIA;
        return result;
    }

    public double SIF_B(double sigma) {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * lengthB / length2a;
        final double b0 = 1.15713;
        final double b1 = -0.7302;
        final double b2 = 0.20827;
        KIB = b0 + b1 * lambda + b2 * lambda * lambda;
        result = sigma * Math.sqrt(Math.PI * lengthB) * KIB;
        return result;
    }

    public double CriticalRadius(SemiellipticalCrack crack2, double sigma, double sigmaYS, double k) {
        double RC = 0;
        RC = (k / Math.PI) * Math.pow(this.SIF_A(sigma) / sigmaYS, 2)
                + (k / Math.PI) * Math.pow(crack2.SIF_A(sigma) / sigmaYS, 2);
        return RC;
    }

    public static SemiellipticalCrack CrackCoalescence(SemiellipticalCrack cr1, SemiellipticalCrack cr2) {
        if (CanCoalescence(cr1, cr2)) {
            SemiellipticalCrack newCrack = new SemiellipticalCrack();
            double newLengthB = Math.max(cr1.getLengthB(), cr2.getLengthB());
            double newLength2A = cr1.getLength2a() + cr2.getLength2a();
            double newAspectRatio = 2 * newLengthB / newLength2A;
            newCrack.setLength2a(newLength2A);
            newCrack.setLengthB(newLengthB);
            newCrack.setAspectRatio(newAspectRatio);
            //Подумати, де будуть siteX і SiteY
            return newCrack;
        }
        return null;

    }

    static boolean CanCoalescence(SemiellipticalCrack cr1, SemiellipticalCrack cr2) {
        boolean result = false;
        // Додати перевірку об"єднання двох тріщин
        return result;
    }

    double da_dtKIrate(double K) {
        double result = 0;
        result = Math.pow(1.1, -7.0)
                * Math.pow((2.5 * Math.pow(10.0, 10.0)
                * Math.exp(-(3 * Math.pow(10.0, -19.0) - 1.5 * Math.pow(10.0, -20.0)
                * Math.pow((K - 2), (1 / 3))))), 0.443);
        return result;
    }
}
