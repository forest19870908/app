package com.cs.app.wx.model;

import java.util.HashMap;
import java.util.Map;

public class WxTextMsg extends WxMsgBase{
	
	private Map<String, String> text = new HashMap<String, String>();
	private String content;
	private int safe;
	private String msgtype= "text";
	
	public void setContent(String content) {
		this.content = content;
	}
	public int getSafe() {
		return safe;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}
	public Map<String, String> getText() {
		text.put("content", content);
		return text;
	}
	public String getMsgtype() {
		return msgtype;
	}

}
