package com.test.api;
////没有做数据驱动的进阶版天气预报接口
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

public class TestPostTest3 {
	 String city;
	 int responseCode;
	 String url ="https://eolink.o.apispace.com/teladress/teladress";
	private TestPost client;
	private JSONObject responseBody;
	private JSONParser jParser;
	@Test
	public void testPostRequest() {
		Assert.assertEquals(city, "å¤©æ´¥");
		Assert.assertEquals(responseCode, 200);
	}
	@BeforeClass
	public void beforeClass() throws ClientProtocolException, IOException {
		client = new TestPost();
		//用NameValuePair添加参数
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("mobile", "15102207658"));
		
		//用嘻哈表准备请求头信息
		HashMap<String, String> hashHead = new HashMap<String, String>();
	      hashHead.put("X-APISpace-Token", "sgqge4aok0s6a607851o6mmsomz7xd18");
	      hashHead.put("Authorization-Type", "apikey");
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
