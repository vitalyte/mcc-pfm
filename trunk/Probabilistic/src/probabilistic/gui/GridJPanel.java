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

import probabilistic.CrackHistory;
import probabilistic.SurfaceArea;
import java.awt.Color;
import java.awt.Graphics;
import probabilistic.SemiellipticalCrack;
import probabilistic.Simulation;
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
    private int height;
    private int width;
    private int grainHeight;
    private int grainWidth;
    private Simulation simulObj;
//    private SemiellipticalCrack crack;
//    private InitiationTime time;

//    SurfaceArea area = new SurfaceArea(height, width, grainHeight, grainWidth);
    public GridJPanel() {
        initComponents();
//        surface = new SurfaceArea(height, width, grainHeight, grainWidth, 0.3, 34.54, 500000000, 5, 500000000);
//        surface.FillRandomCracks(10, 8, 10, 8);
    }

    public GridJPanel(Simulation simulObj) {
        initComponents();
        this.simulObj = simulObj;
//        surface.FillRandomCracks(10, 8, 10, 8);
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
        super.paintComponent(g);
        if (simulObj != null) {
            int k = (int) simulObj.getVisualScale();
            SurfaceArea surface = simulObj.getSurface();
            width = (int) (k * surface.getWidth());
            height = (int) (k * surface.getHeight());
            grainWidth = (int) (k * surface.getGrainWidth());
            grainHeight = (int) (k * surface.getGrainHeight());
            this.setSize(width, height);
//            System.out.println("\n\n1");

            for (int i = 0; i < simulObj.getPainted().size(); i++) {
                SemiellipticalCrack crack = simulObj.getPainted().get(i);
                g.setColor(Color.GREEN);
//                if (!crack.isCriticalCreack()) {
//                    g.setColor(Color.GREEN);
//                }
                if (crack.getCrackTips().size() >2) {
                    g.setColor(Color.BLUE);

                }
                if (crack.checkMaxCondition()) {
                    g.setColor(Color.red);
                }

//          //                super.paintComponent(g);
//                g.drawLine((int) (k * crack.getLeftTip().getX()), (int) (k * crack.getLeftTip().getY()),
//                        (int) (k * crack.getRightTip().getX()), (int) (k * crack.getRightTip().getY()));
                g.drawPolyline(crack.getArrayPolyline(k)[0], crack.getArrayPolyline(k)[1], crack.getCrackTips().size());
//                g.setColor(Color.BLACK);
//                g.drawLine((int) (k * crack.getLeftTip().getX()), (int) (k * crack.getLeftTip().getY()),
//                        (int) (k * crack.getRightTip().getX()), (int) (k * crack.getLeftTip().getY()));



            }
        }
//        else         {
//            super.paintComponent(g);
////            int a = 100;
////            this.setSize(a, a);
//
//
//
//        }




    }

    public Simulation getSimulObj() {
        return simulObj;
    }

    public void setSimulObj(Simulation simulObj) {
        this.simulObj = simulObj;
    }

    public SurfaceArea getSurface() {
        return simulObj.getSurface();
    }
//
//    public void setSurface(SurfaceArea surface) {
//        this.surface = surface;
//    }

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
