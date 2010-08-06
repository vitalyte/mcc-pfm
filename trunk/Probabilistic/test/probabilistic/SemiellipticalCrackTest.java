/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package probabilistic;

import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vitaly
 */
public class SemiellipticalCrackTest {

private Simulation simulObj;
SemiellipticalCrack instance;
SemiellipticalCrack crack2;
    public SemiellipticalCrackTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        simulObj = new Simulation(20e-3, 20e-3, 0.08e-3, 0.08e-3, 2.97e6, 0.0258e6, 200e6, 162e6, 0.2, 10, 2000);
        Point[] lrPoint = {new Point(1e-3,1e-3), new Point(3e-3,1e-3)};
        instance = new SemiellipticalCrack(lrPoint, 0.5e-3, 0);
        Point[] lrPointCrack2 = {new Point(4e-3,1e-3), new Point(6e-3,1e-3)};
        crack2 = new SemiellipticalCrack(lrPointCrack2, 0.5e-3, 0);


    }

    @After
    public void tearDown() {
    }

    /**
     * Test of SIF_A method, of class SemiellipticalCrack.
     */
    @Test
    public void testSIF_A() {
        System.out.println("SIF_A");      
        double expResult = 0.0;
        double result = instance.SIF_A();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of SIF_B method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testSIF_B() {
        System.out.println("SIF_B");
        double expResult = 0.0;
        double result = instance.SIF_B();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of CriticalRadius method, of class SemiellipticalCrack.
     */
    @Test
    public void testCriticalRadius() {
        System.out.println("CriticalRadius");
        double expResult = 0.0;
        double result = instance.CriticalRadius(crack2);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of integrate method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testIntegrate() throws Exception {
        System.out.println("integrate");
        int tIndx = 0;
        double currentTime = 0.0;
        double deltaT = 0.0;
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        boolean expResult = false;
        boolean result = instance.integrate(tIndx, currentTime, deltaT);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArrayPololine method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testGetArrayPololine() {
        System.out.println("getArrayPololine");
        int visualKValue = 0;
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        int[][] expResult = null;
        int[][] result = instance.getArrayPololine(visualKValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAspectRatio method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testGetAspectRatio() {
        System.out.println("getAspectRatio");
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        double expResult = 0.0;
        double result = instance.getAspectRatio();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAspectRatio method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testSetAspectRatio() {
        System.out.println("setAspectRatio");
        double aspectRatio = 0.0;
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        instance.setAspectRatio(aspectRatio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepthB method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testGetDepthB() {
        System.out.println("getDepthB");
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        double expResult = 0.0;
        double result = instance.getDepthB();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDepthB method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testSetDepthB() {
        System.out.println("setDepthB");
        double crackDepthB = 0.0;
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        instance.setDepthB(crackDepthB);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLength2a method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testGetLength2a() {
        System.out.println("getLength2a");
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        double expResult = 0.0;
        double result = instance.getLength2a();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLeftTip method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testGetLeftTip() {
        System.out.println("getLeftTip");
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        Point expResult = null;
        Point result = instance.getLeftTip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRightTip method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testGetRightTip() {
        System.out.println("getRightTip");
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        Point expResult = null;
        Point result = instance.getRightTip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCrackTip method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testGetCrackTip() {
        System.out.println("getCrackTip");
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        ArrayList expResult = null;
        ArrayList result = instance.getCrackTip();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMaxCondition method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testCheckMaxCondition() {
        System.out.println("checkMaxCondition");
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        boolean expResult = false;
        boolean result = instance.checkMaxCondition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeExternal method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testWriteExternal() throws Exception {
        System.out.println("writeExternal");
        ObjectOutput oo = null;
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        instance.writeExternal(oo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readExternal method, of class SemiellipticalCrack.
     */
    @Ignore
    public void testReadExternal() throws Exception {
        System.out.println("readExternal");
        ObjectInput oi = null;
//        SemiellipticalCrack instance = new SemiellipticalCrack();
        instance.readExternal(oi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}