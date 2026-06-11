package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	public String loadProperties(String key) {
		Properties prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			return prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
