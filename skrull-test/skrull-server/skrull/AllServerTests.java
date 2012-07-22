package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import skrull.game.controller.ActionWorkerTest;
import skrull.game.controller.ServerControllerTest;
import skrull.game.factory.GameFactoryTest;
import skrull.rmi.server.SkrullServerEndToEndTest;

@RunWith(Suite.class)
@SuiteClasses(
			{ 
				ActionWorkerTest.class, 
				ServerControllerTest.class,
				GameFactoryTest.class,
				SkrullServerEndToEndTest.class
			})
public class AllServerTests {

}
