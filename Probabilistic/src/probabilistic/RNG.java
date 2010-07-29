/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilistic;

/**
 *
 * @author Alximik
 */
public class RNG {

    static int idum = 0;
    static int idum2 = 123456789, iy = 0;
    static int[] iv = new int[32];

    static void initRNG() {
        //for (int i = 0; i < NTAB;i++)
        //    iv [0] = 0;
    }

    /*Long period (>2*10^18 random generator of L'Ecuyer
    'with Bays-Durham shuffle and added safeguards.
    'Returns a uniform random deviate between 0 and 1.0
    '(exclusive of the endpolong values). Call with idum a negative
    'number to initialize; thereafter, do not alter idum between
    'successive deviates in a sequence. RNMX should approximate
    'the largest floating polong value that is less than 1 */
    /**
     * Long period (>2*10^18 random generator of L'Ecuyer with Bays-Durham
     * shuffle and added safeguards.
     *
     * @param idum  a negative number to initialize; thereafter, do not alter
     * idum between successive deviates in a sequence.
     * 
     * @return  a uniform random deviate between 0 and 1.0
     * (exclusive of the endpolong values).
     */
    public static double Ran2(int idum) {
        final int IM1 = 2147483563;
        final int IM2 = 2147483399;
        final int IA1 = 40014, IA2 = 40692, IQ1 = 53668, IQ2 = 52774;
        final int IR1 = 12211, IR2 = 3791, NTAB = 32, IMM1 = IM1 - 1;
        final int NDIV = 1 + IMM1 / NTAB;
        final double EPS = 3.0e-16;
        final double RNMX = 1.0 - EPS;
        final double AM = 1. / IM1;
        int j = 0, k = 0;
        double temp;
        if (RNG.idum != 0) {
            idum = RNG.idum;
        } else {
            RNG.idum = idum;
        }
        if (idum <= 0) {
            idum = (idum == 0 ? 1 : -idum);
            idum2 = idum;
            for (j = NTAB + 7; j >= 0; j--) {
                k = idum / IQ1;
                idum = IA1 * (idum - k * IQ1) - k * IR1;
                if (idum < 0) {
                    idum += IM1;
                }
                if (j < NTAB) {
                    iv[j] = idum;
                }
            }
            iy = iv[0];
        }
        k = idum / IQ1;
        idum = IA1 * (idum - k * IQ1) - k * IR1;
        if (idum < 0) {
            idum += IM1;
        }
        k = idum2 / IQ2;
        idum2 = IA2 * (idum2 - k * IQ2) - k * IR2;
        if (idum2 < 0) {
            idum2 += IM2;
        }
        j = iy / NDIV;
        iy = iv[j] - idum2;
        iv[j] = idum;
        RNG.idum = idum;
        if (iy < 1) {
            iy += IMM1;
        }
        if ((temp = AM * iy) > RNMX) {
            return RNMX;
        } else {
            return temp;
        }
    }
}
