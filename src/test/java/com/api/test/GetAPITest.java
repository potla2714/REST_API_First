package com.api.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.client.RestClient;
import com.api.util.TestUtil;
import com.base.api.TestBase;

public class GetAPITest extends TestBase{
	TestBase testBase;
	String URL;
	String serviceURL;
	String URI;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		URL = prop.getProperty("url");
		serviceURL = prop.getProperty("serviceURL");
		URI = URL + serviceURL;
	}
	
	@Test (priority =1)
	public void getAPITestWithOutHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(URI);
		
		//status code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code--->"+statusCode);
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
				
				//Json String
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from  API---> "+responseJson);
				
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("Values of per Page is -->" +perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Values of total is -->" +totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//Get the Value from JSON ARRAY
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
				String first_Name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String last_Name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");				
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				System.out.println(id);
				System.out.println(email);
				System.out.println(first_Name);
				System.out.println(last_Name);
				System.out.println(avatar);
				
				
				// All Headers
				Header[] headerArray = closeableHttpResponse.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for(Header header : headerArray) {
					 allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers Array-->"+allHeaders);
		
	}
	
	@Test  (priority =2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("username", "anil");
		//headerMap.put("password", "anil.123");
		//headerMap.put("Auth Token", "877656");
		
		closeableHttpResponse = restClient.get(URI);
		
		//status code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code--->"+statusCode);
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
				
				
				//Json String
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("Response JSON from  API---> "+responseJson);
				
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("Values of per Page is -->" +perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("Values of total is -->" +totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//Get the Value from JSON ARRAY
				String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
				String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
				String first_Name = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String last_Name = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");				
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				System.out.println(id);
				System.out.println(email);
				System.out.println(first_Name);
				System.out.println(last_Name);
				System.out.println(avatar);
				
				
				// All Headers
				Header[] headerArray = closeableHttpResponse.getAllHeaders();
				HashMap<String, String> allHeaders = new HashMap<String, String>();
				for(Header header : headerArray) {
					 allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers Array-->"+allHeaders);
		
	}

}
