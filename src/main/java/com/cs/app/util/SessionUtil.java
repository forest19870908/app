package com.cs.app.util;

import com.cs.core.model.Org;
import com.cs.core.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	public static User getUser() {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return user;
	}
	public static String getUserUuid(){
		return getUser().getUuidF();
	}
	public static Org getOrg() {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		Org org = (Org) session.getAttribute("org");
		return org;
	}
	public static String getOrgUuid(){
		return getOrg().getUuidF();
	}
}
