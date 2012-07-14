package skrull.server;

import junit.framework.TestCase;

import org.junit.Test;

import skrull.client.Hello;

public class HiWorldTest extends TestCase{

	@Test
	public void testGetMessage() {
		Hello h = new Hello();
		assertNotNull(h.getMessage());
	}

}
