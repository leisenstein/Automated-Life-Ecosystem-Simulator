/*
 * CreatureStoreTest.java
 * JUnit based test
 *
 * Created on April 27, 2008, 5:40 PM
 */

package structure;

import ales.structure.CreatureStore;
import junit.framework.*;
import ales.*;
import java.awt.*;
import java.util.*;

/**
 *
 * @author paul
 */
public class CreatureStoreTest extends TestCase {
    private String[] args = {};
    private ALES_Game a = new ALES_Game(args);
    private GameManager g = new GameManager(a);
    
    public CreatureStoreTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getNeighborhood method, of class structure.CreatureStore.
     */
    public void testGetNeighborhood() {
        System.out.println("getNeighborhood");
        
        Point p = null;
        int r = 0;
        CreatureStore instance = null;
        
        CreatureStore expResult = null;
        CreatureStore result = instance.getNeighborhood(p, r);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class structure.CreatureStore.
     */
    public void testGetAll() {
        System.out.println("getAll");
        
        CreatureStore instance = null;
        
        CreatureStore expResult = null;
        CreatureStore result = instance.getAll();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDead method, of class structure.CreatureStore.
     */
    public void testRemoveDead() {
        System.out.println("removeDead");
        
        CreatureStore instance = null;
        
        instance.removeDead();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
