package com.cs.app.wx.util;

import com.alibaba.fastjson.JSONObject;
import com.cs.app.wx.WxCom;
import com.cs.app.wx.WxConstants;
import com.cs.core.model.Org;

public class WxLoginUtil {

	/**
	 * 单点登陆
	 * 
	 * @param code
	 * @param org
	 * @param org
	 * @return
	 */
	public static JSONObject login(String code, Org org) {
		// 拼接请求地址
		String requestUrl = WxConstants.LOGIN_INFO_URL
				.replace("ACCESS_TOKEN", WxCom.getAccessToken(org.getAppcorpidF(),org.getAppsecretF()))
				.replace("CODE", code);
		// 创建成员
		JSONObject jsonObject = WxCom.httpsRequest(requestUrl, "GET", null);
		return jsonObject;
	}

}
