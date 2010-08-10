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

import probabilistic.SurfaceArea;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import probabilistic.SemiellipticalCrack;
import probabilistic.Simulation;

/**
 *
 * @author Vitaly
 */
public class GridJPanel extends javax.swing.JPanel {

    private int height;
    private int width;
    private int grainHeight;
    private int grainWidth;
    private Simulation simulObj;

    public GridJPanel() {
        initComponents();
    }

    public GridJPanel(Simulation simulObj) {
        initComponents();
        this.simulObj = simulObj;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (simulObj != null) {
            int k = (int) simulObj.getVisualScale();
            SurfaceArea surface = Simulation.getSurface();
            width = (int) (k * surface.getWidth());
            height = (int) (k * surface.getHeight());
            grainWidth = (int) (k * surface.getGrainWidth());
            grainHeight = (int) (k * surface.getGrainHeight());
            this.setSize(width, height);
            Graphics2D g2 = (Graphics2D) g;
            for (int i = 0; i < simulObj.getPainted().size(); i++) {
                SemiellipticalCrack crack = simulObj.getPainted().get(i);
                g2.setColor(Color.GREEN);
                if (crack.isCoalescenced()) {
                    g2.setColor(Color.BLUE);
                }
                if (crack.checkMaxCondition()) {
                    g2.setColor(Color.red);
                }
                g2.draw(crack.getPolyline(k));
//                g2.draw(crack.getAverageLine(k));
                g2.setColor(Color.ORANGE);
                g2.draw(crack.getSressReleazeZone(k));

                
            }
        }
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
