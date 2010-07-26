/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.sort;

import java.util.Comparator;
import probabilistic.SemiellipticalCrack;

/**
 *
 * @author Vitaly
 */
public class CrackSorterRTip implements Comparator<SemiellipticalCrack> {

//    1  якщо(о1)   більше(о2   ) ,
//   -1  якщо параметр (о1) менше (о2),
//    0  якщо рівні.
    public int compare(SemiellipticalCrack o1, SemiellipticalCrack o2) {
//        if (o1 == null || o2 == null) {
//            throw new ClassCastException("error when compare SemiellipticalCrack obj");
//        }
//        if ((o1.getSiteX() + o1.getLength2a() / 2) > (o2.getSiteX() + o2.getLength2a() / 2)) {
//            return 1;
//        }
//        if ((o1.getSiteX() + o1.getLength2a() / 2) < (o2.getSiteX() + o2.getLength2a() / 2)) {
//            return -1;
//        }
//        if ((o1.getSiteX() + o1.getLength2a() / 2) == (o2.getSiteX() + o2.getLength2a() / 2)) {
//            return 0;
//        }
         if (o1 == null || o2 == null) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
//         System.out.println(o2.getRightTip().compareTo(o1.getRightTip()));
            return o2.getRightTip().getXDouble().compareTo(o1.getRightTip().getXDouble());
        
    }
}

