/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

import java.util.ArrayList;
import java.util.Collections;

//import sorting.SortPairRC;
import probabilistic.sort.SortPairRatio;

/**
 *
 * @author Vitaly
 */
public class SortedPair {

//    private ArrayList<SemiellipticalCrack> sourceList;
    private static ArrayList<CrackPair> listOfPair;
//    private ArrayList<CrackPair> sortedListOfPair = new ArrayList<CrackPair>();
    private static CrackPair coalescencePair;
    private SemiellipticalCrack resultCrack;
    private static boolean canCoalescence = false;
    static CrackPair pair;

    public SortedPair(ArrayList<SemiellipticalCrack> sourceList) {
//        this.sourceList = sourceList;
//        if (createPairs(sourceList)) {
//            canCoalescence = sortPairsRatio();
//        }
//        if (canCoalescence){
//            coalescencePair.getCrackObj1().getRightTip()
//        }
    }

    public static boolean createPairs(ArrayList<SemiellipticalCrack> sourceListParametr) {
        boolean result = false;
        listOfPair = new ArrayList<CrackPair>();
//        sourceList = sourceListParametr;
        for (int j = 0; j < sourceListParametr.size()-1; j++) {
            for (int i = j + 1; i < sourceListParametr.size(); i++) {
                if ((j + i) < sourceListParametr.size()) {
                    if (sourceListParametr.get(j+1) != null && sourceListParametr.get(j) != null && sourceListParametr.get(i) != null && !sourceListParametr.get(j).equals(sourceListParametr.get(i))) {
                      pair = new CrackPair(sourceListParametr.get(j), sourceListParametr.get(j + i));
                        if (pair.entersTheRadius) {
                            if(coalescencePair == null){
                                coalescencePair = pair;
                            }else
                            if (coalescencePair.getRatioDistanceToRC() > pair.getRatioDistanceToRC()){
                                coalescencePair = pair;
                            }
                            listOfPair.add(pair);
                            result = true;
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
//        if (result == true) {
//            result = sortPairsRatio();
//            System.out.println("listOfPair = " + listOfPair.size());
//        } else {
//            result = false;
//        }
        return result;
    }

//    public static boolean sortPairsRatio() {
//        boolean result = false;
//        if (listOfPair.get(0) != null) {
//            Collections.sort(listOfPair, new SortPairRatio());
//            coalescencePair = listOfPair.get(0);
////            resultCrack = coalescencePair.getCrackObj1().getCrackPoint().getX();
//            result = true;
////        SemiellipticalCrack crack1 = sortedListOfPair.get(0).getCrackObj1();
////        SemiellipticalCrack crack2 = sortedListOfPair.get(0).getCrackObj2();
//        }
//        return result;
//    }

    public boolean isCanCoalescence() {
        return canCoalescence;
    }

    public SemiellipticalCrack getResultCrack() {
        return resultCrack;
    }

    public static CrackPair getCoalescencePair() {
        return coalescencePair;
    }
}
