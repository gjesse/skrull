package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import skrull.game.controller.ActionWorkerTest;
import skrull.game.controller.ServerControllerTest;
import skrull.game.factory.GameFactoryTest;

@RunWith(Suite.class)
@SuiteClasses(
			{ 
				ActionWorkerTest.class, 
				ServerControllerTest.class,
				GameFactoryTest.class
			})
public class AllServerTests {

}
