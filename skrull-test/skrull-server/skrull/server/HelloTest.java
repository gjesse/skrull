package skrull.server;

import java.rmi.RemoteException;

import junit.framework.TestCase;

import org.junit.Test;

import skrull.base.rmi.HelloInterface;
import skrull.server.Hello;

public class HelloTest extends TestCase{

	@Test
	public void testGetMessage() throws RemoteException {
		HelloInterface h = new Hello("hellomessage");
		assertNotNull(h.say());
	}

}
