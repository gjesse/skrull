package skrull.util.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



public class SkrullLogger {

	static {
        // setup log4j config
        final String log4jFile = System.getProperty("log4j.config", "log4j.properties");
        PropertyConfigurator.configure(SkrullLogger.class.getResourceAsStream(log4jFile));
	}
	
	/**
	 * returns a configured implementation of 
	 * a log4j logger.
	 * 
	 * Ideally this would return an interface to that logger
	 * instead (maybe using slf4j or other) but for our purposes
	 * now it will do
	 * 
	 * see http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/Level.html for details on 
	 * logging levels
	 * 
	 * @param clazz
	 * @return a logger object
	 */
	public static Logger getLogger(Class<?> clazz){
		return Logger.getLogger(clazz);
	}
	
}
