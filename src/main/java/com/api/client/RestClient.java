package com.api.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1. GET Method without Header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		//HttpClients is Class and createDefault method is availabele
		//it will create one client connection
		// and it will return CloseableHttpClient class
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);  //http get request call
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);   //hit the GET url
		return closeableHttpResponse;
			
	}
	
	//2. GET Method with Header
		public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet httpGet = new HttpGet(url);  //http get request call
			for(Map.Entry<String, String> entry: headerMap.entrySet() ) {
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);   //hit the GET url
			return closeableHttpResponse;
				
		}
	//3. Post method
		public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
			//HttpClients is Class and createDefault method is availabele
			//it will create one client connection
			// and it will return CloseableHttpClient class
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(url); //POst request call
			//SetEntity Method is used to define the Payload
			 httpPost.setEntity(new StringEntity(entityString));  //for PayLoad
			 
			//For headers
			 for(Map.Entry<String, String> entry: headerMap.entrySet() ) {
					httpPost.addHeader(entry.getKey(), entry.getValue());
				}
			 CloseableHttpResponse closeableHttpresponse = httpClient.execute(httpPost); //hit the URL
			 return closeableHttpresponse;
		}
		
		
		
		

}
