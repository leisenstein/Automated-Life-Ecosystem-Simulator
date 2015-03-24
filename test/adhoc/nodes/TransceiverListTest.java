/*
 * TransceiverListTest.java
 * JUnit based test
 *
 * Created on May 21, 2008, 6:51 AM
 */

package adhoc.nodes;

import adhoc.structure.Ether;
import junit.framework.*;
import structure.*;

/**
 *
 * @author paul
 */
public class TransceiverListTest extends TestCase {
    public Ether e;
    public Integer c;
    public Node n;
    private TransceiverList tL;
    private Transceiver t;
    public TransceiverListTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        e = new Ether();
        c = new Integer(5);
        n = new Node(e,10,c.intValue());
        tL = new TransceiverList(c,e);
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getTransceiver method, of class adhoc.nodes.TransceiverList.
     */
    public void testGetTransceiver() {
        System.out.println("getTransceiver");
        
        
        t = tL.getTransceiver(0);
        String expResult = "Hungry";

        String result = t.getStateName();
        Assert.assertEquals(expResult, result);
        
        t = tL.getTransceiver(4);
        expResult = new String("Idle");
        result = t.getStateName();
        Assert.assertEquals(expResult,result);
       
    }

    /**
     * Test of becomeInvitee method, of class adhoc.nodes.TransceiverList.
     */
    public void testBecomeInvitee() {
        System.out.println("becomeInvitee");
        
        int which = 3;
        
        tL.becomeInvitee(which);
        String eR = "Invitee";
        t = tL.getTransceiver(which);
        String r = t.getStateName();
        Assert.assertEquals(eR,r);

        boolean b = tL.becomeInvitee(5);
        Assert.assertFalse(b);
    }

    /**
     * Test of firstIdle method, of class adhoc.nodes.TransceiverList.
     */
    public void testFirstIdle() {
        System.out.println("firstIdle");
        
        int expResult = 1;
        int result = tL.firstIdle();
        assertEquals(expResult, result);
        }
    
    /**
     * Test of some general functions, just to make sure that the member variables are pointers
     */
    
    public void testDesign(){
        System.out.println("testDesign");
        TransceiverList tl2 = n.getTList();
        tl2.setNode(n);
        Assert.assertSame(n,tl2.nod);
        Assert.assertSame(tl2, n.getTList());
        int l = tl2.firstIdle();
        System.out.println(l);
        n.recieveInvitation();
        int m = tl2.firstIdle();
        System.out.println(m);
        Assert.assertTrue(l+1 == m);
    }
}
