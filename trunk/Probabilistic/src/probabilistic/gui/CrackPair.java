/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

import java.util.Comparator;

/**
 *
 * @author Vitaly
 */
public class CrackPair {

    private SemiellipticalCrack crackObj1, crackObj2;
    Double distanceBetweenTips, criticalRadiusRC, ratioDistanceToRC;
    Point centerOfRC;
    double CriticalRadius;

    public CrackPair(SemiellipticalCrack crackObj1, SemiellipticalCrack crackObj2) {
        this.crackObj1 = crackObj1;
        this.crackObj2 = crackObj2;
        distanceBetweenTips = calculateDistance();
        criticalRadiusRC = calculateRC();
        ratioDistanceToRC = ratioDistanceToRC();
        centerOfRC = centerOfRC();
        CriticalRadius = crackObj1.CriticalRadius(crackObj2);
    }

    double calculateDistance() {
        double distanceX = crackObj2.getCrackPoint().getX() - crackObj2.getLength2a() / 2 - crackObj1.getRightTip();
        double distanceY = crackObj1.getCrackPoint().getY() - crackObj2.getCrackPoint().getY();
        // відстань між вершинами
        double distance = Math.sqrt(Math.abs(distanceY + Math.abs(distanceX)));
        return distance;
    }

    double calculateRC() {
        return 2 * Math.abs(crackObj2.getCrackPoint().getX() - crackObj2.getLength2a() / 2 - crackObj1.getRightTip());
    }

    Point centerOfRC() {
        return new Point(((crackObj2.getCrackPoint().getX() - crackObj2.getLength2a() / 2 - crackObj1.getRightTip()) / 2),
                crackObj1.getCrackPoint().getY());
    }

    double ratioDistanceToRC() {
        return calculateDistance() / calculateRC();
    }

    public Double getCriticalRadiusRC() {
        return criticalRadiusRC;
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

}
