/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

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
                    if (sourceListParametr.get(j + 1) != null && sourceListParametr.get(j) != null && sourceListParametr.get(i) != null) {
                        SemiellipticalCrack firstInPair = sourceListParametr.get(j);
                        SemiellipticalCrack secondInPair = sourceListParametr.get(j + i);
                        if (firstInPair.isInStressRelZoneScreen() || secondInPair.isInStressRelZoneScreen()) {
                            break;
                        }
                        if (CrackPair.isEntersTheRadiusS(firstInPair, secondInPair)) {
                            if (coalescencePair == null) {
                                pair = new CrackPair(firstInPair, secondInPair);
                                coalescencePair = pair;
                                result = true;

                            } else if (coalescencePair.getRatioDistanceToRC() > CrackPair.getRatioDistanceToRC_(firstInPair, secondInPair)) {
                                pair = new CrackPair(firstInPair, secondInPair);
                                coalescencePair = pair;
                                result = true;
                            }
                            coalescencePair_ = coalescencePair;
//                            System.out.println("\n\nCriticalRadius = " + coalescencePair_.getCriticalRadius());

                        } else {
                            break;
                        }
                    }
                } else {
                    break;
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
}
