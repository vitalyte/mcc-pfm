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

        gridJPanel1.setSize(new Dimension (400,400));
        gridJPanel1.setBorder(BorderFactory.createLineBorder (Color.blue, 2));
        gridJPanel1.setBackground(Color.white);
        gridJPanel1.setVisible(true);
        
    }
  

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FormjPanel = new javax.swing.JPanel();
        gridJPanel1 = new probabilistic.gui.GridJPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        SurfaceArea = new javax.swing.JPanel();
        heightjTextField = new javax.swing.JTextField();
        height = new javax.swing.JLabel();
        width = new javax.swing.JLabel();
        widthjTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        heightjLabel = new javax.swing.JLabel();
        dethjLabel = new javax.swing.JLabel();
        crackLength = new javax.swing.JTextField();
        crackDepth = new javax.swing.JTextField();
        submitCrack = new javax.swing.JButton();
        repaint = new javax.swing.JButton();
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

        javax.swing.GroupLayout gridJPanel1Layout = new javax.swing.GroupLayout(gridJPanel1);
        gridJPanel1.setLayout(gridJPanel1Layout);
        gridJPanel1Layout.setHorizontalGroup(
            gridJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        gridJPanel1Layout.setVerticalGroup(
            gridJPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout FormjPanelLayout = new javax.swing.GroupLayout(FormjPanel);
        FormjPanel.setLayout(FormjPanelLayout);
        FormjPanelLayout.setHorizontalGroup(
            FormjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gridJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FormjPanelLayout.setVerticalGroup(
            FormjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FormjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gridJPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        height.setText("Height:");

        width.setText("Width:");

        javax.swing.GroupLayout SurfaceAreaLayout = new javax.swing.GroupLayout(SurfaceArea);
        SurfaceArea.setLayout(SurfaceAreaLayout);
        SurfaceAreaLayout.setHorizontalGroup(
            SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SurfaceAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SurfaceAreaLayout.createSequentialGroup()
                        .addComponent(height)
                        .addGap(25, 25, 25)
                        .addComponent(heightjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SurfaceAreaLayout.createSequentialGroup()
                        .addComponent(width)
                        .addGap(25, 25, 25)
                        .addComponent(widthjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        SurfaceAreaLayout.setVerticalGroup(
            SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SurfaceAreaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(height)
                    .addComponent(heightjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SurfaceAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(width)
                    .addComponent(widthjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SurfaceArea", SurfaceArea);

        heightjLabel.setText("Length:");

        dethjLabel.setText("Depth:");

        crackLength.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crackLengthActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(heightjLabel)
                    .addComponent(dethjLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(submitCrack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(crackDepth, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(crackLength, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addGap(91, 91, 91))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heightjLabel)
                    .addComponent(crackLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dethjLabel)
                    .addComponent(crackDepth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitCrack)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Crack", jPanel1);

        repaint.setText("REPAINT");
        repaint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repaintActionPerformed(evt);
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
                .addComponent(FormjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(repaint))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(FormjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(repaint)
                .addContainerGap())
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
        gridJPanel1.paintComponent(gridJPanel1.getGraphics());
//        gridJPanel1 = new GridJPanel();
    }//GEN-LAST:event_repaintActionPerformed

    private void submitCrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitCrackActionPerformed
        // TODO add your handling code here:
        gridJPanel1.getCrack().setLength2a(Integer.parseInt( crackLength.getText()));
    }//GEN-LAST:event_submitCrackActionPerformed

    private void crackLengthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crackLengthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_crackLengthActionPerformed

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
    private javax.swing.JPanel FormjPanel;
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
    private probabilistic.gui.GridJPanel gridJPanel1;
    private javax.swing.JLabel height;
    private javax.swing.JLabel heightjLabel;
    private javax.swing.JTextField heightjTextField;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JButton repaint;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JButton submitCrack;
    private javax.swing.JLabel width;
    private javax.swing.JTextField widthjTextField;
    // End of variables declaration//GEN-END:variables

}
