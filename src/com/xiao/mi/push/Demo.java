package com.xiao.mi.push;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

public class Demo {
    public static void main(String[] args) {
    	xingetuisong();
    	
    }
    
    public static void  xingetuisong(){
    	MessageIOS message = new MessageIOS();
		message.setAlert("444444444444");
		message.setSound("default.caf");
		message.setBadge(1);
		TimeInterval acceptTime = new TimeInterval(0,0,23,59);
		message.addAcceptTime(acceptTime);
    	//7531d566a300eb2d16cabc21d3999a5076fd1666ed6fe99d4d56e20db8dedbd4 我
		//b970c5c94ec528353b3b71af8bfddb8e46c973e70684af3d20e0d57e7b225dda
		//9901aab43ed3ecc99d7fa61be59b47adf8f65895ca68d59e13eb1210fb19c4d8 测试刘远通
		//2200229902 295bc14219e94777b69bab3d04afb58b 触角
		//2200267232l 9723a9dc39d9eb4ab3626c499cd7575e 法义预发布
		//2200267231  3f27c5edf5af5fcd3693d177fb365cf0
		Map<String, Object> tempMap1 = new HashMap<String, Object>();
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("businessType","1004");
		tempMap1.put("custom", maps);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custom", tempMap1);
		message.setCustom((Map<String, Object>)map.get("custom"));
		long appid= 2200267231l;
    	XingeApp localXingeApp = new XingeApp(appid,"3f27c5edf5af5fcd3693d177fb365cf0");
		JSONObject localJSONObject = localXingeApp.pushSingleDevice("ea7222db97ae90851fbce83c127646c56d035178f03a1d6ad5a14c16c3f346eb",message, 2);
		System.out.println(localJSONObject.toString());
    }

}
