package com.push.xiao.mi;

import java.util.Map;

import com.push.constant.Constant;
import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Result;
import com.xiaomi.xmpush.server.Sender;

/**
 * 小米推送
 * @author yanjk
 * @Date 2016/12/23 9:52
 *
 */
public class XiaomiPushHandler {
	
	
	public static String pushMessage(String xiaomiAppSecret, Map<String, Object> map){
		//正式环境中使用
		Constants.useOfficial();
	    Sender sender = new Sender(xiaomiAppSecret);
	    String regId = (String)map.get("token");
	    /**
	     * 小米推送notifyType
	     * DEFAULT_ALL = -1;
	     * DEFAULT_SOUND  = 1;   使用默认提示音提示
	     * DEFAULT_VIBRATE = 2;  使用默认震动提示
	     * DEFAULT_LIGHTS = 4;   使用默认led灯光提示
	     */
	    Integer notifyType = 4;
	    //设置开关
	  	Object soundSwitch = map.get("soundSwitch");
	    //用户声音设置
  		String voiceMode = "";
		if(null != map.get("voiceMode")) {
			voiceMode = (String)map.get("voiceMode");
		}
  		//用户震动设置
  		String vibrateMode = "";
		if(null != map.get("vibrateMode")) {
			vibrateMode = (String)map.get("vibrateMode");
		}
  		if(voiceMode.equals(Constant.UserInfoMsgTipSetting.VOICEMODE_ENABLE)&& vibrateMode.equals(Constant.UserInfoMsgTipSetting.VIBRATEMODE_DISABLE)) {
  			notifyType = 1;//支持声音-判断声音开关1:开启
  		} else if(voiceMode.equals(Constant.UserInfoMsgTipSetting.VOICEMODE_DISABLE)&& vibrateMode.equals(Constant.UserInfoMsgTipSetting.VIBRATEMODE_ENABLE)) {
  			notifyType = 2;//支持震动
  		}
  		if(null!=soundSwitch&&Constant.UserInfoMsgTipSetting.SOUNDSWITCH_ENABLE.equals(soundSwitch.toString())){
  			notifyType = 1;//声音开关乐盈通智八哥使用
  		}
  		
  		
  		//可选项。默认情况下，通知栏只显示一条推送消息。如果通知栏要显示多条推送消息，需要针对不同的消息设置不同的notify_id（相同notify_id的通知栏消息会覆盖之前的）。
  		Integer notifyId = (int) (System.currentTimeMillis()/1000);
	    Message message = new Message.Builder()
                .title((String)map.get("title"))
                .description((String)map.get("content"))         //描述和title设置一样的值
                .payload((String)map.get("content"))
                .restrictedPackageName((String)map.get("packageName"))
                .passThrough(0)                                //通知栏消息
                .notifyType(notifyType)   
                .notifyId(notifyId)
                .build();
	    String response = "小米推送系统异常";
        try {
			Result result = sender.send(message, regId, 2);   //发送失败的话，重试2次
			if(null != result) {
				StringBuilder str = new StringBuilder();
				str.append("messageId:").append(result.getMessageId()).append(",errorCode:").append(result.getErrorCode());
				str.append(",reason:"+result.getReason());
				response = str.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    return response;
	}

}
