/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rafael
 */
public class POHTest {
    
    public POHTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of deleteTempItem method, of class POH.
     */
    @Test
    public void testDeleteTempItem() {
        System.out.println("deleteTempItem");
        int id = 0;
        POH instance = null;
        instance.deleteTempItem(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endPOS method, of class POH.
     */
    @Test
    public void testEndPOS() {
        System.out.println("endPOS");
        String textFile = "";
        POH instance = null;
        instance.endPOS(textFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveTemp method, of class POH.
     */
    @Test
    public void testRetrieveTemp() {
        System.out.println("retrieveTemp");
        String textFile = "";
        POH instance = null;
        instance.retrieveTemp(textFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
