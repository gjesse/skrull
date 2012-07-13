package skrull.server;

import junit.framework.TestCase;

import org.junit.Test;

import skrull.client.HiWorld;

public class HiWorldTest extends TestCase{

	@Test
	public void testGetMessage() {
		HiWorld h = new HiWorld();
		assertNotNull(h.getMessage());
	}

}
