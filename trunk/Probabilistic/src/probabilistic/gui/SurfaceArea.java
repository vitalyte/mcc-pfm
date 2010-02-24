/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic.gui;

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
    private static int Nmax;
    private static int numColumns;
    private static int numRows;

    public SurfaceArea(int height, int width, int grainHeight, int grainWidth) {
        this.width = width;
        this.height = height;
        this.grainHeight = grainHeight;
        this.grainWidth = grainWidth;
        Nmax = width * height / grainWidth * grainHeight;
        matPointsX = new int[this.width / this.grainWidth][this.height / this.grainHeight];
        matPointsY = new int[this.width / this.grainWidth][this.height / this.grainHeight];
        SurfaceArea.numColumns = this.width / this.grainWidth;
        SurfaceArea.numRows = this.height / this.grainHeight;
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
     * Get the value of numRows
     *
     * @return the value of numRows
     */
    public static int getNumRows() {
        return numRows;
    }

    /**
     * Get the value of numColumns
     *
     * @return the value of numColumns
     */
    public static int getNumColumns() {
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
}
