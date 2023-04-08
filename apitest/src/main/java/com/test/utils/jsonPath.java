package com.test.utils;

import net.minidev.json.JSONArray;
/*java.lang.ClassCastException: class net.minidev.json.JSONArray cannot be cast to class 
com.alibaba.fastjson.JSONArray (net.minidev.json.JSONArray and com.alibaba.fastjson.JSONArray 
are in unnamed module of loader 'app')
*/
import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class jsonPath {
	public boolean isResponseCorrect(JSONObject jo,String checkpoint,String passValue) {
		//用jsonpath处理json,获取result中特定键值
		ReadContext context = JsonPath.parse(jo);
		JSONArray result = context.read("$.data.."+checkpoint);
		String resultString = result.get(0).toString();
		System.out.println(resultString);
		if(resultString.equals(passValue)) {
			return true;
		}else {
			return false;
		}
			
	}
}
