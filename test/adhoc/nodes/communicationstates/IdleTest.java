/*
 * IdleTest.java
 * JUnit based test
 *
 * Created on May 23, 2008, 10:22 AM
 */

package adhoc.nodes.communicationstates;

import adhoc.structure.Ether;
import junit.framework.*;
import adhoc.nodes.*;
import structure.*;
import java.util.*;

/**
 *
 * @author paul
 */
public class IdleTest extends TestCase {
    
    private Idle i;
    private Idle ii;
    private Ether e;
    private Node n;
    
    public IdleTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        e = new Ether();
        n = new Node();
        i = new Idle();
        ii = new Idle(e,n);
    }

    protected void tearDown() throws Exception {
    }
    
    public void testGetStateNameI(){
        String expResult = "Idle";
        String result = i.getStateName();
        Assert.assertEquals(expResult, result);
    }
    
    public void testGetStateNameII(){
        String expResult = "Idle";
        String result = ii.getStateName();
        Assert.assertEquals(expResult,result);
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(IdleTest.class);
        
        return suite;
    }
    
}
