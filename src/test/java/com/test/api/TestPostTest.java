package com.test.api;
//没有做数据驱动的初始天气预报接口
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;
import com.test.client.TestPost;
import com.test.utils.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class TestPostTest {
	 String city;
	 int responseCode;
	 String url ="http://api.k780.com:88/";
	private TestPost client;
	private JSONObject responseBody;
	private JSONParser jParser;
	@Test
	public void testPostRequest() {
		Assert.assertEquals(city, "北京");
		Assert.assertEquals(responseCode, 200);
	}
	@BeforeClass
	public void beforeClass() throws ClientProtocolException, IOException {
		client = new TestPost();
		//用NameValuePair添加参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("app", "weather.today"));
		params.add(new BasicNameValuePair("weaid", "101010100"));
		params.add(new BasicNameValuePair("appkey", "10003"));
		params.add(new BasicNameValuePair("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4"));
		params.add(new BasicNameValuePair("format", "json"));
		
		//用嘻哈表准备请求头信息
		HashMap<String, String> hashHead = new HashMap<String, String>();
	      hashHead.put("Content-Type", "application/x-www-form-urlencoded");
		
	      //传参发送post请求并接收反馈
	      client.sendPost(url, params, hashHead);
	      responseCode = client.getCodeInNumber();
	      responseBody = client.getBodyInJSON();
	      System.out.println(responseBody);
	      jParser = new JSONParser();
	      city = jParser.getCity(responseBody);
	}
}
