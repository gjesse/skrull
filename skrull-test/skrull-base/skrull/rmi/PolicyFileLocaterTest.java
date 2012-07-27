package skrull.rmi;

import static org.junit.Assert.*;

import org.junit.Test;

public class PolicyFileLocaterTest {

	@Test
	public void testGetLocationOfPolicyFile() {
		 System.setProperty("policy.file", "allow_all.policy");
		assertNotNull(PolicyFileLocater.getLocationOfPolicyFile());
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetLocationOfMissingPolicyFile() {
		 System.setProperty("policy.file", "nonexistent.policy");
		 assertNotNull(PolicyFileLocater.getLocationOfPolicyFile());
	}


}
