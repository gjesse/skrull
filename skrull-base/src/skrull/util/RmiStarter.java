package skrull.util;

import org.apache.log4j.Logger;

import skrull.rmi.PolicyFileLocater;
import skrull.util.logging.SkrullLogger;

/**
 * handles bootstrapping, reading system properties and starting of client/server apps
 *
 * @author jhodges, with thanks to http://code.nomad-labs.com/2010/03/26/an-improved-rmi-tutorial-with-eclipse/
 *
 */
public abstract class RmiStarter {

	private static final Logger logger = SkrullLogger.getLogger(RmiStarter.class);

    /**
     * handles rmi starting
     */
    public RmiStarter() throws Exception {

    	SystemPropertyReader.readProperties();
        System.setProperty("java.security.policy", PolicyFileLocater.getLocationOfPolicyFile());

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        logger.info("system properties initialized");
        
        doCustomRmiHandling();
    }

    /**
     * extend this class and do RMI handling here and 
     * additional initialization there
     * 
     */
    public abstract void doCustomRmiHandling();

}