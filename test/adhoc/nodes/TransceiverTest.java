/*
 * TransceiverTest.java
 * JUnit based test
 *
 * Created on April 12, 2008, 4:41 PM
 */

package adhoc.nodes;

import junit.framework.*;
import structure.*;

/**
 *
 * @author paul
 */
public class TransceiverTest extends TestCase {
    
    public TransceiverTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(TransceiverTest.class);
        
        return suite;
    }

    /**
     * Generated implementation of abstract class nodes.Transceiver. Please fill dummy bodies of generated methods.
     */
    private class TransceiverImpl extends Transceiver {

        TransceiverImpl() {
            super();
        }
    }

  
    /**
     * Test of setStateName method, of class adhoc.nodes.Transceiver,
     * Test of getStateName method, of class adhoc.nodes.Transceiver.
     */
    public void testGetStateName() {
        System.out.println("getStateName");
        
        Transceiver instance = new TransceiverImpl();
        
        String expResult = "MyMy HeyHey";
        instance.setStateName("MyMy HeyHey");
        
        String result = instance.getStateName();
        assertEquals(expResult, result);
        
        }

    
}
