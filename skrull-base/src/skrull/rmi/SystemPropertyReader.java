package skrull.rmi;

import java.io.InputStream;
import java.util.Properties;

public class SystemPropertyReader {
    public static void readProperties()
        throws Exception {


    	final String propertyFile = System.getProperty("skrull.properties");
    	InputStream is = PolicyFileLocater.class.getResourceAsStream(propertyFile);
        
        Properties p =
            new Properties(System.getProperties());
        p.load(is);

        // set the system properties
        System.setProperties(p);
    }
}