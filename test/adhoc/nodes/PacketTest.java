/*
 * PacketTest.java
 * JUnit based test
 *
 * Created on May 23, 2008, 5:13 AM
 */

package adhoc.nodes;

import junit.framework.*;

/**
 *
 * @author paul
 */
public class PacketTest extends TestCase {
    
    
    public PacketTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getType method, of class adhoc.nodes.Packet.
     */
    public void testGetType() {
        System.out.println("getType");
        
        Packet instance = new Packet();
        
        char expResult = 'p';
        char result = instance.getType();
        assertEquals(expResult, result);
        
        instance = new Packet('c',"s");
        expResult = 'c';
        result = instance.getType();
        assertEquals(expResult, result);
        
        instance = new Packet('d',"s",1);
        expResult = 'd';
        result = instance.getType();
        assertEquals(expResult, result);
       }

    /**
     * Test of getData method, of class adhoc.nodes.Packet.
     */
    public void testGetData() {
        System.out.println("getData");
        
        Packet instance = new Packet();
        
        String expResult = "";
        String result = instance.getData();
        assertEquals(expResult, result);
        
        instance = new Packet('c',"s");
        
        expResult = "s";
        result = instance.getData();
        assertEquals(expResult, result);
        
        instance = new Packet('c',"str", 1);
        
        expResult = "str";
        result = instance.getData();
        assertEquals(expResult, result);

    }

    /**
     * Test of getColor method, of class adhoc.nodes.Packet.
     */
    public void testGetColor() {
        System.out.println("getColor");
        
        Packet instance = new Packet();
        
        int expResult = 0;
        int result = instance.getColor();
        assertEquals(expResult, result);
        
        instance = new Packet('c',"s");
        expResult = 0;
        result = instance.getColor();
        assertEquals(expResult, result);

        instance = new Packet('c',"s",5);
        expResult = 5;
        result = instance.getColor();
        assertEquals(expResult, result);

    }
    
}
