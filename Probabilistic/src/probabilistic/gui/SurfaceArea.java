/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

//import sorting.CrackSorterRTip;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.IntegratorException;
import probabilistic.*;
import sorting.CrackSorterRTip;

/**
 *
 * @author Vitaly
 */
public class SurfaceArea {

    private int Nmax;
    private InitiationTime timeObj;
    private double height;
    private double width;
    private double grainHeight;
    private double grainWidth;
    private double[][] matPointsX;
    private double[][] matPointsY;
    private int numColumns;
    private int numRows;
    double parametrK, yieldStress, sigma;
    private Integer seed;
    private boolean[][] matrix;
    boolean filledCkracks = false;
    private ArrayList<SemiellipticalCrack> ellipticalCrack;
    private ArrayList<SemiellipticalCrack> crackSortedByRightTip;
    private ArrayList<SemiellipticalCrack> cracksRemoved;
    double maxCrackLength;
    double k1SCC = 2;
    int visualKValue;
    //cracks history General array
    private ArrayList<ArrayList> cracksHistoryList;
    private int timeIndex = 0;
    private ArrayList<? extends SemiellipticalCrack> paintedCracks;
    private int maxCrackLengthTimeIndex;

    public SurfaceArea(double height, double width, double grainHeight, double grainWidth,
            double meanInitiationTime, double scaleInitiationTime, double sigma, double yieldStress, double parametrK) {
        this.width = width;
        this.height = height;
        this.grainHeight = grainHeight;
        this.grainWidth = grainWidth;
//        Nmax = (int) ((width * height) / (grainWidth * grainHeight));
        numColumns = (int) (this.width / this.grainWidth);
        numRows = (int) (this.height / this.grainHeight);

        //визначення кратних параметрів
        Nmax = numColumns * numRows;
        this.width = numColumns *this.grainWidth;
        this.height = numRows * this.grainHeight;

        initMatrix(height, width, grainHeight, grainWidth);
        timeObj = new InitiationTime(Nmax, meanInitiationTime, scaleInitiationTime);
        ellipticalCrack = new ArrayList<SemiellipticalCrack>();
        cracksRemoved = new ArrayList<SemiellipticalCrack>();
        this.parametrK = parametrK;
        this.yieldStress = yieldStress;
        this.sigma = sigma;
        cracksHistoryList = new ArrayList<ArrayList>();

    }

    /**
     * Set the value of matrix
     *
     * @param matrix new value of matrix
     */
    public void initMatrix(double height, double width, double grainHeight, double garinWidth) {
        Random rnd = new Random();
        int s = -rnd.nextInt(1000000) + 1;
        seed = new Integer(s);
        matPointsX = new double[numColumns][numRows];
        matPointsY = new double[numColumns][numRows];
        matrix = new boolean[numColumns][numRows];
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                matrix[i][j] = false;
            }
        }
    }

    ArrayList<SemiellipticalCrack> FillRandomCracks(double length2AMean, double length2AScale,
            double depthMean, double depthScale) throws DerivativeException, IntegratorException {
        if (isFilledCkracks() == false) {
            int i = 0;
            exitMaxCondition:
            {
                while (i < Nmax) {
                    timeIndex = i;
                    //ввести ще один цикл перевірки координати точки!!!
                    double rndX = UniformDistribution.PPF(RNG.Ran2(seed), 0, width);
                    double rndY = UniformDistribution.PPF(RNG.Ran2(seed), 0, height);
                    int rndI = (int) (rndX / grainWidth);
                    int rndJ = (int) (rndY / grainHeight);
                    double deltaT;
                    if (isSquareEmpty(matrix, rndI, rndJ)) {
                        // точку кинули в порожню клітину
                        matPointsX[rndI][rndJ] = rndX;
                        matPointsY[rndI][rndJ] = rndY;
                        matrix[rndI][rndJ] = true;
                        //потягнути з панелі
                        double length2A = NormalDistribution.PPF(RNG.Ran2(seed), length2AMean, length2AScale);
                        double depth = NormalDistribution.PPF(RNG.Ran2(seed), depthMean, depthScale);
                        SemiellipticalCrack newCrack = new SemiellipticalCrack(this, rndX, rndY, length2A, depth, i);
                        ellipticalCrack.add(newCrack);
                        double currentTime = timeObj.getInitTime().get(i);
//                        System.out.print("iMax = " + timeObj.getInitTime().size());
//                        System.out.println("i = " + i);

                        if (i == timeObj.getInitTime().size()-1) {
                            break exitMaxCondition;
                        }else if (i > 0) {
                            deltaT = timeObj.getInitTime().get(i + 1) - timeObj.getInitTime().get(i);
                        } else {
                            deltaT = timeObj.getInitTime().get(i);
                        }

//                        if ((newCrack.getLength2a() >= maxCrackLength)) {
//                            break exitMaxCondition;
//                        }

                        boolean coalescence = true;
                        boolean growth = false;
                        int iCoalescenceGrowth = 0;
                        //об'єднання тріщин
                        coalescenceOfCrack:
                        while (coalescence) {
                            crackSortedByRightTip = new ArrayList<SemiellipticalCrack>(ellipticalCrack);
                            Collections.sort(crackSortedByRightTip, new CrackSorterRTip());
                            if (iCoalescenceGrowth > 0 & !SortedPair.createPairs(crackSortedByRightTip)) {
                                break;
                            }
                            while (SortedPair.createPairs(crackSortedByRightTip)) {
                                //додати умову максимальної довжини тріщини
                                newCrack = new SemiellipticalCrack(SortedPair.getCoalescencePair().getCrackObj1(),
                                        SortedPair.getCoalescencePair().getCrackObj2(), i);
                                ellipticalCrack.add(newCrack);
                                cracksRemoved.add(SortedPair.getCoalescencePair().getCrackObj1());
                                cracksRemoved.add(SortedPair.getCoalescencePair().getCrackObj2());
                                ellipticalCrack.remove(SortedPair.getCoalescencePair().getCrackObj1());
                                ellipticalCrack.remove(SortedPair.getCoalescencePair().getCrackObj2());
                                crackSortedByRightTip = new ArrayList<SemiellipticalCrack>(ellipticalCrack);
                                Collections.sort(crackSortedByRightTip, new CrackSorterRTip());
//                                if (newCrack.getLength2a() >= maxCrackLength) {
//                                    break exitMaxCondition;
//                                }
                            }
                            //підростання тріщин
                            if (!growth) {
                                for (int j = 0; j < ellipticalCrack.size(); j++) {
                                    ellipticalCrack.get(j).integrate(currentTime, deltaT);
//                                    if (ellipticalCrack.get(j).getLength2a() >= maxCrackLength) {
//                                        break exitMaxCondition;
//                                    }
                                }
                            }

                            growth = true;
                            iCoalescenceGrowth++;
                        }
                        boolean maxLengthCondition = false;
                        for (int j = 0; j < ellipticalCrack.size(); j++) {
                            if (ellipticalCrack.get(j).getLength2a() >= maxCrackLength) {
                                maxLengthCondition = true;
                                ellipticalCrack.get(j).setMaxLength(true);
                            }
                        }
                        if (maxLengthCondition) {
                            maxCrackLengthTimeIndex = i;
                            break exitMaxCondition;
                        }
                        i++;
                    }

                }
            }
            filledCkracks = true;
        }
        paintedCracks = ellipticalCrack;
        return ellipticalCrack;


    }

    //   ****************
    //відладка
    public void print(List lst) {

        for (int i = 0; i < lst.size(); i++) {
            SemiellipticalCrack objectCrack = (SemiellipticalCrack) lst.get(i);
            System.out.println("Initiation Time: " + objectCrack.getTimeIndex()
                    + "\tCoordinate of right tip : " + objectCrack.getRightTip().getX() + " x "
                    + objectCrack.getCrackPoint().getY());
        }
        System.out.println("\n\n");
    }
//**********************

    public ArrayList<? extends SemiellipticalCrack> displayHistory(int timeIndex) {
        ArrayList<CrackHistory> dispHistList = new ArrayList<CrackHistory>();
        for (int i = 0; i < cracksHistoryList.size(); i++) {
            ArrayList historyOfCrack = cracksHistoryList.get(i);
            for (int j = 0; j < historyOfCrack.size(); j++) {
                CrackHistory historyInstance = (CrackHistory) historyOfCrack.get(j);
                if (timeIndex == historyInstance.getTimeIndex()) {
                    dispHistList.add(historyInstance);
                }
            }
        }
        paintedCracks = dispHistList;
        return paintedCracks;

    }

    /*
     * Сортує тріщини по координаті правої вершини
     */
    public void setParametrK(double parametrK) {
        this.parametrK = parametrK;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public void setYieldStress(double yieldStress) {
        this.yieldStress = yieldStress;
    }

    boolean isSquareEmpty(boolean[][] matrix, int i, int j) {
        if (matrix[i][j] == false) {
            return true;
        }
        return false;
    }

    public void setMatPointsX(int i, int j, int valueX) {
        this.matPointsX[i][j] = valueX;
    }

    public void setMatPointsY(int i, int j, int valueY) {
        this.matPointsY[i][j] = valueY;
    }

    public void setMatrix(int i, int j, boolean value) {
        this.matrix[i][j] = value;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public Integer getSeed() {
        return seed;
    }

    /**
     * Get the value of Nmax
     *
     * @return the value of Nmax
     */
    public int getNmax() {
        return Nmax;
    }

    /**
     * Get the value of numRows
     *
     * @return the value of numRows
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Get the value of numColumns
     *
     * @return the value of numColumns
     */
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Get the value of matPointsY
     *
     * @return the value of matPointsY
     */
    public double[][] getMatPointsY() {
        return matPointsY;
    }

    /**
     * Get the value of matPointsX
     *
     * @return the value of matPointsX
     */
    public double[][] getMatPointsX() {
        return matPointsX;
    }

    /**
     * Get the value of grainWidth
     *
     * @return the value of grainWidth
     */
    public double getGrainWidth() {
        return grainWidth;
    }

    /**
     * Set the value of grainWidth
     *
     * @param grainWidth new value of grainWidth
     */
    public void setGrainWidth(int grainWidth) {
        this.grainWidth = grainWidth;
    }

    /**
     * Get the value of grainHeight
     *
     * @return the value of grainHeight
     */
    public double getGrainHeight() {
        return grainHeight;
    }

    /**
     * Set the value of grainHeight
     *
     * @param grainHeight new value of grainHeight
     */
    public void setGrainHeight(int grainHeight) {
        this.grainHeight = grainHeight;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(int width) {
        this.width = width;
    }

//    public ArrayList getPointObjList() {
//        return pointObjList;
//    }
//
//    public void setPointObjList(ArrayList pointObjList) {
//        this.pointObjList = pointObjList;
//    }
    public boolean isFilledCkracks() {
        return filledCkracks;
    }

    public void setFilleddCkracks(boolean filleddCkracks) {
        this.filledCkracks = filleddCkracks;
    }

    public InitiationTime getTimeObj() {
        return timeObj;
    }

    public void setTimeObj(InitiationTime timeObj) {
        this.timeObj = timeObj;
    }

//    public ArrayList<SemiellipticalCrack> getEllipticalCrack() {
//        return ellipticalCrack;
//    }
//    public void setEllipticalCrack(ArrayList ellipticalCrack) {
//        this.ellipticalCrack = ellipticalCrack;
//    }
    public double getParametrK() {
        return parametrK;
    }

    public double getSigma() {
        return sigma;
    }

    public double getYieldStress() {
        return yieldStress;
    }

    public int getVisualKValue() {
        return visualKValue;
    }

    public void setVisualKValue(int visualKValue) {
        this.visualKValue = visualKValue;
    }

    public double getMaxCrackLength() {
        return maxCrackLength;
    }

    public void setMaxCrackLength(double maxCrackLength) {
        this.maxCrackLength = maxCrackLength;
    }

    public ArrayList<ArrayList> getCracksHistoryList() {
        return cracksHistoryList;
    }

    public void setCracksHistoryList(ArrayList<ArrayList> cracksHistoryList) {
        this.cracksHistoryList = cracksHistoryList;
    }

    public ArrayList<SemiellipticalCrack> getCracksRemoved() {
        return cracksRemoved;
    }

    public void setCracksRemoved(ArrayList<SemiellipticalCrack> cracksRemoved) {
        this.cracksRemoved = cracksRemoved;
    }

    public int getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(int timeIndex) {
        this.timeIndex = timeIndex;
    }

    public ArrayList<? extends SemiellipticalCrack> getPaintedCracks() {
        return paintedCracks;
    }

    public void setPaintedCracks(ArrayList<SemiellipticalCrack> paintedCracks) {
        this.paintedCracks = paintedCracks;
    }

    public Double[] getMaxCrackTimeIndexArray() {
        timeObj.getInitTime().toArray();
        Double[] crackTimeIndexArray = timeObj.getInitTime().toArray(new Double[maxCrackLengthTimeIndex]);

        return crackTimeIndexArray;
    }

    public int getMaxCrackLengthTimeIndex() {
        return maxCrackLengthTimeIndex;
    }

    public String getMaxCrackLengthTimeIndexS() {
        return (new Integer(maxCrackLengthTimeIndex)).toString();
    }
}
