/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

/**
 *
 * @author Vitaly
 */
public class CrackPair {

    private SemiellipticalCrack crackObj1, crackObj2;
    Double distanceBetweenTips, ratioDistanceToRC;
    Point centerOfRC;
    double CriticalRadius;
    boolean entersTheRadius;
//    SemiellipticalCrack

    public CrackPair(SemiellipticalCrack crackObj1, SemiellipticalCrack crackObj2) {
        this.crackObj1 = crackObj1;
        this.crackObj2 = crackObj2;
        distanceBetweenTips = Point.Distance(crackObj2.getLeftTip(), crackObj1.getRightTip());
        CriticalRadius = crackObj1.CriticalRadius(crackObj2);
        ratioDistanceToRC = distanceBetweenTips / CriticalRadius;
        centerOfRC = new Point(crackObj1.getRightTip().getX()+CriticalRadius/2, crackObj1.getCrackPoint().getY());  
        entersTheRadius = new Circle(centerOfRC, CriticalRadius).IsPointInsideCircle(crackObj2.getLeftTip());
    }

    public Double getDistanceBetweenTips() {
        return distanceBetweenTips;
    }

    public Double getRatioDistanceToRC() {
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

}
