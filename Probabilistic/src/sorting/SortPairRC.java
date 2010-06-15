/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sorting;

import java.util.Comparator;
import probabilistic.gui.CrackPair;

/**
 *
 * @author Vitaly
 */
public class SortPairRC implements Comparator<CrackPair> {

        public int compare(CrackPair o1, CrackPair o2) {
            if (o1 == null || o2 == null) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            return o2.getCriticalRadiusRC().compareTo(o1.getCriticalRadiusRC());
        }
    }

