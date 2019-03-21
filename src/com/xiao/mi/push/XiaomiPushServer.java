package com.xiao.mi.push;

import java.util.ArrayList;
import java.util.List;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import com.xiaomi.xmpush.server.TargetedMessage;

public class XiaomiPushServer {
	
	public static String MY_PACKAGE_NAME = "com.hhly.customer";
	public static String APP_SECRET_KEY = "8GG911s0XGN2eyZJ0jS5Og==";
	
	
	public static void pushMessage(){
		//Constants.useOfficial();正式
		Constants.useSandbox();//测试
	}
	
	/**
	 * 构建消息体
	 * @return
	 * @throws Exception
	 */
	private Message buildMessage() throws Exception {
	     String PACKAGENAME = "com.xiaomi.mipushdemo";
	     String messagePayload = "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     Message message = new Message.Builder()
	             .title(title)//通知栏展示的通知标题
	             .description(description)//通知栏展示的通知通知描述
	             .payload(messagePayload)//消息内容
	             .restrictedPackageName(PACKAGENAME)//设置app的包名packageName。packageName必须和开发者网站上申请的结果一致。
	             .passThrough(0)  //1消息使用透传方式 0通知栏
	             .notifyType(1)     // 1使用默认提示音提示;2使用默认震动提示;4使用默认led灯光提示
	             .extra("flow_control", "4000")     // 设置平滑推送, 推送速度4000每秒(qps=4000)
	             .build();
	     return message;
	}
	
	private List<TargetedMessage> buildMessages() throws Exception {
	     List<TargetedMessage> messages = new ArrayList<TargetedMessage>();
	     TargetedMessage message1 = new TargetedMessage();
	     message1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias1");
	     message1.setMessage(buildMessage());
	     messages.add(message1);
	     TargetedMessage message2 = new TargetedMessage();
	     message2.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias2");
	     message2.setMessage(buildMessage());
	     messages.add(message2);
	     return messages;
	}
	
	
	
	
	
	
	
	
	
	
	
	private void sendMessage() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload= "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
//	     sender.send(message, regId, 0); //根据regID，发送消息到指定设备上，不重试。
	}

	private void sendMessages() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     List<TargetedMessage> messages = new ArrayList<TargetedMessage>();
	     TargetedMessage targetedMessage1 = new TargetedMessage();
	     targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias1");
	     String messagePayload1= "This is a message1";
	     String title1 = "notification title1";
	     String description1 = "notification description1";
	     Message message1 = new Message.Builder()
	                .title(title1)
	                .description(description1).payload(messagePayload1)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     targetedMessage1.setMessage(message1);
	     messages.add(targetedMessage1);
	     TargetedMessage targetedMessage2 = new TargetedMessage();
	     targetedMessage1.setTarget(TargetedMessage.TARGET_TYPE_ALIAS, "alias2");
	     String messagePayload2= "This is a message2";
	     String title2 = "notification title2";
	     String description2 = "notification description2";
	     Message message2 = new Message.Builder()
	                .title(title2)
	                .description(description2).payload(messagePayload2)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     targetedMessage2.setMessage(message2);
	     messages.add(targetedMessage2);
	     sender.send(messages, 0); //根据alias，发送消息到指定设备上，不重试。
	}

	private void sendMessageToAlias() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload = "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     String alias = "testAlias";    //alias非空白，不能包含逗号，长度小于128。
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.sendToAlias(message, alias, 0); //根据alias，发送消息到指定设备上，不重试。
	}

	private void sendMessageToAliases() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload = "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     List<String> aliasList = new ArrayList<String>();
	     aliasList.add("testAlias1");  //alias非空白，不能包含逗号，长度小于128。
	     aliasList.add("testAlias2");  //alias非空白，不能包含逗号，长度小于128。
	     aliasList.add("testAlias3");  //alias非空白，不能包含逗号，长度小于128。
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.sendToAlias(message, aliasList, 0); //根据aliasList，发送消息到指定设备上，不重试。
	}

	private void sendBroadcast() throws Exception {
	     Constants.useOfficial();
	     Sender sender = new Sender(APP_SECRET_KEY);
	     String messagePayload = "This is a message";
	     String title = "notification title";
	     String description = "notification description";
	     String topic = "testTopic";
	     Message message = new Message.Builder()
	                .title(title)
	                .description(description).payload(messagePayload)
	                .restrictedPackageName(MY_PACKAGE_NAME)
	                .notifyType(1)     // 使用默认提示音提示
	                .build();
	     sender.broadcast(message, topic, 0); //根据topic，发送消息到指定一组设备上，不重试。
	}
	
	
}
