/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

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
        this.crackObj1 = crackObj1;
        this.crackObj2 = crackObj2;

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



    public static boolean isEntersTheRadiusS(SemiellipticalCrack crackObj1_, SemiellipticalCrack crackObj2_) {
//       
        boolean entersTheRadius_ = false;
        if ( crackObj2_.isInStressRelZoneLTip() != true && crackObj1_.isInStressRelZoneRTip() != true
                ) {
            double distanceBetweenTips_ = Point.Distance(crackObj2_.getLeftTip(), crackObj1_.getRightTip());
            double CriticalRadius_ = crackObj1_.CriticalRadius(crackObj2_);

            Point centerOfRC_ = new Point(crackObj1_.getRightTip().getX() + CriticalRadius_ / 2, crackObj1_.getRightTip().getY());
            entersTheRadius_ = new Circle(centerOfRC_, CriticalRadius_).IsPointInsideCircle(crackObj2_.getLeftTip());
            if (entersTheRadius_) {
                return entersTheRadius_;
            }
        }
        return entersTheRadius_;
    }

    public static double getRatioDistanceToRC_(SemiellipticalCrack crackObj1_, SemiellipticalCrack crackObj2_) {

        double distanceBetweenTips_ = Point.Distance(crackObj2_.getLeftTip(), crackObj1_.getRightTip());
        double CriticalRadius_ = crackObj1_.CriticalRadius(crackObj2_);
        double ratioDistanceToRC_ = distanceBetweenTips_ / CriticalRadius_;
        return ratioDistanceToRC_;
    }
}
