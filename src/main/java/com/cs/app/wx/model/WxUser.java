package com.cs.app.wx.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxUser {
	
	private String userid;
	private String name;
	private String english_name;
	private String mobile;
	private String position;
	private String gender;
	private String email;
	private String avatar_mediaid;
	private String telephone;
	private List<Integer> department;
	private List<Integer> order;
	private int isleader;
	private int enable =1;
	private Map<String, String> extattr = new HashMap<String, String>();
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnglish_name() {
		return english_name;
	}
	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatar_mediaid() {
		return avatar_mediaid;
	}
	public void setAvatar_mediaid(String avatar_mediaid) {
		this.avatar_mediaid = avatar_mediaid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<Integer> getDepartment() {
		return department;
	}
	public void setDepartment(List<Integer> department) {
		this.department = department;
	}
	public List<Integer> getOrder() {
		return order;
	}
	public void setOrder(List<Integer> order) {
		this.order = order;
	}
	public int getIsleader() {
		return isleader;
	}
	public void setIsleader(int isleader) {
		this.isleader = isleader;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Map<String, String> getExtattr() {
		return extattr;
	}
	public void setExtattr(Map<String, String> extattr) {
		this.extattr = extattr;
	}
	
	
}
