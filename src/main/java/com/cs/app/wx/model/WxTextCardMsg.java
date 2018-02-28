package com.cs.app.wx.model;

import java.util.HashMap;
import java.util.Map;

public class WxTextCardMsg extends WxMsgBase{
	
	private Map<String, String> textcard = new HashMap<String, String>();
	private String title;
	private String description;
	private String url;
	private String btntxt;
	private int safe;
	private String msgtype= "textcard";
	
	public int getSafe() {
		return safe;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}
	public Map<String, String> getTextcard() {
		textcard.put("title", title);
		textcard.put("description", description);
		textcard.put("url", url);
		textcard.put("btntxt", btntxt);
		return textcard;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setBtntxt(String btntxt) {
		this.btntxt = btntxt;
	}

}
