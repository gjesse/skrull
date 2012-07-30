package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import skrull.rmi.client.ClientListenerTest;
import skrull.rmi.util.SkrullClientSterterIntegrationTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	ClientListenerTest.class,
	SkrullClientSterterIntegrationTest.class
	})
public class AllClientTests {

}
