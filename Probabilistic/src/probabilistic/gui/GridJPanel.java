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
    public GridJPanel() {
        initComponents();
        
    }
    

    @Override
     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize(400,400);
        int width = this.getWidth();
        int height = this.getHeight();
        int stepW = width/5;
        int stepH = height/5;
        for (int i=0;i<=width;i+=stepW)
            g.drawLine(i, 0, i, height);
        for (int j=0;j<=width;j+=stepH)
            g.drawLine(0, j, width, j);
        FillRandomPoints(g, width, height, stepW, stepH);
    }
    boolean isSquareEmpty(boolean [][] matrix, int i,int j)
    {
       if (matrix[i][j] == false )
           return true;
       return false;
    }
    void FillRandomPoints (Graphics g, int w,int h, int stepW, int stepH)
    {
       //UniformDistribution uniform = new UniformDistribution();
         Random rnd = new Random();
        int s = -rnd.nextInt(1000000) + 1;
        Integer seed = new Integer(s);
        int Nmax = w*h/(stepW*stepH);
        int m = h/stepH;
        int n = w/stepW;
        boolean [][] matrix = new boolean [m][n];
        for (int i=0; i < m; i++)
            for(int j=0; j < n; j++)
                matrix[i][j] = false;
        int i=0;
        while (i<Nmax)
        {
            double rndX = UniformDistribution.PPF(RNG.Ran2(seed), 0, w);
            double rndY = UniformDistribution.PPF(RNG.Ran2(seed), 0, h);
            int rndI = (int)rndX/stepW;
            int rndJ = (int)rndY/stepH;
            if (isSquareEmpty(matrix,rndI,rndJ)){
                // точку кинули в порожню клітину
                matrix[rndI][rndJ] = true;
                g.drawLine((int)rndX, (int)rndY, (int)rndX+1, (int)rndY );
                i++;
            }
                
        }
       // for (int i=0;i<Nmax;i++)
       // {
       //     double rndX = UniformDistribution.PPF(RNG.Ran2(seed), 0, w);
       //     double rndY = UniformDistribution.PPF(RNG.Ran2(seed), 0, h);
       //     g.drawLine((int)rndX, (int)rndY, (int)rndX+1, (int)rndY );
       // }
        //for (int i = 0; i < 10; i++) {
        //    System.out.println(RNG.Ran2(seed));
        //}

      // for (int i=0;i<w;i++)
     //  UniformDistribution.PPF(, WIDTH, WIDTH);
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
