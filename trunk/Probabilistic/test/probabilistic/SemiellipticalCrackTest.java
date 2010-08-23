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
        Point[] lrPoint = {new Point(3e-3, 1e-3), new Point(3.1e-3, 1e-3)};
        instance = new SemiellipticalCrack(lrPoint, 1e-4, simulObj.getTime().getInitTime().get(1));
        Point[] lrPointCrack2 = {new Point(4e-3, 1e-3), new Point(4.1e-3, 1e-3)};
        crack2 = new SemiellipticalCrack(lrPointCrack2, 0.05e-3, simulObj.getTime().getInitTime().get(1));


    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAspectRatio method, of class SemiellipticalCrack.
     */
    @Test
    public void testGetAspectRatioBeforeGrowth() {

//        SemiellipticalCrack instance = new SemiellipticalCrack();
        double expResult = 2.0;
        double result = instance.getAspectRatio();
        
        assertEquals(expResult, result, 1E7);
        // TODO review the generated test code and remove the default call to fail.
System.out.println("AspectRatioBeforeGrowth " + result);
    }

    /**
     * Test of SIF_A method, of class SemiellipticalCrack.
     */
    @Test
    public void testSIF_A() {
//        System.out.println("SIF_A");
        double expResult = 1707690.6446578614;
        double result = instance.SIF_A();
//        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("SIF_A =" + result);
    }

    /**
     * Test of SIF_B method, of class SemiellipticalCrack.
     */
    @Test
    public void testSIF_B() {

        double expResult = 1592210.2800456102;
        double result = instance.SIF_B();
//        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("SIF_B =" + result);
    }

    /**
     * Test of CriticalRadius method, of class SemiellipticalCrack.
     */
    @Test
    public void testCriticalRadius() {
//        System.out.println("CriticalRadius");
        double expResult = 1.4148111961591222E-5;
        double result = instance.CriticalRadius(crack2);
//        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("CriticalRadius = " + result);
    }

    /**
     * Test of integrate method, of class SemiellipticalCrack.
     */
    @Test
    public void testIntegrate() throws Exception {
        double currentTime = 1.0E4;
        double deltaT = 3.6E3;
        double beforeGrowthLength = instance.getLength2a();
        double beforeGrowthDepth = instance.getDepthB();

        boolean result = instance.integrate(currentTime, deltaT);
        // TODO review the generated test code and remove the default call to fail.
        double afterGrowthLength = instance.getLength2a();
        double afterGrowthDepth = instance.getDepthB();
        System.out.println("delta length after 3600 s " + (afterGrowthLength - beforeGrowthLength));
        System.out.println("delta depyh after 3600 s " + (afterGrowthDepth - beforeGrowthDepth));
        assertEquals(1.01317541452007E-4, afterGrowthLength, 1e-6);
    }



    /**
     * Test of getAspectRatio method, of class SemiellipticalCrack.
     */
    @Test
    public void testGetAspectRatioAfterGrowth() {

//        SemiellipticalCrack instance = new SemiellipticalCrack();
        double expResult = 1.0;
        double result = instance.getAspectRatio();
        assertEquals(expResult, result, 1E7);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println("AspectRatioAfterGrowth " + result);

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
//        fail("The test case is a prototype.");
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
        ArrayList result = instance.getCrackTips();
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
