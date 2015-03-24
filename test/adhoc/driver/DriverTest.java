/*
 * DriverTest.java
 * JUnit based test
 *
 * Created on May 23, 2008, 10:25 AM
 */

package adhoc.driver;

import junit.framework.*;

/**
 *
 * @author paul
 */
public class DriverTest extends TestCase {
    
    public DriverTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(DriverTest.class);
        
        return suite;
    }
    
}
