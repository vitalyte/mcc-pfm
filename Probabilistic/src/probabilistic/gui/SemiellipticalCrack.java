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

    private double initiationTimeObj;
//    private double siteXPoint;
//    private double siteYPoint;
    private double length2a = 10;
    private double depthB;
    private double aspectRatio;
    private static SurfaceArea surfaceAreaObj;
    private Point crackPoint;
    private Point rightTip;
    private Point leftTip;
    int timeIndex;
    private double sigma, sigmaYS, k;

//
//    public SemiellipticalCrack(SurfaceArea surface, double pointX, double pointY) {
//        surfaceAreaObj = surface;
//        siteXPoint = pointX;
//        siteYPoint = pointY;
//        crackPoint = new Point(pointX, pointY);
//    }
    public SemiellipticalCrack(SurfaceArea surface, double pointX, double pointY,
            double length2A, double depthB, int timeIndex) {
        surfaceAreaObj = surface;
        crackPoint = new Point(pointX, pointY);
        this.length2a = length2A;
        this.depthB = depthB;
        this.timeIndex = timeIndex;
        rightTip = new Point((pointX + length2A / 2), pointY);
        leftTip = new Point((pointX - length2A / 2), pointY);
        sigma = surfaceAreaObj.getSigma();
        sigmaYS = surfaceAreaObj.getYieldStress();
        k = surfaceAreaObj.getParametrK();
    }

    public SemiellipticalCrack(SemiellipticalCrack obj1, SemiellipticalCrack obj2, int timeIndex) {
        surfaceAreaObj = obj1.getSurfaceAreaObj();
        length2a = Math.abs(obj1.getLeftTip().getX() - obj2.getRightTip().getX());
        crackPoint = new Point(obj1.getLeftTip().getX() + length2a / 2, obj1.getLeftTip().getY());
        this.depthB = Math.max(obj1.getDepthB(), obj2.getDepthB());
        this.timeIndex = timeIndex;
        sigma = surfaceAreaObj.getSigma();
        sigmaYS = surfaceAreaObj.getYieldStress();
        k = surfaceAreaObj.getParametrK();
    }

    private double SIF_A() {
        double result = 0;
        double KIA = 0;
        double lambda = 2 * depthB / length2a;
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

    private double SIF_B() {
        double result = 0;
        double KIB = 0;
        double lambda = 2 * depthB / length2a;
        final double b0 = 1.15713;
        final double b1 = -0.7302;
        final double b2 = 0.20827;
        KIB = b0 + b1 * lambda + b2 * lambda * lambda;
        result = sigma * Math.sqrt(Math.PI * depthB) * KIB;
        return result;
    }

    public double CriticalRadius(SemiellipticalCrack crack2) {
        double RC = 0;
        RC = (k / Math.PI) * Math.pow(this.SIF_A() / sigmaYS, 2)
                + (k / Math.PI) * Math.pow(crack2.SIF_A() / sigmaYS, 2);
        return RC;
    }

//    public static SemiellipticalCrack CrackCoalescence(SemiellipticalCrack cr1, SemiellipticalCrack cr2) {
//        if (CanCoalescence(cr1, cr2)) {
//            //визначимо початок тріщини зліва
//            if ((cr1.getCrackPoint().getX() - cr1.getLength2a()/2) -
//                    (cr2.getCrackPoint().getX() - cr2.getLength2a()/2))
//            double newLength2A = cr1.getLength2a() + cr2.getLength2a();
//            double newDepthB = Math.max(cr1.getDepthB(), cr2.getDepthB());
//            double newAspectRatio = 2 * newDepthB / newLength2A;
//
//            SemiellipticalCrack newCrack = new SemiellipticalCrack(surfaceAreaObj, 10, 10,
//                    newLength2A, newDepthB, cr1.getTimeIndex());
//            newCrack.setAspectRatio(newAspectRatio);
//            //Подумати, де будуть siteX і SiteY
//            return newCrack;
//        }
//        return null;
//
//    }
//    public static boolean CanCoalescence(SemiellipticalCrack cr1, SemiellipticalCrack cr2) {
//        boolean result = false;
//        // Додати перевірку об"єднання двох тріщин
//        double
//        if (result) {
//
//        }
//        return result;
//    }
    public double da_dtKIrate(double K) {
        double result = 0;
        result = Math.pow(1.1, -7.0)
                * Math.pow((2.5 * Math.pow(10.0, 10.0)
                * Math.exp(-(3 * Math.pow(10.0, -19.0) - 1.5 * Math.pow(10.0, -20.0)
                * Math.pow((K - 2), (1 / 3))))), 0.443);
        return result;
    }

//    public SemiellipticalCrack() {
//        this(surfaceAreaObj);
//    }
//    public SemiellipticalCrack(Point pointObj) {
//        siteXPoint = pointObj.getX();
//    }
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
    public double getDepthB() {
        return depthB;
    }

    /**
     * Set the value of crackLengthB
     *
     * @param crackLengthB new value of crackLengthB
     */
    public void setDepthB(double crackDepthB) {
        this.depthB = crackDepthB;
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
     * Get the value of crackSiteX
     *
     * @return the value of crackSiteX
     */
    /**
     * Get the value of initiationTime
     *
     * @return the value of initiationTime
     */
    public double getInitiationTime() {
        return initiationTimeObj;
    }

    /**
     * Set the value of initiationTime
     *
     * @param initiationTime new value of initiationTime
     */
    public void setInitiationTime(double initiationTimeObj) {
        this.initiationTimeObj = initiationTimeObj;
    }

    public Point getCrackPoint() {
        return crackPoint;
    }

    public void setCrackPoint(Point crackPoint) {
        this.crackPoint = crackPoint;
    }

    public int getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(int timeIndex) {
        this.timeIndex = timeIndex;
    }

    public Point getLeftTip() {
        return leftTip;
    }

    public Point getRightTip() {
        return rightTip;
    }
}
