package skrull.util;

import java.io.InputStream;
import java.util.Properties;


/**
 * Helper class to read the system properties.
 * 
 * Relies on an existing "skrull.properties" setting that will usually
 * be passed to the app on the command line
 *
 * Example:
 * 	-Dskrull.properties=skrull.client.propertiess
 * 
 * @author jesse
 *
 */
public class SystemPropertyReader {
	
	/**
	 * Reads the properties and sets them as System propreties
	 * @throws Exception
	 */
    public static void readProperties()
        throws Exception {


    	final String propertyFile = System.getProperty("skrull.properties");
    	InputStream is = SystemPropertyReader.class.getResourceAsStream(propertyFile);
        
        Properties p =
            new Properties(System.getProperties());
        p.load(is);

        // set the system properties
        System.setProperties(p);

    }
}