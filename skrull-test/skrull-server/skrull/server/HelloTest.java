package skrull.server;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.junit.Test;

import skrull.base.rmi.ControllerInterface;
import skrull.server.Controller;

public class HelloTest extends TestCase{

	@Test
	public void testGetMessage() throws RemoteException {
		ControllerInterface h = new Controller("hellomessage");
		assertNotNull(h.say());
	}

}
