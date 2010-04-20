/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package probabilistic;
//import sun.util.

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Vitaly
 */
public class InitiationTime {
    public ArrayList initTime;
    double mean;
    double scale;
    int Nmax;
    InitiationTime (){
        Integer seed = -1;
        Random rnd = new Random();
        int s = -rnd.nextInt(1000000) + 1;
        mean = 0.3;
        scale = 34.54;
        seed = new Integer(s);
        for (int i=0;i<Nmax;i++){
            double p = RNG.Ran2(seed);//ExponentialDistribution.PPF(i, seed, s)
            double time = ExponentialDistribution.PPF(p, mean, scale);
            initTime.add(time);
        }
        //sort;
        //initTime.
        //SortInitTime();
    }
    public static ArrayList SortInitTime(ArrayList tmp)
    {
        return null;
    }
    public void PrintInitTime()
    {
        for (int i=0;i<Nmax;i++)
            System.out.println((double)initTime.get(i));
    }

}
