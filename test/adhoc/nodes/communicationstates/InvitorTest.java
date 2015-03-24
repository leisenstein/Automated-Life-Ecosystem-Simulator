/*
 * InvitorTest.java
 * JUnit based test
 *
 * Created on May 23, 2008, 5:26 AM
 */

package adhoc.nodes.communicationstates;

import junit.framework.*;

/**
 *
 * @author paul
 */
public class InvitorTest extends TestCase {
    
    Invitor n;
    
    public InvitorTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
        public static Test suite() {
        TestSuite suite = new TestSuite(InvitorTest.class);
        
        return suite;
    }

    public void testGetStateNameN(){
        n = new Invitor();
        String expResult = "Invitr";
        String result = n.getStateName();
        Assert.assertFalse(expResult.matches(result));
        expResult = new String("Invitor");
        assertEquals(expResult,result);
    }
}
