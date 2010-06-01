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
//import java.util.ArrayList;
//import java.util.Random;
//import probabilistic.*;
//import probabilistic.NormalDistribution;

/**
 *
 * @author Vitaly
 */
public class GridJPanel extends javax.swing.JPanel {

    /** Creates new form GridJPanel */
    private int height = 300;
    private int width = 300;
    private int grainHeight = 100;
    private int grainWidth = 100;
    private SurfaceArea surface;
//    private SemiellipticalCrack crack;
//    private InitiationTime time;

//    SurfaceArea area = new SurfaceArea(height, width, grainHeight, grainWidth);
    public GridJPanel() {
        initComponents();
        surface = new SurfaceArea(height, width, grainHeight, grainWidth, 0.3, 34.54);
        surface.FillRandomCracks(height, width, grainHeight, grainWidth, 10, 8, 10, 8);
    }

//    public GridJPanel(int height_, int width_, int grainHeight_, int grainWidth_) {
//        height = height_;
//        width = width_;
//        grainHeight = grainHeight_;
//        grainWidth = grainWidth_;
//
//        surface = new SurfaceArea(height_, width_, grainHeight_, grainWidth_);
////        time = new InitiationTime(surface.getNmax(), 0.3, 34.54);
//        surface.FillRandomCracks(height_, width_, grainHeight_, grainWidth_);
//    }
    //static boolean notPaint = false;
    //paint grid
    @Override
    public void paintComponent(Graphics g) {
        width = surface.getWidth();
        height = surface.getHeight();
        grainWidth = surface.getGrainWidth();
        grainHeight = surface.getGrainHeight();
        super.paintComponent(g);
        this.setSize(surface.getWidth(), surface.getHeight());
        int widthWindow = surface.getWidth();
        int heightWindow = surface.getHeight();
        for (int i = 0; i <= widthWindow; i += grainWidth) {
            g.drawLine(i, 0, i, heightWindow);
        }
        for (int j = 0; j <= heightWindow; j += grainHeight) {
            g.drawLine(0, j, widthWindow, j);
        }
//        for (int i = 0; i < surface.getNumColumns(); i++) {
//            for (int j = 0; j < surface.getNumRows(); j++) {
//                g.drawLine((int) surface.getMatPointsX()[i][j] - (int) crack.getLength2a() / 2,
//                        surface.getMatPointsY()[i][j],
//                        surface.getMatPointsX()[i][j] + (int) crack.getLength2a() / 2,
//                        surface.getMatPointsY()[i][j]);
//            }
//        }

        for (int i = 0; i < surface.getEllipticalCrack().size(); i++) {
            SemiellipticalCrack crack = (SemiellipticalCrack) surface.getEllipticalCrack().get(i);
            g.drawLine((crack.getSiteX() - (int) crack.getLength2a()), crack.getSiteY(),
                    (crack.getSiteX() + (int) crack.getLength2a()), crack.getSiteY());

        }


    }

//    boolean isSquareEmpty(boolean[][] matrix, int i, int j) {
//        if (matrix[i][j] == false) {
//            return true;
//        }
//        return false;
//    }
//    void FillRandomCracks() {
//        this.FillRandomCracks(this.height, this.width, this.grainHeight, this.grainWidth);
//    }
//
//    void FillRandomCracks(int height, int width, int grainHeight, int grainWidth) {
//
//        if (surface.isFilleddCkracks() == false) {
//            Integer seed = surface.getSeed();
//            int i = 0;
//            while (i < surface.getNmax()) {
//                //ввести ще один цикл перевірки координати точки!!!
//                double rndX = UniformDistribution.PPF(RNG.Ran2(seed), 0, surface.getWidth());
//                double rndY = UniformDistribution.PPF(RNG.Ran2(seed), 0, surface.getHeight());
//                int rndI = (int) rndX / surface.getGrainWidth();
//                int rndJ = (int) rndY / surface.getGrainHeight();
//                if (surface.isSquareEmpty(surface.getMatrix(), rndI, rndJ)) {
//                     //потягнути з панелі
//                    double length2A = NormalDistribution.PPF(RNG.Ran2(seed),10,1 );
//                    double depth = NormalDistribution.PPF(RNG.Ran2(seed), 2,1);
//                    crack = new SemiellipticalCrack(surface, rndX, rndY, length2A, depth, i);
//                    Double crackTime = (Double) surface.getTimeObj().getInitTime().get(i);
//
//                    // точку кинули в порожню клітину
//                    crack.setSiteX((int) rndX);
//                    surface.setMatPointsX(rndI, rndJ, crack.getSiteX());
//                    crack.setSiteY((int) rndY);
//                    surface.setMatPointsY(rndI, rndJ, crack.getSiteY());
//                    surface.setMatrix(rndI, rndJ, true);
//                    surface.getEllipticalCrack().add(crack);
//                    i++;
//                }
//            }
//            surface.setFilleddCkracks(true);
//        }
//        ////відладка
//        print(surface.getEllipticalCrack());
//
//    }
//
// //   ****************
//    //відладка
//    public void print(ArrayList lst) {
//        lst = surface.getEllipticalCrack();
//        for (int i = 0; i < lst.size(); i++) {
//            SemiellipticalCrack objectCrack = (SemiellipticalCrack) lst.get(i);
//            System.out.println("Initiation Time: " + objectCrack.getInitiationTime()
//                    + "\tCoordinate : " + objectCrack.getCrackPoint().getX() + " x "
//                    + objectCrack.getCrackPoint().getY());
//        }
//        System.out.println("\n\n");
//    }
////**********************
//    public InitiationTime getTime() {
//        return time;
//    }
//
//    public void setTime(InitiationTime time) {
//        this.time = time;
//    }
//
//
//    public SemiellipticalCrack getCrack() {
//        return crack;
//    }
    public SurfaceArea getSurface() {
        return surface;
    }

    public void setSurface(SurfaceArea surface) {
        this.surface = surface;
    }

    public void setGrainHeight(int grainHeight) {
        this.grainHeight = grainHeight;
    }

    public void setGrainWidth(int grainWidth) {
        this.grainWidth = grainWidth;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getGrainHeight() {
        return grainHeight;
    }

    public int getGrainWidth() {
        return grainWidth;
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