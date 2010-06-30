/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

//import sorting.CrackSorterRTip;
import integration.CrackOrderOde;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.apache.commons.math.ode.DerivativeException;
import org.apache.commons.math.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math.ode.FirstOrderIntegrator;
import org.apache.commons.math.ode.IntegratorException;
import org.apache.commons.math.ode.nonstiff.DormandPrince853Integrator;
import probabilistic.*;
import sorting.CrackSorterRTip;

/**
 *
 * @author Vitaly
 */
public class SurfaceArea {

    private int height = 400;
    private int width = 400;
    private int grainHeight = 200;
    private int grainWidth = 200;
    private int[][] matPointsX;
    private int[][] matPointsY;
    private int Nmax;
    private int numColumns;
    private int numRows;
    double parametrK, yieldStress, sigma;
    private Integer seed;
    private boolean[][] matrix;
//    private ArrayList pointObjList;
    boolean filledCkracks = false;
    private InitiationTime timeObj;
    private ArrayList<SemiellipticalCrack> ellipticalCrack;
    private ArrayList<SemiellipticalCrack> crackSortedByRightTip;
    double maxCrackLength;
    double k1SCC = 2;

    public SurfaceArea(int height, int width, int grainHeight, int grainWidth,
            double meanInitiationTime, double scaleInitiationTime, double sigma, double yieldStress, double parametrK) {
        this.width = width;
        this.height = height;
        this.grainHeight = grainHeight;
        this.grainWidth = grainWidth;
        Nmax = (width * height) / (grainWidth * grainHeight);
        numColumns = this.width / this.grainWidth;
        numRows = this.height / this.grainHeight;
        initMatrix(height, width, grainHeight, grainWidth);
        timeObj = new InitiationTime(Nmax, meanInitiationTime, scaleInitiationTime);
        ellipticalCrack = new ArrayList<SemiellipticalCrack>();
        this.parametrK = parametrK;
        this.yieldStress = yieldStress;
        this.sigma = sigma;
    }

    /**
     * Set the value of matrix
     *
     * @param matrix new value of matrix
     */
    public void initMatrix(int height, int width, int grainHeight, int garinWidth) {
        Random rnd = new Random();
        int s = -rnd.nextInt(1000000) + 1;
        seed = new Integer(s);
        matPointsX = new int[numColumns][numRows];
        matPointsY = new int[numColumns][numRows];
        matrix = new boolean[numColumns][numRows];
        for (int i = 0; i < numColumns; i++) {
            for (int j = 0; j < numRows; j++) {
                matrix[i][j] = false;
            }
        }
    }

    void FillRandomCracks(double length2AMean, double length2AScale,
            double depthMean, double depthScale) throws DerivativeException, IntegratorException {
        if (isFilleddCkracks() == false) {
            int i = 0;
            while (i < Nmax) {
                //ввести ще один цикл перевірки координати точки!!!
                double rndX = UniformDistribution.PPF(RNG.Ran2(seed), 0, width);
                double rndY = UniformDistribution.PPF(RNG.Ran2(seed), 0, height);
                int rndI = (int) rndX / grainWidth;
                int rndJ = (int) rndY / grainHeight;
                double deltaT;
                if (isSquareEmpty(matrix, rndI, rndJ)) {
                    // точку кинули в порожню клітину
                    matPointsX[rndI][rndJ] = (int) rndX;
                    matPointsY[rndI][rndJ] = (int) rndY;
                    matrix[rndI][rndJ] = true;
                    //потягнути з панелі
                    double length2A = NormalDistribution.PPF(RNG.Ran2(seed), length2AMean, length2AScale);
                    double depth = NormalDistribution.PPF(RNG.Ran2(seed), depthMean, depthScale);
                    ellipticalCrack.add(new SemiellipticalCrack(this, rndX, rndY, length2A, depth, i));
                    double currentTime = timeObj.getInitTime().get(i);
//                    double previousTime = timeObj.getInitTime().get(i);
                    if (i > 0) {
                        deltaT = timeObj.getInitTime().get(i) - timeObj.getInitTime().get(i - 1);
//                        previousTime = timeObj.getInitTime().get(i - 1);
                    } else {
                        deltaT = timeObj.getInitTime().get(i);
                    }

//                    boolean coalescence = true;
                    boolean growth = true;
                    while (growth) {

                        //об'єднання тріщин
                        crackSortedByRightTip = new ArrayList<SemiellipticalCrack>(ellipticalCrack);
                        Collections.sort(crackSortedByRightTip, new CrackSorterRTip());
                        while (SortedPair.createPairs(crackSortedByRightTip)) {
                            //додати умову максимальної довжини тріщини
                            ellipticalCrack.add(new SemiellipticalCrack(SortedPair.getCoalescencePair().getCrackObj1(), SortedPair.getCoalescencePair().getCrackObj2(), i));
                            ellipticalCrack.remove(SortedPair.getCoalescencePair().getCrackObj1());
                            ellipticalCrack.remove(SortedPair.getCoalescencePair().getCrackObj2());
                            crackSortedByRightTip = new ArrayList<SemiellipticalCrack>(ellipticalCrack);
                            Collections.sort(crackSortedByRightTip, new CrackSorterRTip());
//                        pairObj = new SortedPair(ellipticalCrack);
                        }

                        //підростання тріщин
                        growth = false;
                        for (int j = 0; j < ellipticalCrack.size(); j++) {
                            double beforeGrowthLength = ellipticalCrack.get(j).getLength2a();
                            double beforeGrowthDepth = ellipticalCrack.get(j).getDepthB();
                            FirstOrderIntegrator dp853 = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
                            FirstOrderDifferentialEquations ode = new CrackOrderOde(new double[]{ellipticalCrack.get(j).SIF_A(), ellipticalCrack.get(j).SIF_B()}, k1SCC);
                            double[] y = new double[]{0.0, 0.0}; // initial state
                            dp853.integrate(ode, 0.0, y, currentTime, y); // now y contains final state at time t=16.0
                            ellipticalCrack.get(j).setLength2a(y[0]);
                            ellipticalCrack.get(j).setDepthB(y[1]);
                            if (beforeGrowthLength != ellipticalCrack.get(j).getLength2a()
                                    || beforeGrowthDepth != ellipticalCrack.get(j).getDepthB()) {
                                growth = true;
                            }
                        }
                    }











                    i++;
                }
            }
            filledCkracks = true;
        }
        ////відладка
//        Collections.sort(crackSortedByRightTip);
//        Collections.copy(crackSortedByRightTip, ellipticalCrack);
//        Collections.sort(ellipticalCrack);

//        print(ellipticalCrack);
//        print(crackSortedByRightTip);

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
    public int[][] getMatPointsY() {
        return matPointsY;
    }

    /**
     * Get the value of matPointsX
     *
     * @return the value of matPointsX
     */
    public int[][] getMatPointsX() {
        return matPointsX;
    }

    /**
     * Get the value of grainWidth
     *
     * @return the value of grainWidth
     */
    public int getGrainWidth() {
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
    public int getGrainHeight() {
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
    public int getHeight() {
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
    public int getWidth() {
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
    public boolean isFilleddCkracks() {
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

    public ArrayList getEllipticalCrack() {
        return ellipticalCrack;
    }

    public void setEllipticalCrack(ArrayList ellipticalCrack) {
        this.ellipticalCrack = ellipticalCrack;
    }

    public double getParametrK() {
        return parametrK;
    }

    public double getSigma() {
        return sigma;
    }

    public double getYieldStress() {
        return yieldStress;
    }
}
