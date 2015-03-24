/*
 * InviteeTest.java
 * JUnit based test
 *
 * Created on May 23, 2008, 10:22 AM
 */

package adhoc.nodes.communicationstates;

import junit.framework.*;

/**
 *
 * @author paul
 */
public class InviteeTest extends TestCase {
    
    private Invitee v;
    
    public InviteeTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        v = new Invitee();
    }

    protected void tearDown() throws Exception {
    }
    
    public void testGetStateNameV(){
        String eR = "Invitee";
        String r = v.getStateName();
        Assert.assertEquals(eR, r);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(InviteeTest.class);
        
        return suite;
    }
    
}
