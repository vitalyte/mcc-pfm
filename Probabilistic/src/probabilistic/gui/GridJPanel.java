/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GridJPanel.java
 *
 * Created on 22 лют 2010, 14:05:53
 */
package probabilistic.gui;

import java.awt.Graphics;
import java.util.Random;
import probabilistic.*;

/**
 *
 * @author Vitaly
 */
public class GridJPanel extends javax.swing.JPanel {

    /** Creates new form GridJPanel */
    private int height = 400;
    private int width = 400;
    private int grainHeight = 200;
    private int grainWidth = 200;
    private SurfaceArea surface;
    private SemiellipticalCrack crack;
    {
    }
//    SurfaceArea area = new SurfaceArea(height, width, grainHeight, grainWidth);

    public GridJPanel() {
        initComponents();
        surface = new SurfaceArea(height, width, grainHeight, grainWidth);
        FillRandomCracks(height, width, grainHeight, grainWidth);
    }

    //static boolean notPaint = false;
    // @Override
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.setSize(width, height);
        int widthWindow = this.getWidth();
        int heightWindow = this.getHeight();
        for (int i = 0; i <= widthWindow; i += grainWidth) {
            g.drawLine(i, 0, i, heightWindow);
        }
        for (int j = 0; j <= heightWindow; j += grainHeight) {
            g.drawLine(0, j, widthWindow, j);
        }
        for (int i = 0; i < surface.getNumColumns(); i++) {
            for (int j = 0; j < surface.getNumRows(); j++) {
                g.drawLine((int)surface.getMatPointsX()[i][j] -(int) crack.getLength2a()/2,
                        surface.getMatPointsY()[i][j],
                        surface.getMatPointsX()[i][j] + (int)crack.getLength2a()/2,
                        surface.getMatPointsY()[i][j]);
            }
        }

    }

    boolean isSquareEmpty(boolean[][] matrix, int i, int j) {
        if (matrix[i][j] == false) {
            return true;
        }
        return false;
    }

    void FillRandomCracks(int height, int width, int grainHeight, int grainWidth) {
        
        Integer seed = surface.getSeed();
        int i = 0;
        while (i < surface.getNmax()) {
            
            //ввести ще один цикл перевірки координати точки!!!

            double rndX = UniformDistribution.PPF(RNG.Ran2(seed), 0, surface.getWidth());
            double rndY = UniformDistribution.PPF(RNG.Ran2(seed), 0, surface.getHeight());
            int rndI = (int) rndX / surface.getGrainWidth();
            int rndJ = (int) rndY / surface.getGrainHeight();
            if (surface.isSquareEmpty(surface.getMatrix(), rndI, rndJ)) {
                crack = new SemiellipticalCrack();
                // точку кинули в порожню клітину
                crack.setSiteX((int) rndX);
                surface.setMatPointsX(rndI, rndJ, crack.getSiteX());
                crack.setSiteY((int) rndY);
                surface.setMatPointsY(rndI, rndJ, crack.getSiteY());
                surface.setMatrix(rndI, rndJ, true);
            }

            i++;
        }
    }

    public SemiellipticalCrack getCrack() {
        return crack;
    }



    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder("grid"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
