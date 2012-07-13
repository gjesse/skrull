package skrull.client;

import junit.framework.TestCase;

import org.junit.Test;

public class HiWorldTest extends TestCase{

	@Test
	public void testGetMessage() {
		HiWorld h = new HiWorld();
		assertNotNull(h.getMessage());
	}

}
