/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.IntegratorException;

/**
 *
 * @author vitaly
 */
public class Simulation {

    String path;
    String filePath;
    private boolean[][] matrix;
    private double[][] matPointsX;
    private double[][] matPointsY;
    private int seed;
    boolean filledCkracks = false;
    private static double maxCrackLength, visualScale,
            parametrK, yieldStress, sigma;
    private static SurfaceArea surface;
    private InitiationTime timeObj;
    private int maxTimeIndx;
    private int timeIndx = 0;
    private SemiellipticalCrack newCrack;
    private ArrayList<SemiellipticalCrack> ellipticalCrack;
    private static ArrayList<ArrayList> cracksHistoryList;
    private ArrayList<SemiellipticalCrack> paintedCracks;
    private static boolean maxLengthCondition;
    private static String histFolder = "Serializable";

    public Simulation(double height, double width, double grainHeight, double grainWidth,
            double meanInitiationTime, double scaleInitiationTime, double sigma, double yieldStress, double parametrK, double maxCrackLength, double visualKValue) {
        surface = new SurfaceArea(height, width, grainHeight, grainWidth);
        initMatrix(surface);
        timeObj = new InitiationTime(surface.getNmax() + 1, meanInitiationTime, scaleInitiationTime);
        Simulation.sigma = sigma;
        Simulation.yieldStress = yieldStress;
        ellipticalCrack = new ArrayList<SemiellipticalCrack>();
        cracksHistoryList = new ArrayList<ArrayList>();
        Simulation.parametrK = parametrK;

        Simulation.maxCrackLength = maxCrackLength;
        Simulation.visualScale = visualKValue;
        maxLengthCondition = false;
        try {
            path = new java.io.File(".").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
        }
        File folder = new File(".." + File.separator + histFolder);
        folder.mkdir();
        filePath = ".." + File.separator + histFolder + File.separator;
        File[] files = folder.listFiles();
        for (File file : files) {
            // Delete each file
            if (!file.delete()) {
                // Failed to delete file
                System.out.println("Failed to delete " + file);
            }
        }


    }

    private void initMatrix(SurfaceArea surface) {
        Random rnd = new Random();
        int s = -rnd.nextInt(1000000) + 1;
        seed = s;
        matPointsX = new double[surface.getNumColumns()][surface.getNumRows()];
        matPointsY = new double[surface.getNumColumns()][surface.getNumRows()];
        matrix = new boolean[surface.getNumColumns()][surface.getNumRows()];
        for (int i = 0; i < surface.getNumColumns(); i++) {
            for (int j = 0; j < surface.getNumRows(); j++) {
                matrix[i][j] = false;
            }
        }
    }

    public void FillRandomCracks(double length2AMean, double length2AScale,
            double depthMean, double depthScale) {
//        boolean filledCkracks = false;
        int i = 0;
        int step = 0;
        if (isFilledCkracks() == false) {
            exitMaxCondition:
            {
                while (i < surface.getNmax()) {
                    timeIndx = i;
                    //ввести ще один цикл перевірки координати точки!!!
                    double rndX = UniformDistribution.PPF(RNG.Ran2(seed), 0, surface.getWidth());
                    double rndY = UniformDistribution.PPF(RNG.Ran2(seed), 0, surface.getHeight());
                    int rndI = (int) (rndX / surface.getGrainWidth());
                    int rndJ = (int) (rndY / surface.getGrainHeight());
                    double deltaT;
                    if (isSquareEmpty(matrix, rndI, rndJ)) {
//                         точку кинули в порожню клітину
                        generNewCrack(i, rndX, rndY, rndI, rndJ, length2AMean, length2AScale, depthMean, depthScale);
                        //Serialization
                        if (step == 99) {
                            outputToFile(i);
                            step = 0;
                        }

                        double currentTime = timeObj.getInitTime().get(i);
                        if (i == timeObj.getInitTime().size()) {
                            break exitMaxCondition;
                        } else {
                            deltaT = timeObj.getInitTime().get(i + 1) - timeObj.getInitTime().get(i);
                        }
                        boolean growth = false;
//                        int iCoalescenceGrowth = 0;
                        coalescenceGrowthCycle:
                        while (true) {
                            //об’єднання тріщин
//                            coalescence(i);
                            if (growth) {

                                if (maxLengthCondition) {
                                    maxTimeIndx = i;
                                    break exitMaxCondition;
                                }
                                break coalescenceGrowthCycle;
                            }

                            //підростання тріщин
//                            if (!growth) {
//                                try {
//                                    growth(i, currentTime, deltaT);
//                                } catch (DerivativeException ex) {
//                                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
//                                } catch (IntegratorException ex) {
//                                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
                            growth = true;
//                            iCoalescenceGrowth++;
                        }

                        i++;
                        step++;
                    }

                }
                outputToFile(i);
            }
            filledCkracks = true;
            maxTimeIndx = i;
        }
        getPaintedCracks(i);
    }

    /**
     * add coalescence crack and remove two source cracks
     *
     *
     */
    private void generNewCrack(int i, double rndX, double rndY, int rndI, int rndJ, double length2AMean, double length2AScale,
            double depthMean, double depthScale) {
        matPointsX[rndI][rndJ] = rndX;
        matPointsY[rndI][rndJ] = rndY;
        matrix[rndI][rndJ] = true;
        //потягнути з панелі
        double length2A = NormalDistribution.PPF(RNG.Ran2(seed), length2AMean, length2AScale);
        double depth = NormalDistribution.PPF(RNG.Ran2(seed), depthMean, depthScale);
        double lX = rndX - length2A / 2;
        double rX = rndX + length2A / 2;
        if (lX < 0) {
            lX = 0;
        }
        if (rX > surface.getWidth()) {
            rX = surface.getWidth();
        }
        Point lPoint = new Point(lX, rndY);
        Point rPoint = new Point(rX, rndY);
        Point[] lrPoint = {lPoint, rPoint};
        newCrack = new SemiellipticalCrack(lrPoint, depth, i);
        ellipticalCrack.add(newCrack);
    }

    /**
     * add coalescence crack and remove two source cracks
     *
     *
     */
    private void coalescence(int i) {
        while (Pair.createPairs(ellipticalCrack)) {
            newCrack = new SemiellipticalCrack(Pair.getCoalescencePair().getCrackObj1(),
                    Pair.getCoalescencePair().getCrackObj2(), i);
            ellipticalCrack.add(newCrack);
            ellipticalCrack.remove(Pair.getCoalescencePair().getCrackObj1());
            ellipticalCrack.remove(Pair.getCoalescencePair().getCrackObj2());
        }
    }

    /**
     * Growth all cracks of ellipticalCrack List array
     *
     */
    private void growth(int tIndx, double currentTime, double deltaT) throws DerivativeException, IntegratorException {
        for (int j = 0; j < ellipticalCrack.size(); j++) {
            ellipticalCrack.get(j).integrate(tIndx, currentTime, deltaT);
        }
    }

    /**
     * Get the value of filledCkracks
     *
     * @return the value of filledCkracks
     */
    public boolean isFilledCkracks() {
        return filledCkracks;
    }

    boolean isSquareEmpty(boolean[][] matrix, int i, int j) {
        if (matrix[i][j] == false) {
            return true;
        }
        return false;
    }

    public ArrayList<SemiellipticalCrack> getPaintedCracks(int timeIndx) {
//        ArrayList<CrackHistory> dispHistList = new ArrayList<CrackHistory>();
        inputFromFile(timeIndx);
        return paintedCracks;
    }

    /**
     * output to file
     */
    private void outputToFile(int timeIndx) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath + timeIndx + ".ser"), Const.BUFFER_SIZE));
//            out = new ObjectOutputStream(
//                    new FileOutputStream(filePath + timeIndx + ".ser"));
            out.writeObject(ellipticalCrack);
        } catch (IOException ex) {
//            ex.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
//                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * input file
     */
    private void inputFromFile(int timeIndx) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(
                    new FileInputStream(filePath + timeIndx + ".ser")));
            paintedCracks = (ArrayList<SemiellipticalCrack>) in.readObject();
        } catch (IOException ex) {
//            ex.printStackTrace();
        } catch (Exception ex) {
//            ex.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
//                     ex.printStackTrace();
                }
            }
        }


    }

    public ArrayList<SemiellipticalCrack> getPainted() {
        return paintedCracks;
    }

    /**
     * Get the value of newCrack
     *
     * @return the value of newCrack
     */
    public SemiellipticalCrack getNewCrack() {
        return newCrack;
    }

    /**
     * Set the value of newCrack
     *
     * @param newCrack new value of newCrack
     */
    public void setNewCrack(SemiellipticalCrack newCrack) {
        this.newCrack = newCrack;
    }

    /**
     * Get the value of time
     *
     * @return the value of time
     */
    public InitiationTime getTime() {
        return timeObj;
    }

    /**
     * Set the value of time
     *
     * @param time new value of time
     */
    public void setTime(InitiationTime time) {
        this.timeObj = time;
    }

    /**
     * Get the value of surface
     *
     * @return the value of surface
     */
    public static SurfaceArea getSurface() {
        return surface;
    }

//    /**
//     * Set the value of surface
//     *
//     * @param surface new value of surface
//     */
//    public void setSurface(SurfaceArea surface) {
//        this.surface = surface;
//    }
    public double getVisualScale() {
        return visualScale;
    }

    public void setVisualScale(double visualScale) {
        Simulation.visualScale = visualScale;
    }

    public int getMaxTimeIndx() {
        return maxTimeIndx;
    }

    public String getMaxTimeIndxS() {
        return new Integer(maxTimeIndx).toString();
    }

    public static double getParametrK() {
        return parametrK;
    }

    public static double getSigma() {
        return sigma;
    }

    public static double getYieldStress() {
        return yieldStress;
    }

    public static ArrayList<ArrayList> getCracksHistoryList() {
        return cracksHistoryList;
    }

    public static boolean isMaxLengthCondition() {
        return maxLengthCondition;
    }

    public static void setMaxLengthCondition(boolean maxLengthCondition) {
        Simulation.maxLengthCondition = maxLengthCondition;
    }

    public static double getMaxCrackLength() {
        return maxCrackLength;
    }
}
