package com.xiao.mi.push;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

import sun.rmi.runtime.Log;

public class TestPush {
	public static String MY_PACKAGE_NAME = "com.hhly.customer";
	public static String APP_SECRET_KEY = "8GG911s0XGN2eyZJ0jS5Og==";
	private void sendMessage() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload = "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
//	     Result result = sender.send(message, regId, 0); //Result对于sendToAlias()，broadcast()和send()调用方式完全一样
//	     Log.v("Server response: ", "MessageId: " + result.getMessageId()
//	                                + " ErrorCode: " + result.getErrorCode().toString()
//	                                + " Reason: " + result.getReason());
	}
}
