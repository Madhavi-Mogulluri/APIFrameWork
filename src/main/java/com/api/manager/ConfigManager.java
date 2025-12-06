package com.api.manager;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
  

	private static Properties properties = new Properties();
	

	static {
        String env =  System.getProperty("env","qa");
        System.out.println("Running test cases on env " + env);

		String fileName = "config_"+ env+".properties";
	InputStream input =	ConfigManager.class.getClassLoader().getResourceAsStream(fileName);
	if(input != null) {
		
		try {
			properties.load(input);
			System.out.println("config properties are" + properties);
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
	
	
	}
	
	
	public static String get(String key) {
		
		return properties.getProperty(key).trim();
	}
	
	public static void set(String key, String value) {
		
		properties.setProperty(key, value);
		
	}
	
	
}
