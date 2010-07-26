/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

/**
 *
 * @author Vitaly
 */
public class CrackHistory extends SemiellipticalCrack {

//    double currentTime;
    boolean criticalCreack = false;
    boolean beforeGenereateNewCrack = false;
    private int timeIndex;

//    public CrackHistory(SemiellipticalCrack obj1, int timeI, boolean beforeGenereateNewCrack) {
//        super(obj1);
//        this.beforeGenereateNewCrack = beforeGenereateNewCrack;
////        currentTime = obj1.getSurfaceAreaObj().getTimeObj().getInitTime().get(timeI);
//        timeIndex = timeI;
//        if ((this.getLength2a() >=Simulation.getMaxCrackLength()) & beforeGenereateNewCrack) {
//            criticalCreack = true;
//        }
//    }

    public CrackHistory(SemiellipticalCrack obj1, int timeI) {
        super(obj1);
//        currentTime = obj1.getSurfaceAreaObj().getTimeObj().getInitTime().get(timeI);
        timeIndex = timeI;
//        if ((obj1.getLength2a() >= obj1.getSurfaceAreaObj().getMaxCrackLength()) ) {
//            criticalCreack = true;
//        }
    }

//    public double getCurrentTime() {
//        return currentTime;
//    }

    public boolean isCriticalCreack() {
        return criticalCreack;
    }

    /**
     * @return the timeIndex
     */
    public int getTimeIndex() {
        return timeIndex;
    }
}
