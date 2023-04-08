package com.test.client;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.HashMap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class RestfulClient {

	private static JSONObject responseBody;
	private static CloseableHttpClient HttpClient;
	private static HttpGet HttpGet;
	private static int responseCode;
	private static CloseableHttpResponse HttpResponse;
	HashMap<String,String> responseHeads;
//	public static void main(String[] args) throws ClientProtocolException, IOException {
//	通过httpclient获取请求的反馈
	public void getResponse(String url) throws ClientProtocolException, IOException {
		
//		String url = "https://eolink.o.apispace.com/teladress/teladress?";
		HttpClient = HttpClients.createDefault();
		HttpGet = new HttpGet(url);
		HttpResponse  = HttpClient.execute(HttpGet);
	}

//		以json格式获取到反馈的主题
	public JSONObject getBodyInJSON() throws ParseException, IOException {
		HttpEntity entity;
		String entityToString;
		entity = HttpResponse.getEntity();
		entityToString = EntityUtils.toString(entity);
		responseBody = JSON.parseObject(entityToString);
		System.out.println("this is response body" + responseBody);
		return responseBody;
		
	}
//	以哈希图的方式获取到反馈头
		public HashMap<String, String>getHeaderInHash(){
			Header[] headers;
			headers = HttpResponse.getAllHeaders();
			responseHeads = new HashMap<String, String>();
			for(Header header:headers) {
				responseHeads.put(header.getName(), header.getValue());
			}
			return responseHeads;
			
	}
		//获取发聩状态码
		public int getCodeInNumber() {
			responseCode = HttpResponse.getStatusLine().getStatusCode();
			System.out.println("this is your code"+responseCode);
			return responseCode;
			
		}

}
