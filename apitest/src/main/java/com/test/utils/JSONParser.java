package com.test.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JSONParser {
	JSONObject internalJSON;
	private String city;
	
	public String getCity(JSONObject jo) {
		
		try {
			//http://api.k780.com:88 返回的是result
			//"https://eolink.o.apispace.com/teladress/teladress"返回的是data
			JSONObject internalJSON = jo.getJSONObject("result");
			city = internalJSON.getString("citynm");
			System.out.println(city+"6666");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return city;
		
	}
}
