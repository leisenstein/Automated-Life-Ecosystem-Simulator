/*
 * CoverageTest.java
 * JUnit based test
 *
 * Created on June 28, 2008, 10:59 PM
 */

package adhoc.structure;

import junit.framework.*;

/**
 *
 * @author paul
 */
public class CoverageTest extends TestCase {
    
    Coverage testWorld;
    
    public CoverageTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        testWorld = new Coverage();
    }

    protected void tearDown() throws Exception {
    }
    
    public void testBoard() {
        int expect = 100000;
        int result = (int)testWorld.getBoard().getMaxX();
        Assert.assertEquals(expect,result);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CoverageTest.class);
        
        return suite;
    }
    
}
