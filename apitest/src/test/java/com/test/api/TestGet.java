package com.test.api;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.RestfulClient;
import com.test.utils.JSONParser;

public class TestGet {
	RestfulClient client;
	JSONObject responseBody;
	JSONParser jParser;
	int responseCode;
	String city;
	String url = "http://api.k780.com:88/?app=weather.today&weaid=101020100&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
	
	@Test
	public void TestGetRequest() {
		Assert.assertEquals(city, "上海");
		Assert.assertEquals(responseCode, 200);
	}
	@BeforeClass
	
	public void beforeClass() throws ClientProtocolException, IOException {
		client = new RestfulClient();
		client.getResponse(url);
		
		responseCode = client.getCodeInNumber();
		responseBody = client.getBodyInJSON();
		
		jParser = new JSONParser();
		city = jParser.getCity(responseBody);
	
		
		
		
		
		
	}
	
	
	
	
	
	
	
}
