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
import probabilistic.InitiationTime;

/**
 *
 * @author Vitaly
 */
public class ProbabilisticGUI extends javax.swing.JFrame {

    SurfaceArea surfaceObj;

    /** Creates new form ProbabilisticGUI */
    public ProbabilisticGUI() {
        initComponents();
        gridJPanel1.validate();

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
        jLabel3 = new javax.swing.JLabel();
        gHeight = new javax.swing.JTextField();
        gWidth = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Time = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        timeMean = new javax.swing.JTextField();
        timeScale = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Crack = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        crackDepthMean = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dethjLabel = new javax.swing.JLabel();
        crackLengthSkale = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lengthjLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        crackLengthMean = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        crackDepthSkale = new javax.swing.JTextField();
        gridJPanel1 = new probabilistic.gui.GridJPanel();
        surfaceGeometry = new javax.swing.JLabel();
        submitCrack1 = new javax.swing.JButton();
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

        surfaceHeight.setText("300");
        surfaceHeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surfaceHeightActionPerformed(evt);
            }
        });

        height.setText("Height:");

        width.setText("Width:");

        surfaceWidth.setText("300");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel1.setText("Surface");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel3.setText("Grain");

        gHeight.setText("100");
        gHeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gHeightActionPerformed(evt);
            }
        });

        gWidth.setText("100");
        gWidth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gWidthActionPerformed(evt);
            }
        });

        jLabel4.setText("height");

        jLabel5.setText("width");

        javax.swing.GroupLayout SurfaceAreaLayout = new javax.swing.GroupLayout(SurfaceArea);
        SurfaceArea.setLayout(SurfaceAreaLayout);
        SurfaceAreaLayout.setHorizontalGroup(
            SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SurfaceAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addGroup(SurfaceAreaLayout.createSequentialGroup()
                        .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(width)
                            .addComponent(height)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gWidth)
                            .addComponent(gHeight)
                            .addComponent(surfaceWidth)
                            .addComponent(surfaceHeight, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        SurfaceAreaLayout.setVerticalGroup(
            SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SurfaceAreaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SurfaceAreaLayout.createSequentialGroup()
                        .addComponent(surfaceHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(surfaceWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(gHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SurfaceAreaLayout.createSequentialGroup()
                        .addComponent(height)
                        .addGap(12, 12, 12)
                        .addComponent(width)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        Geometry.addTab("Geometry", SurfaceArea);

        jLabel6.setText("mean:");

        jLabel7.setText("scale");

        timeMean.setText("0.3");
        timeMean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeMeanActionPerformed(evt);
            }
        });

        timeScale.setText("34.54");
        timeScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeScaleActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Time:");

        javax.swing.GroupLayout TimeLayout = new javax.swing.GroupLayout(Time);
        Time.setLayout(TimeLayout);
        TimeLayout.setHorizontalGroup(
            TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TimeLayout.createSequentialGroup()
                        .addGroup(TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(timeScale)
                            .addComponent(timeMean, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel12))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        TimeLayout.setVerticalGroup(
            TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TimeLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(timeMean))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(timeScale))
                .addGap(155, 155, 155))
        );

        Geometry.addTab("Time", Time);

        jLabel11.setText("Scale:");

        crackDepthMean.setText("10");
        crackDepthMean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crackDepthMeanActionPerformed(evt);
            }
        });

        jLabel8.setText("Mean:");

        dethjLabel.setText("Depth:");

        crackLengthSkale.setText("5");
        crackLengthSkale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crackLengthSkaleActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabel2.setText("Crack");

        lengthjLabel.setText("Length:");

        jLabel9.setText("Scale:");

        crackLengthMean.setText("10");
        crackLengthMean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crackLengthMeanActionPerformed(evt);
            }
        });

        jLabel10.setText("Mean:");

        crackDepthSkale.setText("5");
        crackDepthSkale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crackDepthSkaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CrackLayout = new javax.swing.GroupLayout(Crack);
        Crack.setLayout(CrackLayout);
        CrackLayout.setHorizontalGroup(
            CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(CrackLayout.createSequentialGroup()
                        .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(crackDepthSkale)
                            .addComponent(crackDepthMean)
                            .addComponent(crackLengthSkale)
                            .addComponent(crackLengthMean, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                    .addComponent(jLabel2)
                    .addGroup(CrackLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dethjLabel)
                            .addComponent(lengthjLabel))))
                .addGap(244, 244, 244))
        );
        CrackLayout.setVerticalGroup(
            CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrackLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lengthjLabel)
                .addGap(8, 8, 8)
                .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crackLengthMean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(crackLengthSkale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(dethjLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(crackDepthMean, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(crackDepthSkale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        Geometry.addTab("Crack", Crack);

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

        submitCrack1.setText("submit");
        submitCrack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitCrack1ActionPerformed(evt);
            }
        });

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(gridJPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surfaceGeometry, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(261, 261, 261)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Geometry, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitCrack1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Geometry, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(submitCrack1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(surfaceGeometry)
                        .addGap(2, 2, 2)
                        .addComponent(gridJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void crackLengthMeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crackLengthMeanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crackLengthMeanActionPerformed

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
        int HeightValue = (Integer.parseInt(surfaceHeight.getText()));
        int WidthValue = (Integer.parseInt(surfaceWidth.getText()));
        int gWidthValue = (Integer.parseInt(gWidth.getText()));
        int gHeightValue = (Integer.parseInt(gHeight.getText()));
        double timeMeanValue = Double.parseDouble(timeMean.getText());
        double timeScaleValue =  Double.parseDouble(timeScale.getText());
        double lengthMean = Double.parseDouble(crackLengthMean.getText());
        double lengthScale = Double.parseDouble(crackLengthSkale.getText());
        double depthMean = Double.parseDouble(crackDepthMean.getText());
        double depthScale = Double.parseDouble(crackDepthSkale.getText());
        surfaceObj = new SurfaceArea(HeightValue, WidthValue, gHeightValue, gWidthValue, timeMeanValue, timeScaleValue);
        surfaceObj.FillRandomCracks(HeightValue, WidthValue, gHeightValue, gWidthValue,
                lengthMean, lengthScale,depthMean, depthScale);
        gridJPanel1.setSurface(surfaceObj);
        gridJPanel1.paintComponent(gridJPanel1.getGraphics());
        surfaceGeometry.setText("hxw surface=" + gridJPanel1.getSurface().getHeight() + "x"
                + gridJPanel1.getSurface().getWidth() + " " + "grain=" + gridJPanel1.getSurface().getGrainHeight() + "x"
                + gridJPanel1.getSurface().getGrainWidth());



        gridJPanel1.validate();
        gridJPanel1.invalidate();
        //this.validateTree();
        gridJPanel1.repaint();

    }//GEN-LAST:event_submitCrack1ActionPerformed

    private void timeMeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeMeanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeMeanActionPerformed

    private void timeScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeScaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeScaleActionPerformed

    private void crackLengthSkaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crackLengthSkaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crackLengthSkaleActionPerformed

    private void crackDepthMeanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crackDepthMeanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crackDepthMeanActionPerformed

    private void crackDepthSkaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crackDepthSkaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crackDepthSkaleActionPerformed

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
    private javax.swing.JPanel Crack;
    private javax.swing.JTabbedPane Geometry;
    private javax.swing.JPanel SurfaceArea;
    private javax.swing.JPanel Time;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JTextField crackDepthMean;
    private javax.swing.JTextField crackDepthSkale;
    private javax.swing.JTextField crackLengthMean;
    private javax.swing.JTextField crackLengthSkale;
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
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lengthjLabel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton submitCrack1;
    private javax.swing.JLabel surfaceGeometry;
    private javax.swing.JTextField surfaceHeight;
    private javax.swing.JTextField surfaceWidth;
    private javax.swing.JTextField timeMean;
    private javax.swing.JTextField timeScale;
    private javax.swing.JLabel width;
    // End of variables declaration//GEN-END:variables
}