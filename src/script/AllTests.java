package script;
import org.junit.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase{


	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(DataProviderExample.class);
		suite.addTestSuite(Google.class);
		suite.addTestSuite(BaseTest.class);
		//$JUnit-END$
		return suite;
	}

}
