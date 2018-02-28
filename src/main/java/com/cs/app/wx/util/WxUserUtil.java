package com.cs.app.wx.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.cs.app.wx.WxCom;
import com.cs.app.wx.WxConstants;
import com.cs.app.wx.model.WxUser;

public class WxUserUtil {

	/**
	 * 发送微信企业消息
	 * 
	 * @param json
	 * @return
	 */
	public static String userManager(String json) {
		// 拼接请求地址
		String requestUrl = WxConstants.USER_URL.replace("ACCESS_TOKEN",
				WxCom.getAccessToken(WxConstants.CORPID, WxConstants.ADDRESS_LIST_SECRET));
		// 创建成员
		JSONObject jsonObject = WxCom.httpsRequest(requestUrl, "POST", json);
		return jsonObject.toJSONString();
	}

	public static void main(String[] args) {
		WxUser wxUser = new WxUser();
		wxUser.setUserid("ShuCeShi");
		wxUser.setName("树测试");
		List<Integer> department = new ArrayList<Integer>();
		department.add(1);
		wxUser.setDepartment(department);
		wxUser.setMobile("13652135421");
		String jsonString = JSONObject.toJSONString(wxUser);
		System.out.println(jsonString);
		System.out.println(userManager(jsonString));
	}

}
