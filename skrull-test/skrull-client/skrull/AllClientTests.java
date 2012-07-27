package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import skrull.rmi.client.ClientListenerTest;

@RunWith(Suite.class)
@SuiteClasses({ ClientListenerTest.class })
public class AllClientTests {

}
