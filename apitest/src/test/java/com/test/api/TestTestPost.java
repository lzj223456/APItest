package com.test.api;
//数据分离处理的接口
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import com.alibaba.fastjson.JSONObject;
import com.test.client.TestPost;
import com.test.utils.ExcelProcess;
import com.test.utils.jsonPath;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class TestTestPost extends TestApi {
	 private Object[][] excelData;
	private TestPost client;
	private HashMap<String, String> hashHead;
	private String url;
	private JSONObject responseBody;
	private int responseCode;

	@BeforeClass
	 public void setup() throws IOException {
		 //读取用例excel
		// config.properties————testData》》TestApi——excelPath=prop.getProperty("testData");
		 excelData = ExcelProcess.proessExcel(excelPath, 1);
		 System.out.println(excelData.length+"------excelData.length");
		 //实例化client
		 client = new TestPost();
		 //设置好请求头
		 hashHead = new HashMap<String,String>();
		 hashHead.put("X-APISpace-Token", "sgqge4aok0s6a607851o6mmsomz7xd18");
	      hashHead.put("Authorization-Type", "apikey");
	      hashHead.put("Content-Type", "application/x-www-form-urlencoded");
	}
		 
		 @Test
		 public void testPostRequest() throws ClientProtocolException, IOException {
			 //从第二行开始遍历表单，跳过表头
			 for(int i=1;i<excelData.length;i++) {
				 String address = excelData[i][3].toString();
				 url = host+address;

				 System.out.println(url);
				 
				 String checkPoint = excelData[i][4].toString();
				 System.out.println("检查点"+checkPoint);
				 String checkValue = excelData[i][5].toString();
				 System.out.println(checkValue);
				 //创建NameValuePair 类型的集合
				 List<NameValuePair> keys = new ArrayList<NameValuePair>();
				 //需要注意参数的数量
				 for(int j=7;j<excelData[i].length;j=j+2) {
					 //因为每种请求的参数个数不确定，在这里进行非空判断
					   System.out.println("-------");
			            if(excelData[i][j]==null){
			                break;
			            }
			            //接口实例化，使用构造方法传值
			            NameValuePair pair = new BasicNameValuePair(excelData[i][j].toString(),excelData[i][j+1].toString());
			            System.out.println(pair);
			            keys.add(pair);             
			        }
			      
			      //发出请求
			      client.sendPost(url, keys, hashHead);

			      responseBody = client.getBodyInJSON();
			      responseCode = client.getCodeInNumber();

			      jsonPath jsonP = new jsonPath();
			      boolean result = jsonP.isResponseCorrect(responseBody, checkPoint, checkValue);
			      
			      //断言判断结果
			      Assert.assertTrue(result);
			      
			      Assert.assertEquals(responseCode, 200);
			    }
				 
			 
		 }
		 
		 
	 }



