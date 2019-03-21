package com.push.mei.zu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.meizu.push.sdk.server.IFlymePush;
import com.meizu.push.sdk.server.ResultPack;
import com.meizu.push.sdk.server.VarnishedMessage;
import com.push.constant.Constant;

/**
 * 魅族推送
 * 
 * @author yanjk
 * @Date 2016/12/23 9:52
 *
 */
public class MeizuPushHandler {

	public static String pushMessage(String meizuAppSecret, Long meizuAppId, Map<String, Object> map) {
		IFlymePush push = new IFlymePush(meizuAppSecret);
		// 震动标识，默认是true
		boolean vibrateFlag = true;
		// 声音标识，默认是true
		boolean soundFlag = true;
		// 组装消息
		VarnishedMessage message = new VarnishedMessage.Builder().appId(meizuAppId).title((String) map.get("title"))
				.content((String) map.get("content")).vibrate(vibrateFlag).sound(soundFlag).build();
		// 目标用户
		List<String> pushIds = new ArrayList<String>();
		pushIds.add((String) map.get("token"));

		// 调用推送服务
		ResultPack<Map<Integer, List<String>>> result = null;
		try {
			result = push.pushMessage(message, pushIds, 2); // 如果推送失败，默认重新推送2次
		} catch (IOException e) {
			e.printStackTrace();
		}
		String response = "魅族推送系统异常";
		if (result != null) {
			response = handleResult(result);
		}
		return response;
	}

	private static String handleResult(ResultPack<Map<Integer, List<String>>> result) {
		StringBuilder str = new StringBuilder();
		if (result.isSucceed()) {
			str.append("成功，isSucceed:").append(result.isSucceed());
		} else {
			str.append("失败， resultCode：").append(result.code()).append(",comment:").append(result.comment());
		}
		return str.toString();
	}

}
