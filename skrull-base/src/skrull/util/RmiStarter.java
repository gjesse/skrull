package skrull.util;

import skrull.rmi.PolicyFileLocater;
import skrull.rmi.SystemPropertyReader;

/**
 * handles bootstrapping and starting of client/server apps
 *
 * @author jhodges, with thanks to http://code.nomad-labs.com/2010/03/26/an-improved-rmi-tutorial-with-eclipse/
 *
 */
public abstract class RmiStarter {

    /**
     *
     * @param clazzToAddToServerCodebase a class that should be in the java.rmi.server.codebase property.
     * @throws Exception 
     */
    public RmiStarter() throws Exception {

    	SystemPropertyReader.readProperties();
        System.setProperty("java.security.policy", PolicyFileLocater.getLocationOfPolicyFile());

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        doCustomRmiHandling();
    }

    /**
     * extend this class and do RMI handling here
     */
    public abstract void doCustomRmiHandling();

}