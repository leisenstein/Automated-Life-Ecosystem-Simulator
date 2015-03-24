/*
 * SatedTest.java
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
public class SatedTest extends TestCase {
    
    public SatedTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(SatedTest.class);
        
        return suite;
    }
    
}
