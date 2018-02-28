package com.cs.app.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.cs.app.wx.WxCom;
import com.cs.app.wx.WxConstants;

public class WxLoginUtil {

	/**
	 * 单点登陆
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject login(String code) {
		// 拼接请求地址
		String requestUrl = WxConstants.LOGIN_INFO_URL
				.replace("ACCESS_TOKEN", WxCom.getAccessToken(WxConstants.CORPID, WxConstants.MOBLE_SECRET))
				.replace("CODE", code);
		// 创建成员
		JSONObject jsonObject = WxCom.httpsRequest(requestUrl, "GET", null);
		return jsonObject;
	}

}
