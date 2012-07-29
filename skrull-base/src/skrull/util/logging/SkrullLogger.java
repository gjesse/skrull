package skrull.util.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class SkrullLogger {

	static {
        // setup log4j config
        final String log4jFile = System.getProperty("log4j.config", "log4j.properties");
        PropertyConfigurator.configure(SkrullLogger.class.getResourceAsStream(log4jFile));
	}
	
	public static Logger getLogger(Class<?> clazz){
		return Logger.getLogger(clazz);
	}
	
}
