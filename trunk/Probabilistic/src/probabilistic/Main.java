/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package probabilistic;

import java.util.Random;

/**
 *
 * @author Alximik
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //for (int i=)
    System.out.println(NormalDistribution.CDF(0.5, 0, 1));
    System.out.println(NormalDistribution.PDF(0.5, 0, 1));
    System.out.println(NormalDistribution.PPF(0.999, 0, 1));
    // testing RNG
    Random rnd = new Random();
    int s = -rnd.nextInt(1000000)+1;
    Integer seed = new Integer (s);
    for (int i=0;i<10;i++)
        System.out.println(RNG.Ran2(seed));
    }
}
