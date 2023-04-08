package com.test.client;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.HashMap;

public class TestGetAPI {
	private static Header[] responseHeader;
	private static HttpEntity responseBody;
	private static CloseableHttpClient HttpClient;
	private static HttpGet HttpGet;
	private static int responseCode;
	private static CloseableHttpResponse HttpResponse;

	public static void main(String[] args) throws ClientProtocolException, IOException {
		String url = "http://api.k780.com:88/?app=weather.today&weaid=101020100&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
		HttpClient = HttpClients.createDefault();
		HttpGet = new HttpGet(url);
//		HttpGet.addHeader("X-APISpace-Token","sgqge4aok0s6a607851o6mmsomz7xd18");
//		HttpGet.addHeader("Authorization-Type","apikey");
		HttpResponse  = HttpClient.execute(HttpGet);
		responseCode = HttpResponse.getStatusLine().getStatusCode();
		responseBody = HttpResponse.getEntity();
		responseHeader = HttpResponse.getAllHeaders();
//		System.out.println(responseCode);
//		System.out.println(responseBody);
//		System.out.println(responseHeader);
		
		//用EntityUtils工具类将反馈主体处理为字符串形式
		String resnponseBodyString = EntityUtils.toString(responseBody,"utf-8");
		//用哈希图将反馈头信息以键值对形式保存
		HashMap<String,String> hashMap = new HashMap<String,String>();
		for(Header header:responseHeader){
			hashMap.put( header.getName(), header.getValue());}
		
		System.out.println(responseCode);
		System.out.println(resnponseBodyString);
		System.out.println();
		System.out.println(hashMap);
	}
	
}
