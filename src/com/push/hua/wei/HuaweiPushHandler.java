package com.push.hua.wei;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import nsp.NSPClient;
import nsp.OAuth2Client;
import nsp.support.common.AccessToken;
import nsp.support.common.NSPException;

/**
 * 华为推送
 * @author yanjk
 * @Date 2016/12/23 9:52
 *
 */
public class HuaweiPushHandler {
	
	
	public static final String TIMESTAMP_NORMAL = "yyyy-MM-dd HH:mm:ss";
	
	public static String pushMessage(String huaweiAppSecret, String huaweiAppId, Map<String, Object> map){
		String response = "华为推送NSPException异常，推送失败";
		OAuth2Client oauth2Client = new OAuth2Client();
		try {
			oauth2Client.initKeyStoreStream(HuaweiPushHandler.class.getResource("/mykeystorebj.jks").openStream(), "123456");
	        AccessToken access_token = oauth2Client.getAccessToken("client_credentials", huaweiAppId, huaweiAppSecret);
	        NSPClient client = new NSPClient(access_token.getAccess_token());
	        //设置每个路由的连接数和最大连接数
	        client.initHttpConnections(30, 50);
	        //如果访问https必须导入证书流和密码
	        client.initKeyStoreStream(HuaweiPushHandler.class.getResource("/mykeystorebj.jks").openStream(), "123456");
	        response = singleSend2(client, map);
		} catch(Exception e) {
		}
		return response;
	}
	
	
	 /**
     * 单发消息
     * @param client
     * @throws NSPException
     */
    public static String singleSend(NSPClient client, Map<String, Object> map)
    {
    	 long currentTime = System.currentTimeMillis();
         SimpleDateFormat dataFormat = new SimpleDateFormat(TIMESTAMP_NORMAL);
         
         // 目标用户，必选。
         // 由客户端获取， 32 字节长度。手机上安装了push应用后，会到push服务器申请token，申请到的token会上报给应用服务器
         String token = (String)map.get("token");
         
         // 发送到设备上的消息，必选
         // 最长为4096 字节（开发者自定义，自解析）
//         String android = "{\"notification_title\":\"the good news!\",\"notification_content\":\"Price reduction!\",\"doings\":3,\"url\":\"vmall.com\"}";
         Map<String, Object> contentMap = new HashMap<String, Object>();
         contentMap.put("notification_title", map.get("title"));
         contentMap.put("notification_content", map.get("content"));
         //1--打开应用   3--打开链接   4--打开富媒体消息
         contentMap.put("doings", 1);
         String android = JSON.toJSONString(contentMap);
         
         // 消息是否需要缓存，必选
         // 0：不缓存
         // 1：缓存
         // 缺省值为0
         int cacheMode = 1;
         
         // 标识消息类型（缓存机制），必选
         // 由调用端赋值，取值范围（1~100）。当TMID+msgType的值一样时，仅缓存最新的一条消息
         int msgType = 1;
         
         // 可选
         // 0: 当前用户
         // 1: 主要用户
         // -1: 默认用户
         //
         String userType = "1";
         
         // unix时间戳，可选
         // 格式：2016-12-23 15:40
         // 消息过期删除时间
         // 如果不填写，默认超时时间为当前时间后48小时
         String expire_time = dataFormat.format(currentTime + 3 * 24 * 60 * 60 * 1000);
         
         // 构造请求
         HashMap<String, Object> hashMap = new HashMap<String, Object>();
         hashMap.put("deviceToken", token);
         hashMap.put("android", android);
         hashMap.put("cacheMode", cacheMode);
         hashMap.put("msgType", msgType);
         hashMap.put("userType", userType);
         hashMap.put("expire_time", expire_time);
         
         // 设置http超时时间
         client.setTimeout(10000, 15000);
         // 接口调用
         PushRet resp = null;
		 try {
			resp = client.call("openpush.message.psSingleSend", hashMap, PushRet.class);
		 } catch (NSPException e) {
		 }
	     String response = "华为推送NSPException异常，推送失败";
	     if(resp != null) {
	    	 response = "消息响应:" + resp.getResultcode() + ",message:" + resp.getMessage();
	     }
         return response;
    }

	/**
	 * 单发消息---------华为专门做推送的方法
	 * @param client
	 * @throws NSPException
	 */
	public static String singleSend2(NSPClient client, Map<String, Object> map) {
		// 目标用户，必选。
		// 由客户端获取， 32 字节长度。手机上安装了push应用后，会到push服务器申请token，申请到的token会上报给应用服务器
		String token = (String)map.get("token");

		// 发送到设备上的消息，必选
		// 最长为4096 字节（开发者自定义，自解析）
//        String android = "{\"notification_title\":\"the good news!\",\"notification_content\":\"Price reduction!\",\"doings\":3,\"url\":\"vmall.com\"}";
		Map<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("notification_title", map.get("title"));
		contentMap.put("notification_content", map.get("content"));
		//1--打开应用   3--打开链接   4--打开富媒体消息
		contentMap.put("doings", 1);
		String android = JSON.toJSONString(contentMap);
		//推送范围，必选
		//1：指定用户，必须指定tokens字段
		//2：所有人，无需指定tokens，tags，exclude_tags
		//3：一群人，必须指定tags或者exclude_tags字段
		Integer push_type = 1;

		//构造请求
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("push_type", push_type);
		hashMap.put("tokens", token);
		hashMap.put("android", android);

		//设置http超时时间
		client.setTimeout(10000, 15000);
		String response = null;
		try {
			response = client.call("openpush.openapi.notification_send", hashMap, String.class);
		} catch (NSPException e) {
		}
		return response;
	}

}
