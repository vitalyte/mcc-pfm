/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

//import sorting.CrackSorterRTip;

/**
 *
 * @author Vitaly
 */
public class SurfaceArea {

    private int Nmax;
    private double height;
    private double width;
    private double grainHeight;
    private double grainWidth;

    private int numColumns;
    private int numRows;


    public SurfaceArea(double height, double width, double grainHeight, double grainWidth) {
        this.width = width;
        this.height = height;
        this.grainHeight = grainHeight;
        this.grainWidth = grainWidth;
        numColumns = (int) (this.width / this.grainWidth);
        numRows = (int) (this.height / this.grainHeight);

        //визначення кратних параметрів
        Nmax = numColumns * numRows;
        this.width = numColumns * this.grainWidth;
        this.height = numRows * this.grainHeight;

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


}
