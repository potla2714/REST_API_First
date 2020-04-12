package com.base.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.Property;

public class TestBase {
	public Properties prop;
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("D:\\WorkSpace1\\RestAPITest\\src\\main\\java\\com\\api\\config\\config.properties");
			prop.load(ip);

		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		  
	}

}
