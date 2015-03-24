/*
 * EtherTest.java
 * JUnit based test
 *
 * Created on June 28, 2008, 10:59 PM
 */

package adhoc.structure;

import junit.framework.*;
import java.awt.*;
import java.util.*;
import structure.*;

/**
 *
 * @author paul
 */
public class EtherTest extends TestCase {
    
    public EtherTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(EtherTest.class);
        
        return suite;
    }
    
}
