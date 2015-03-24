/*
 * HungryTest.java
 * JUnit based test
 *
 * Created on May 23, 2008, 10:26 AM
 */

package adhoc.nodes.overheadstates;

import junit.framework.*;

/**
 *
 * @author paul
 */
public class HungryTest extends TestCase {
    
    private Hungry h;
    
    public HungryTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        h = new Hungry();
    }
    
    public void testGetStateNameH(){
        String expResult = "Hungry";
        String result = h.getStateName();
        Assert.assertEquals(expResult, result);
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(HungryTest.class);
        
        return suite;
    }
    
}
