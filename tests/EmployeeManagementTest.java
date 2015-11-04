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
 * @author jianing
 */
public class EmployeeManagementTest {
    
    public EmployeeManagementTest() {
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
     * Test of add method, of class EmployeeManagement.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        EmployeeManagement instance = new EmployeeManagement();
        instance.add();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class EmployeeManagement.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        EmployeeManagement instance = new EmployeeManagement();
        instance.delete();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class EmployeeManagement.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        EmployeeManagement instance = new EmployeeManagement();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
