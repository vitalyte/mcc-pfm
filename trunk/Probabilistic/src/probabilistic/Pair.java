/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

//import sorting.SortPairRC;
/**
 *
 * @author Vitaly
 */
public class Pair {

    private static CrackPair coalescencePair_;
    private SemiellipticalCrack resultCrack;
    private static boolean canCoalescence = false;
    static CrackPair pair;

    public Pair() {
    }

    public static boolean createPairs(ArrayList<SemiellipticalCrack> sourceListParametr) {
        boolean result = false;
        coalescencePair_ = null;
        CrackPair coalescencePair = null;
        for (int j = 0; j < sourceListParametr.size() - 1; j++) {
            for (int i = j + 1; i < sourceListParametr.size(); i++) {
                if ((j + i) < sourceListParametr.size()) {
                    if (sourceListParametr.get(j) != null && sourceListParametr.get(i) != null) {
                        //Sorting from left to right cracks
                        SemiellipticalCrack leftInPair = sourceListParametr.get(j);
                        SemiellipticalCrack rightInPair = sourceListParametr.get(j + i);
                        double xOfR = (rightInPair.getRightTip().getX() - rightInPair.getLeftTip().getX()) / 2;
                        double xOfL = (leftInPair.getRightTip().getX() - leftInPair.getLeftTip().getX()) / 2;
                        if (xOfR < xOfL) {
                            SemiellipticalCrack crackObjTemp = rightInPair;
                            rightInPair = leftInPair;
                            leftInPair = crackObjTemp;
                        }
                        if (rightInPair.getLeftTip().getX() < leftInPair.getRightTip().getX()) {
                            continue;
                        }
                        //end of Sorting from left to right cracks

                        if ((leftInPair.isInStressRelZoneScreen() && !leftInPair.isDeeper())
                                || (rightInPair.isInStressRelZoneScreen() && !rightInPair.isDeeper())) {
                            continue;
                        }

                        if (coalescencePair == null) {
                            if (CrackPair.isEntersTheRadiusS(leftInPair, rightInPair)
//                                &&  !isCoalescencedOverCracks(sourceListParametr, leftInPair, rightInPair)
                                    ) {
                                pair = new CrackPair(leftInPair, rightInPair);
                                coalescencePair = pair;
                                result = true;
                            }

                        } else {
                            if (coalescencePair.getRatioDistanceToRC() > CrackPair.getRatioDistanceToRC_(leftInPair, rightInPair)
//                                    &&  !isCoalescencedOverCracks(sourceListParametr, leftInPair, rightInPair)
                                    ) {
                                pair = new CrackPair(leftInPair, rightInPair);
                                coalescencePair = pair;
                                result = true;
                            }
                        }

                        coalescencePair_ = coalescencePair;
//                            System.out.println("\n\nCriticalRadius = " + coalescencePair_.getCriticalRadius());                         
                    }
                } else {
                    continue;
                }
            }
        }
        return result;
    }

    public boolean isCanCoalescence() {
        return canCoalescence;
    }

    public SemiellipticalCrack getResultCrack() {
        return resultCrack;
    }

    public static CrackPair getCoalescencePair() {
        return coalescencePair_;
    }

    public static boolean isCoalescencedOverCracks(ArrayList<SemiellipticalCrack> sourceListParametr,
            SemiellipticalCrack leftInPair, SemiellipticalCrack rightInPair) {
        Point pL = leftInPair.getRightTip();
        Point pR = rightInPair.getLeftTip();
        boolean result = false;
        SemiellipticalCrack newCrack = new SemiellipticalCrack();
        ArrayList points = new ArrayList<Point>(2);
        points.add(pL);
        points.add(pR);
        newCrack.setCrackTip(points);
        newCrack.setDepthB(Math.max(leftInPair.getDepthB(), rightInPair.getDepthB()));
        result = newCrack.setInZoneScreenCoal(sourceListParametr);
        return result;
    }
}
