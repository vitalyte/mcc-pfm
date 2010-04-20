/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;
//import sun.util.

import java.util.ArrayList;
import java.util.Collections;
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

    InitiationTime(int Nmax, double mean, double scale) {
        initTime = new ArrayList();
        this.Nmax = Nmax;
        this.mean = mean;
        this.scale = scale;
        Integer seed = -1;
        Random rnd = new Random();
        int s = -rnd.nextInt(1000000) + 1;
        seed = new Integer(s);
        for (int i = 0; i < Nmax; i++) {
            double p = RNG.Ran2(seed);//ExponentialDistribution.PPF(i, seed, s)
            double time = ExponentialDistribution.PPF(p, mean, scale);
            initTime.add(new Double(time));
        }
        SortInitTime(this.initTime);
    }

    InitiationTime() {
        this(100, 0.3, 34.54);
    }

    public static void SortInitTime(ArrayList tmp) {
        Collections.sort(tmp);
    }

    public void PrintInitTime() {
        for (int i = 0; i < Nmax; i++) {
            System.out.println(initTime.get(i));
        }
    }

    public static void main(String[] args) {
        InitiationTime inittimeObj = new InitiationTime();
        SortInitTime(inittimeObj.initTime);
        inittimeObj.PrintInitTime();
    }
}
