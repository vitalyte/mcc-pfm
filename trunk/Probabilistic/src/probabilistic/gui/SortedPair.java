/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

import java.util.ArrayList;
import java.util.Collections;

//import sorting.SortPairRC;
import sorting.SortPairRatio;

/**
 *
 * @author Vitaly
 */
public class SortedPair {

    private ArrayList<SemiellipticalCrack> sourceList;
    private ArrayList<CrackPair> listOfPair;
//    private ArrayList<CrackPair> sortedListOfPair = new ArrayList<CrackPair>();
    private CrackPair coalescencePair;
    private SemiellipticalCrack resultCrack;
    private boolean canCoalescence = false;
    CrackPair pair;

    public SortedPair(ArrayList<SemiellipticalCrack> sourceList) {
        this.sourceList = sourceList;
        if (createPairs(sourceList)) {
            canCoalescence = sortPairsRatio();
        }
//        if (canCoalescence){
//            coalescencePair.getCrackObj1().getRightTip()
//        }

    }

    public boolean createPairs(ArrayList<SemiellipticalCrack> sourceListParametr) {
        boolean result = false;

        listOfPair = new ArrayList<CrackPair>();
        sourceList = sourceListParametr;
        for (int j = 0; j < sourceListParametr.size(); j++) {
//            if ((j) >= sourceListParametr.size()) {
//            SemiellipticalCrack semiellipticalCrack = sourceListParametr.get(j);            
            for (int i = j; i < sourceListParametr.size(); i++) {
                if ((j + i) < sourceListParametr.size()) {
                    if (sourceListParametr.get(j) != null && sourceListParametr.get(i) != null && !sourceListParametr.get(j).equals(sourceListParametr.get(i))) {
                        pair = new CrackPair(sourceListParametr.get(j), sourceListParametr.get(j + i));
                        if (pair.entersTheRadius) {
                            listOfPair.add(pair);
                            result = true;
                        } else {
                            break;
                        }
                    }

//                        if (pair.isEntersTheRadius()) {

                } else {
                    break;
                }
            }

//            } else {
//                break;
//            }
        }
        return result;
    }

    private boolean sortPairsRatio() {
        boolean result = false;
        if (listOfPair.get(0) != null) {
            Collections.sort(listOfPair, new SortPairRatio());
            coalescencePair = listOfPair.get(0);
//            resultCrack = coalescencePair.getCrackObj1().getCrackPoint().getX();
            result = true;
//        SemiellipticalCrack crack1 = sortedListOfPair.get(0).getCrackObj1();
//        SemiellipticalCrack crack2 = sortedListOfPair.get(0).getCrackObj2();
        }
        return result;
    }

    public boolean isCanCoalescence() {
        return canCoalescence;
    }

    public SemiellipticalCrack getResultCrack() {
        return resultCrack;
    }

    public CrackPair getCoalescencePair() {
        return coalescencePair;
    }
}
