package skrull;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import skrull.game.controller.*;
import skrull.game.factory.GameFactoryTest;
import skrull.game.model.AbstractGameModelTest;
import skrull.game.model.rockpaperscissors.RockPaperScissorsTest;
import skrull.game.model.tictactoe.TicTacToeTest;
import skrull.rmi.server.ServerListenerTest;
import skrull.rmi.server.SkrullServerEndToEndTest;

@RunWith(Suite.class)
@SuiteClasses(
			{ 
				ServerControllerTest.class,
				GameFactoryTest.class,
				SkrullServerEndToEndTest.class,
				ActivityMonitorTest.class,
				AbstractGameControllerTest.class,
				AbstractGameModelTest.class,
				RockPaperScissorsTest.class,
				TicTacToeTest.class,
				ServerListenerTest.class
			})
public class AllServerTests {

}
