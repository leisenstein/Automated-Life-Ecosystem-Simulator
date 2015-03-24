/*
 * NodeTest.java
 * JUnit based test
 *
 * Created on April 12, 2008, 4:42 PM
 */

package adhoc.nodes;

import adhoc.structure.Ether;
import com.sun.org.apache.xpath.internal.axes.NodeSequence;
import junit.framework.*;
import java.util.*;
import structure.*;

/**
 *
 * @author paul
 */
public class NodeTest extends TestCase {
    
    private Node defaultNode;
    private Integer defaultCapacity;
    private Ether defaultEther;
    private Packet defaultPacket;
    private Packet altPacket;
    
    public NodeTest(String testName) {
        super(testName);
    }
    
    
    public void testCapacity(){
        Assert.assertTrue(defaultNode.getCapacity().intValue() == defaultCapacity.intValue());
    }
    
    public void testClist(){
        boolean b = defaultNode.getUsed(31);
        boolean c = defaultNode.getUsed(0);
        Assert.assertTrue(b == false);
        Assert.assertFalse(b == true);
        Assert.assertFalse(c == true);
    }
    
    public void testGlist(){
        boolean b = defaultNode.getLegal(31);
        boolean c = defaultNode.getLegal(0);
        Assert.assertTrue(b == true);
        Assert.assertFalse(c == false);
    }
    
    public void testStateNames(){
        adhoc.nodes.communicationstates.Idle myIdle = new adhoc.nodes.communicationstates.Idle(defaultEther, defaultNode);
        adhoc.nodes.overheadstates.Hungry myHun = new adhoc.nodes.overheadstates.Hungry();
        adhoc.nodes.Transceiver idleTrans = defaultNode.getTransceiver(4);
        adhoc.nodes.Transceiver hungryTrans = defaultNode.getTransceiver(0);
        String hungryString = new String(hungryTrans.getStateName());
        String idleString = new String(idleTrans.getStateName());
        Assert.assertTrue(hungryString.matches(myHun.getStateName()));
        Assert.assertTrue(idleString.matches(myIdle.getStateName()));
    }
    
  /*  public void testAlterStateName(){
        defaultNode.getTransceiver(3).setStateName("Oberon");
        Assert.assertTrue(defaultNode.getTransceiver(3).getStateName().matches("Oberon"));
    }*/
    
    public void testBecomeInvitee(){
        defaultNode.recieveInvitation();
        Assert.assertTrue(defaultNode.getTransceiver(1).getStateName().matches("Invitee"));
    }
    
    public void testAddPacket(){
        Assert.assertTrue(defaultNode.addPacket(defaultPacket));
    }
    
    public void testGetPacket(){
        defaultNode.addPacket(defaultPacket);
        Packet myPacket = defaultNode.getPacket();
        Assert.assertTrue(myPacket.getData().matches("defaultPacket"));
    }
    
    public void testReceiveInvitation(){
        Assert.assertTrue(defaultNode.recieveInvitation());
    }
    
    public void testPushPacket(){
        defaultNode.pushPacket(3,altPacket);
        Transceiver myTrans = (Transceiver)defaultNode.getTransceiver(3);
        Packet myPac = myTrans.getPacket();
        Assert.assertEquals(myPac.getData(), altPacket.getData());      
    }
    
    protected void setUp() throws Exception {
        defaultEther = new Ether();
        defaultNode = new Node(defaultEther, 32, 5);
        defaultCapacity = new Integer(5);
        defaultPacket = new Packet('a',"defaultPacket");
        altPacket = new Packet('g',"altPacket");
    }
    
    protected void tearDown() throws Exception {
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite(NodeTest.class);
        return suite;
    }
}
