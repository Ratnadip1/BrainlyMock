package framework.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * A singleton class for reading values from Calculator.properties file
 * @author Ratnadip
 */
public class PropertiesFileReader {
	
	public static PropertiesFileReader propFileReader = null;
	private static final String propFileLocation = "src/test/resources/data/Calculator.properties";
	private Properties properties = null;
	
	private PropertiesFileReader() throws IOException {
		loadPropertiesData();
	}

	private void loadPropertiesData() throws IOException {
		// TODO Auto-generated method stub
		FileReader reader = new FileReader(propFileLocation);
		BufferedReader bufferedReader = new BufferedReader(reader);
		properties = new Properties();
		properties.load(bufferedReader);
	}

	public static PropertiesFileReader getInstance() throws IOException {
		// TODO Auto-generated method stub
		if(propFileReader == null) {
			propFileReader = new PropertiesFileReader();
		}
		return propFileReader;
	}
	
	public String getProperty(String propertyKey) {
		return properties.getProperty(propertyKey);
	}	
}
