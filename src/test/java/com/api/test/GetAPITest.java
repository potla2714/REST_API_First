package com.api.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.client.RestClient;
import com.base.api.TestBase;

public class GetAPITest extends TestBase{
	TestBase testBase;
	String URL;
	String serviceURL;
	String URI;
	RestClient restClient;
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		URL = prop.getProperty("url");
		serviceURL = prop.getProperty("serviceURL");
		URI = URL + serviceURL;
	}
	
	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		restClient.get(URI);
		
	}

}
