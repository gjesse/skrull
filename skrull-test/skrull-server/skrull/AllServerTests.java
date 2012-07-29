package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import skrull.game.controller.*;
import skrull.game.factory.GameFactoryTest;
import skrull.game.model.AbstractGameModelTest;
import skrull.rmi.server.SkrullServerEndToEndTest;

@RunWith(Suite.class)
@SuiteClasses(
			{ 
				ServerControllerTest.class,
				GameFactoryTest.class,
				SkrullServerEndToEndTest.class,
				ActivityMonitorTest.class,
				AbstractGameControllerTest.class,
				AbstractGameModelTest.class
			})
public class AllServerTests {

}
