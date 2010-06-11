/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Vitaly
 */
public class SortedPair {

    private ArrayList<SemiellipticalCrack> sourceList;
    private ArrayList<crackPair> listOfPair;
    private ArrayList<crackPair> sortedListOfPair;

    public void createPairs(ArrayList<SemiellipticalCrack> sourceListParametr) {
        listOfPair = new ArrayList<crackPair>();
        for (int j = 0; j < sourceListParametr.size(); j++) {
//            SemiellipticalCrack semiellipticalCrack = sourceListParametr.get(j);
            for (int i = j; i < sourceListParametr.size(); i++) {
                if ((j + i) <= sourceListParametr.size()) {
                    sortedListOfPair.add(new crackPair(sourceListParametr.get(j), sourceListParametr.get(j + i)));
                }else break;
            }
        }
    }

    private class crackPair {

        private SemiellipticalCrack crackObj1, crackObj2;
        Double distance;

        public crackPair(SemiellipticalCrack crackObj1, SemiellipticalCrack crackObj2) {
            this.crackObj1 = crackObj1;
            this.crackObj2 = crackObj2;
        }
        Double calculateDistance (SemiellipticalCrack crackObj1, SemiellipticalCrack crackObj2){
            double distanceX = crackObj1.getCrackPoint().getX() - crackObj1.getLength2a()/2 - crackObj1.getRightTip();
            double distanceY = crackObj1.getCrackPoint().getY() - crackObj2.getCrackPoint().getY();
            //порахувати відстань між вершинами
        return new Double(distanceY);
        }

    }
}
