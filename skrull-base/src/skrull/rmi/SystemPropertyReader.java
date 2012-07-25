package skrull.rmi;

import java.io.FileInputStream;
import java.util.Properties;

public class SystemPropertyReader {
    public static void readProperties()
        throws Exception {


        FileInputStream propFile =
            new FileInputStream(System.getProperty("skrull.properties"));
        Properties p =
            new Properties(System.getProperties());
        p.load(propFile);

        // set the system properties
        System.setProperties(p);
    }
}