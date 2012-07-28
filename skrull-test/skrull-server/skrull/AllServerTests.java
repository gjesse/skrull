package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import skrull.game.controller.ActivityMonitorTest;
import skrull.game.controller.ServerControllerTest;
import skrull.game.factory.GameFactoryTest;
import skrull.rmi.server.SkrullServerEndToEndTest;

@RunWith(Suite.class)
@SuiteClasses(
			{ 
				ServerControllerTest.class,
				GameFactoryTest.class,
				SkrullServerEndToEndTest.class,
				ActivityMonitorTest.class
			})
public class AllServerTests {

}
