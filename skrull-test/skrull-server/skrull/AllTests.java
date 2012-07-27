package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllServerTests.class,
	AllBaseTests.class,
	AllClientTests.class})
public class AllTests {

}
