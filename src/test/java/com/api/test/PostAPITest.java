package com.api.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.client.RestClient;
import com.api.data.UsersData;
import com.base.api.TestBase;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PostAPITest extends TestBase{
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
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		
		//Headers Adding
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("username", "anil");
		//headerMap.put("password", "anil.123");
		//headerMap.put("Auth Token", "877656");
		
		//Jackson API--this utlity convert Java Objects to JSON Objects  and Json Objects to java
		ObjectMapper mapper = new ObjectMapper();
		UsersData userData = new UsersData("morpheus", "leader");   //excepted users objects
		//now userData java objects convert to JSON 
		mapper.writeValue(new File("D:\\WorkSpace1\\RestAPITest\\src\\main\\java\\com\\api\\data\\UserData.json"), userData);;
		
		//Objects to JSON in String
		String userDataJsonString = mapper.writeValueAsString(userData);
		System.out.println(userDataJsonString);
		closeableHttpResponse = restClient.post(URI, userDataJsonString, headerMap);  //call the API
		
		//Validate the response from API
		//1.Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->"+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);
		
		//Json String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The Response from API--->" +responseJson);
		
		//Validate --->//Json to Java 
		UsersData usersDataResponseObj = mapper.readValue(responseString, UsersData.class); //Actual users objcts
		System.out.println(usersDataResponseObj);		
		Assert.assertTrue(userData.getName().equals(usersDataResponseObj.getName()));
		Assert.assertTrue(userData.getJob().equals(usersDataResponseObj.getJob()));
		System.out.println(usersDataResponseObj.getId());
		System.out.println(usersDataResponseObj.getCreatedAt());
	}

}
