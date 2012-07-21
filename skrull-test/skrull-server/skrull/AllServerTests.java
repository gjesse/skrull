package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import skrull.game.controller.ActionWorkerTest;
import skrull.game.controller.ServerControllerTest;

@RunWith(Suite.class)
@SuiteClasses(
			{ 
				ActionWorkerTest.class, 
				ServerControllerTest.class 
			})
public class AllServerTests {

}
