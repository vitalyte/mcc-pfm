/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;
import java.util.ArrayList;
import java.util.Collections;

import sorting.SortPairRC;
import sorting.SortPairRatio;

/**
 *
 * @author Vitaly
 */
public class SortedPair {

    private ArrayList<SemiellipticalCrack> sourceList;
    private ArrayList<CrackPair> listOfPair;
    private ArrayList<CrackPair> sortedListOfPair = new ArrayList<CrackPair>();
    private double minRC;
    private CrackPair coalescencePair;

    public SortedPair(ArrayList<SemiellipticalCrack> sourceList, double minRC) {
        this.sourceList = sourceList;
        this.minRC = minRC;
    }




    void createPairs(ArrayList<SemiellipticalCrack> sourceListParametr) {
        listOfPair = new ArrayList<CrackPair>();
        for (int j = 0; j < sourceListParametr.size(); j++) {
//            SemiellipticalCrack semiellipticalCrack = sourceListParametr.get(j);
            for (int i = j; i < sourceListParametr.size(); i++) {
                if ((j + i) <= sourceListParametr.size()) {
                    listOfPair.add(new CrackPair(sourceListParametr.get(j), sourceListParametr.get(j + i)));
                } else {
                    break;
                }
            }
        }
    }
    private void sortPairsRC(double rC) {
        Collections.sort(listOfPair,new SortPairRC());
        for (int i = 0; (rC >= listOfPair.get(i).getCriticalRadiusRC()); i++) {
            sortedListOfPair.add(listOfPair.get(i));
        }
        if (!sortedListOfPair.get(0).equals(null)){
        Collections.sort(sortedListOfPair, new SortPairRatio());
        coalescencePair = sortedListOfPair.get(0);
//        SemiellipticalCrack crack1 = sortedListOfPair.get(0).getCrackObj1();
//        SemiellipticalCrack crack2 = sortedListOfPair.get(0).getCrackObj2();
        }
    }


    public ArrayList<CrackPair> getSortedListOfPair() {

        return sortedListOfPair;
    }





    
}
