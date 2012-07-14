package skrull.client;

import junit.framework.TestCase;

import org.junit.Test;

public class HiWorldTest extends TestCase{

	@Test
	public void testGetMessage() {
		Hello h = new Hello();
		assertNotNull(h.getMessage());
	}

}
