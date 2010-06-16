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
    private ArrayList<CrackPair> sortedListOfPair = new ArrayList<CrackPair>();
    private CrackPair coalescencePair;
    private SemiellipticalCrack resultCrack;
    private boolean canCoalescence;

    public SortedPair(ArrayList<SemiellipticalCrack> sourceList) {
        this.sourceList = sourceList;
        createPairs(sourceList);
        canCoalescence = sortPairsRatio();
//        if (canCoalescence){
//            coalescencePair.getCrackObj1().getRightTip()
//        }

    }

    void createPairs(ArrayList<SemiellipticalCrack> sourceListParametr) {
        listOfPair = new ArrayList<CrackPair>();
        for (int j = 0; j < sourceListParametr.size(); j++) {
//            SemiellipticalCrack semiellipticalCrack = sourceListParametr.get(j);
            for (int i = j + 1; i < sourceListParametr.size(); i++) {
                if ((j + i) <= sourceListParametr.size()) {
                    CrackPair pair = new CrackPair(sourceListParametr.get(j), sourceListParametr.get(j + i));
                    if (pair.isEntersTheRadius()) {
                        listOfPair.add(pair);

                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private boolean sortPairsRatio() {
        boolean result = false;
        if (!listOfPair.get(0).equals(null)) {
            Collections.sort(listOfPair, new SortPairRatio());
            coalescencePair = sortedListOfPair.get(0);
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
