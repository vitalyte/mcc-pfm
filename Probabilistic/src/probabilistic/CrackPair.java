/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

/**
 *
 * @author Vitaly
 */
public class CrackPair {

    private SemiellipticalCrack crackObj1, crackObj2;
    double ratioDistanceToRC;
    double distanceBetweenTips;
    Point centerOfRC;
    double CriticalRadius;
    boolean entersTheRadius;

    public CrackPair() {
    }


    public CrackPair(SemiellipticalCrack crackObj1, SemiellipticalCrack crackObj2) {

        if (crackObj2.getLeftTip().getX() < crackObj1.getRightTip().getX()) {
            this.crackObj1 = crackObj2;
            this.crackObj2 = crackObj1;
        } else {
            this.crackObj1 = crackObj1;
            this.crackObj2 = crackObj2;
        }
        distanceBetweenTips = Point.Distance(this.crackObj2.getLeftTip(), this.crackObj1.getRightTip());
        CriticalRadius = this.crackObj1.CriticalRadius(crackObj2);
//        System.out.println("CriticalRadius = "+CriticalRadius);
        ratioDistanceToRC = distanceBetweenTips / CriticalRadius;
        centerOfRC = new Point(this.crackObj1.getRightTip().getX() + this.CriticalRadius / 2, this.crackObj1.getRightTip().getY());
        entersTheRadius = new Circle(centerOfRC, CriticalRadius).IsPointInsideCircle(this.crackObj2.getLeftTip());
//        if(entersTheRadius){
//            System.out.println("\n\nCriticalRadius = " + CriticalRadius +" "+ entersTheRadius);
//        }

    }

    public double getDistanceBetweenTips() {
        return distanceBetweenTips;
    }

    public double getRatioDistanceToRC() {
        return ratioDistanceToRC;
    }

    public SemiellipticalCrack getCrackObj1() {
        return crackObj1;
    }

    public SemiellipticalCrack getCrackObj2() {
        return crackObj2;
    }

    public double getCriticalRadius() {
        return CriticalRadius;
    }

    public boolean isEntersTheRadius() {
        return entersTheRadius;
    }

    public static SemiellipticalCrack[] leftRightCracks(SemiellipticalCrack crackObj1_, SemiellipticalCrack crackObj2_) {
        SemiellipticalCrack crackArr[] = {crackObj1_, crackObj2_};
        if (crackObj2_.getLeftTip().getX() < crackObj1_.getRightTip().getX()) {
            crackArr[1] = crackObj1_;
            crackArr[0] = crackObj2_;
        }
        return crackArr;
    }

    public static boolean isEntersTheRadiusS(SemiellipticalCrack crackObj1_, SemiellipticalCrack crackObj2_) {
        if (crackObj2_.getLeftTip().getX() < crackObj1_.getRightTip().getX()) {
            SemiellipticalCrack crackObjTemp = crackObj2_;
            crackObj2_ = crackObj1_;
            crackObj1_ = crackObjTemp;
        }
        boolean entersTheRadius_ = false;
        if (crackObj2_.getLeftTip().getX() >= crackObj1_.getRightTip().getX()) {

            double distanceBetweenTips_ = Point.Distance(crackObj2_.getLeftTip(), crackObj1_.getRightTip());
            double CriticalRadius_ = crackObj1_.CriticalRadius(crackObj2_);

            Point centerOfRC_ = new Point(crackObj1_.getRightTip().getX() + CriticalRadius_ / 2, crackObj1_.getRightTip().getY());
            entersTheRadius_ = new Circle(centerOfRC_, CriticalRadius_).IsPointInsideCircle(crackObj2_.getLeftTip());
        } else {
            entersTheRadius_ = false;
        }

        return entersTheRadius_;
    }

    public static double getRatioDistanceToRC_(SemiellipticalCrack crackObj1_, SemiellipticalCrack crackObj2_) {
        if (crackObj2_.getLeftTip().getX() < crackObj1_.getRightTip().getX()) {
            SemiellipticalCrack crackObjTemp = crackObj2_;
            crackObj2_ = crackObj1_;
            crackObj1_ = crackObjTemp;
        }
        double distanceBetweenTips_ = Point.Distance(crackObj2_.getLeftTip(), crackObj1_.getRightTip());
        double CriticalRadius_ = crackObj1_.CriticalRadius(crackObj2_);
        double ratioDistanceToRC_ = distanceBetweenTips_ / CriticalRadius_;
        return ratioDistanceToRC_;
    }

}
