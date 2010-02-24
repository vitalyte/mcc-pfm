/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

import java.util.Random;
import probabilistic.*;

/**
 *
 * @author Vitaly
 */
public class SemiellipticalCrack {

    private int initiationTime;
    private int siteX;
    private int siteY;
    private int length2a;
    private int lengthB;
    private double aspectRatio;
    private static SurfaceArea surfaceAreaObj;
    private static boolean[][] matrix;
    private static Integer seed;
    private static int Nmax;
    public static int[][] matPointsX;
    public static int[][] matPointsY;

    public SemiellipticalCrack() {


        double rndX = UniformDistribution.PPF(RNG.Ran2(seed), 0, surfaceAreaObj.getGrainWidth());
        double rndY = UniformDistribution.PPF(RNG.Ran2(seed), 0, surfaceAreaObj.getGrainHeight());
        int rndI = (int) rndX / surfaceAreaObj.getGrainWidth();
        int rndJ = (int) rndY / surfaceAreaObj.getGrainHeight();
        if (isSquareEmpty(matrix, rndI, rndJ)) {
            // точку кинули в порожню клітину
            setSiteX((int) rndX);
            matPointsX[rndI][rndJ] = siteX;
            setSiteY((int) rndY);
            matPointsY[rndI][rndJ] = siteY;
            matrix[rndI][rndJ] = true;
        }
    }

    /**
     * Set the value of matrix
     *
     * @param matrix new value of matrix
     */
    public static void initMatrix(int width, int height, int garinWidth, int grainHeight) {
        surfaceAreaObj = new SurfaceArea(height, width, grainHeight, garinWidth);
        Random rnd = new Random();
        int s = -rnd.nextInt(1000000) + 1;
        SemiellipticalCrack.seed = new Integer(s);
        matPointsX = new int[SurfaceArea.getNumColumns()][SurfaceArea.getNumRows()];
        matPointsY = new int[SurfaceArea.getNumColumns()][SurfaceArea.getNumRows()];

        SemiellipticalCrack.matrix = new boolean[SurfaceArea.getNumColumns()][SurfaceArea.getNumRows()];
        for (int i = 0; i < SurfaceArea.getNumColumns(); i++) {
            for (int j = 0; j < SurfaceArea.getNumRows(); j++) {
                SemiellipticalCrack.matrix[i][j] = false;
            }
        }
    }

    /**
     * Get the value of matPointsY
     *
     * @return the value of matPointsY
     */
    public static int[][] getMatPointsY() {
        return matPointsY;
    }

    /**
     * Get the value of matPointsX
     *
     * @return the value of matPointsX
     */
    public static int[][] getMatPointsX() {
        return matPointsX;
    }

    /**
     * Get the value of Nmax
     *
     * @return the value of Nmax
     */
    public static int getNmax() {
        return Nmax;
    }

    /**
     * Get the value of seed
     *
     * @return the value of seed
     */
    public Integer getSeed() {
        return seed;
    }

    /**
     * Get the value of surfaceAreaObj
     *
     * @return the value of surfaceAreaObj
     */
    public SurfaceArea getSurfaceAreaObj() {
        return surfaceAreaObj;
    }

    boolean isSquareEmpty(boolean[][] matrix, int i, int j) {
        if (matrix[i][j] == false) {
            return true;
        }
        return false;
    }

    /**
     * Get the value of matrix
     *
     * @return the value of matrix
     */
    public boolean[][] getMatrix() {
        return matrix;
    }

    /**
     * Set the value of matrix
     *
     * @param matrix new value of matrix
     */
    public void setMatrix(boolean[][] matrix) {
        SemiellipticalCrack.matrix = matrix;
    }

    /**
     * Get the value of aspectRatio
     *
     * @return the value of aspectRatio
     */
    public double getAspectRatio() {
        return aspectRatio;
    }

    /**
     * Set the value of aspectRatio
     *
     * @param aspectRatio new value of aspectRatio
     */
    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Get the value of crackLengthB
     *
     * @return the value of crackLengthB
     */
    public int getLengthB() {
        return lengthB;
    }

    /**
     * Set the value of crackLengthB
     *
     * @param crackLengthB new value of crackLengthB
     */
    public void setLengthB(int crackLengthB) {
        this.lengthB = crackLengthB;
    }

    /**
     * Get the value of crackLength
     *
     * @return the value of crackLength
     */
    public int getLength2a() {
        return length2a;
    }

    /**
     * Set the value of crackLength
     *
     * @param crackLength new value of crackLength
     */
    public void setLength2a(int crackLength) {
        this.length2a = crackLength;
    }

    /**
     * Get the value of crackSiteY
     *
     * @return the value of crackSiteY
     */
    public int getSiteY() {
        return siteY;
    }

    /**
     * Set the value of crackSiteY
     *
     * @param crackSiteY new value of crackSiteY
     */
    public void setSiteY(int crackSiteY) {
        this.siteY = crackSiteY;
    }

    /**
     * Get the value of crackSiteX
     *
     * @return the value of crackSiteX
     */
    public int getSiteX() {
        return siteX;
    }

    /**
     * Set the value of crackSiteX
     *
     * @param crackSiteX new value of crackSiteX
     */
    public void setSiteX(int crackSiteX) {
        this.siteX = crackSiteX;
    }

    /**
     * Get the value of initiationTime
     *
     * @return the value of initiationTime
     */
    public int getInitiationTime() {
        return initiationTime;
    }

    /**
     * Set the value of initiationTime
     *
     * @param initiationTime new value of initiationTime
     */
    public void setInitiationTime(int initiationTime) {
        this.initiationTime = initiationTime;
    }
}
