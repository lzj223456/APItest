package com.test.client;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.HashMap;
import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.List;
import java.util.Set;


public class TestPost {

	private static JSONObject responseBody;
	private static CloseableHttpClient HttpClient;

	private static int responseCode;
	private static CloseableHttpResponse HttpResponse;
	HashMap<String,String> responseHeads;

//	通过httpclient获取请求的反馈
	public void sendPost(String url,List<NameValuePair> params,HashMap<String,String>headers) throws ClientProtocolException, IOException {
		
		
		HttpClient = HttpClients.createDefault();
		//创建post请求对象
		HttpPost httpPost = new HttpPost(url);
		//设置主体格式,保证返回参数不乱码
		httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
		//设置头部信息
		Set<String> set = headers.keySet();
		for(Iterator<String> iterator = set.iterator();iterator.hasNext();) {
			String key = iterator.next();
			String value = headers.get(key);
			httpPost.addHeader(key,value);
		}
		HttpResponse  = HttpClient.execute(httpPost);
	}

//		以json格式获取到反馈的主题
	public JSONObject getBodyInJSON() throws ParseException, IOException {
		HttpEntity entity;
		String entityToString;
//		调用HttpResponse.getEntity()方法获取HttpEntity的实例对象，该对象包装了服务器的响应内容，
//		也就是所请求的数据。
		entity = HttpResponse.getEntity();
		////设置编码格式
		entityToString = EntityUtils.toString(entity,"utf-8");
		
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