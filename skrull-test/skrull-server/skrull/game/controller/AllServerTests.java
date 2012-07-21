package skrull.game.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
			{ 
				ActionWorkerTest.class, 
				ServerControllerTest.class 
			})
public class AllServerTests {

}
