package com.cs.app.wx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.cs.app.wx.WxCom;
import com.cs.app.wx.WxConstants;
import com.cs.app.wx.model.WxTextCardMsg;

public class WxSendMsgUtil {

	/**
	 * 发送微信企业消息
	 * 
	 * @param json
	 * @return
	 */
	public static String sendMsg(String json) {
		// 拼接请求地址
		String requestUrl = WxConstants.SEND_MSG_URL.replace("ACCESS_TOKEN",
				WxCom.getAccessToken(WxConstants.CORPID, WxConstants.MOBLE_SECRET));
		// 创建成员
		JSONObject jsonObject = WxCom.httpsRequest(requestUrl, "POST", json);
		return jsonObject.toJSONString();
	}

	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		WxTextCardMsg wxMsg = new WxTextCardMsg();
		wxMsg.setTouser("@all");
		wxMsg.setAgentid(1000002);
		wxMsg.setTitle("待办通知");
		wxMsg.setDescription("<div class=\"gray\">" + simpleDateFormat.format(new Date())
				+ "</div> <div class=\"normal\">恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">请于"
				+ simpleDateFormat.format(new Date()) + "前联系行政同事领取</div>");
		wxMsg.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515733768240&di=d7efd32dd243dd2cf0ae330a47c2442a&imgtype=0&src=http%3A%2F%2Fwanzao2.b0.upaiyun.com%2Fsystem%2Fpictures%2F17361042%2Foriginal%2Ffc4abb8905d38958.png");
		String jsonString = JSONObject.toJSONString(wxMsg);
		System.out.println(jsonString);
		System.out.println(sendMsg(jsonString));
	}

}
