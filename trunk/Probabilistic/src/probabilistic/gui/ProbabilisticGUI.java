/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProbabilisticGUI.java
 *
 * Created on 22 лют 2010, 16:10:36
 */
package probabilistic.gui;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Vitaly
 */
public class ProbabilisticGUI extends javax.swing.JFrame {

    /** Creates new form ProbabilisticGUI */
    public ProbabilisticGUI() {
        initComponents();
        //Container content = getContentPane();

//        gridJPanel1.setSize(new Dimension(400, 400));
//        gridJPanel1.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
//        gridJPanel1.setBackground(Color.white);
//        gridJPanel1.setVisible(true);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Geometry = new javax.swing.JTabbedPane();
        SurfaceArea = new javax.swing.JPanel();
        surfaceHeight = new javax.swing.JTextField();
        height = new javax.swing.JLabel();
        width = new javax.swing.JLabel();
        surfaceWidth = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        heightjLabel = new javax.swing.JLabel();
        crackLength = new javax.swing.JTextField();
        dethjLabel = new javax.swing.JLabel();
        crackDepth = new javax.swing.JTextField();
        submitCrack = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        gHeight = new javax.swing.JTextField();
        gWidth = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        submitCrack1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        repaint = new javax.swing.JButton();
        gridJPanel1 = new probabilistic.gui.GridJPanel();
        surfaceGeometry = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        surfaceHeight.setText("600");
        surfaceHeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surfaceHeightActionPerformed(evt);
            }
        });

        height.setText("Height:");

        width.setText("Width:");

        surfaceWidth.setText("600");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Surface");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Crack");

        heightjLabel.setText("Length:");

        crackLength.setText("50");
        crackLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crackLengthActionPerformed(evt);
            }
        });

        dethjLabel.setText("Depth:");

        crackDepth.setText("10");
        crackDepth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crackDepthActionPerformed(evt);
            }
        });

        submitCrack.setText("submit");
        submitCrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCrackActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Grain");

        gHeight.setText("200");
        gHeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gHeightActionPerformed(evt);
            }
        });

        gWidth.setText("200");
        gWidth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gWidthActionPerformed(evt);
            }
        });

        jLabel4.setText("height");

        jLabel5.setText("width");

        submitCrack1.setText("submit");
        submitCrack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCrack1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SurfaceAreaLayout = new javax.swing.GroupLayout(SurfaceArea);
        SurfaceArea.setLayout(SurfaceAreaLayout);
        SurfaceAreaLayout.setHorizontalGroup(
            SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SurfaceAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SurfaceAreaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(179, Short.MAX_VALUE))
                    .addGroup(SurfaceAreaLayout.createSequentialGroup()
                        .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SurfaceAreaLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(29, 29, 29)
                                .addComponent(gWidth, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SurfaceAreaLayout.createSequentialGroup()
                                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(heightjLabel)
                                    .addComponent(dethjLabel)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(crackDepth, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(crackLength, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                    .addComponent(gHeight, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SurfaceAreaLayout.createSequentialGroup()
                                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(width)
                                    .addComponent(height))
                                .addGap(22, 22, 22)
                                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(surfaceHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(submitCrack1, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                        .addComponent(submitCrack, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                        .addComponent(surfaceWidth, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)))))
                        .addGap(91, 91, 91))))
        );
        SurfaceAreaLayout.setVerticalGroup(
            SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SurfaceAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SurfaceAreaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(height))
                    .addComponent(surfaceHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(width)
                    .addComponent(surfaceWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(gWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitCrack1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heightjLabel)
                    .addComponent(crackLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dethjLabel)
                    .addComponent(crackDepth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitCrack)
                .addContainerGap())
        );

        Geometry.addTab("Geometry", SurfaceArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );

        Geometry.addTab("Crack", jPanel1);

        repaint.setText("REPAINT");
        repaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repaintActionPerformed(evt);
            }
        });

        gridJPanel1.setBackground(new java.awt.Color(244, 237, 237));
        gridJPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)));

        javax.swing.GroupLayout gridJPanel1Layout = new javax.swing.GroupLayout(gridJPanel1);
        gridJPanel1.setLayout(gridJPanel1Layout);
        gridJPanel1Layout.setHorizontalGroup(
            gridJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        gridJPanel1Layout.setVerticalGroup(
            gridJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );

        surfaceGeometry.setText("hxw surface=" + gridJPanel1.getSurface().getHeight() + "x"
            + gridJPanel1.getSurface().getWidth() + " "
            + "grain=" + gridJPanel1.getSurface().getGrainHeight() + "x"
            + gridJPanel1.getSurface().getGrainWidth());

        fileMenu.setText("File");

        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setText("Save As ...");
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setText("Help");

        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gridJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surfaceGeometry))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Geometry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(repaint, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Geometry, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(repaint))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(surfaceGeometry)
                        .addGap(2, 2, 2)
                        .addComponent(gridJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(445, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void crackDepthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crackDepthActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_crackDepthActionPerformed

    private void repaintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repaintActionPerformed
        // TODO add your handling code here:
        Graphics g = this.getGraphics();
        this.validateTree();
        this.repaint();

    }//GEN-LAST:event_repaintActionPerformed

    private void submitCrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCrackActionPerformed
        // TODO add your handling code here:
        gridJPanel1.getCrack().setLength2a(Integer.parseInt(crackLength.getText()));

        gridJPanel1.FillRandomCracks();

        gridJPanel1.validate();
        gridJPanel1.invalidate();
        //this.validateTree();
        gridJPanel1.repaint();





    }//GEN-LAST:event_submitCrackActionPerformed

    private void crackLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crackLengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crackLengthActionPerformed

    private void surfaceHeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surfaceHeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_surfaceHeightActionPerformed

    private void gHeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gHeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gHeightActionPerformed

    private void gWidthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gWidthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gWidthActionPerformed

    private void submitCrack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCrack1ActionPerformed
        // TODO add your handling code here:



        int sWidth_ = (Integer.parseInt(surfaceWidth.getText()));
        int sHeight_ = (Integer.parseInt(surfaceHeight.getText()));
        int gWidth_ = (Integer.parseInt(gWidth.getText()));
        int gHeight_ = (Integer.parseInt(gHeight.getText()));
        gridJPanel1.setSurface(new SurfaceArea(sHeight_, sWidth_, gHeight_, gWidth_));
        gridJPanel1.paintComponent(gridJPanel1.getGraphics());
        surfaceGeometry.setText("hxw surface=" + gridJPanel1.getSurface().getHeight() + "x"
                + gridJPanel1.getSurface().getWidth() + " " + "grain=" + gridJPanel1.getSurface().getGrainHeight() + "x"
                + gridJPanel1.getSurface().getGrainWidth());



        gridJPanel1.validate();
        gridJPanel1.invalidate();
        //this.validateTree();
        gridJPanel1.repaint();

    }//GEN-LAST:event_submitCrack1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ProbabilisticGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Geometry;
    private javax.swing.JPanel SurfaceArea;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JTextField crackDepth;
    private javax.swing.JTextField crackLength;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JLabel dethjLabel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTextField gHeight;
    private javax.swing.JTextField gWidth;
    private probabilistic.gui.GridJPanel gridJPanel1;
    private javax.swing.JLabel height;
    private javax.swing.JLabel heightjLabel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JButton repaint;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton submitCrack;
    private javax.swing.JButton submitCrack1;
    private javax.swing.JLabel surfaceGeometry;
    private javax.swing.JTextField surfaceHeight;
    private javax.swing.JTextField surfaceWidth;
    private javax.swing.JLabel width;
    // End of variables declaration//GEN-END:variables
}
