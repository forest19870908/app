package com.cs.app.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor - 令牌
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	/** "令牌"属性名称 */
	private static final String TOKEN_ATTRIBUTE_NAME = "token";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//登陆接口不需要拦截
		if(request.getRequestURI().startsWith("/app/login")){
			return true;
		}
		String token=null;
		if(request.getParameter(TOKEN_ATTRIBUTE_NAME) != null){
			token=request.getParameter(TOKEN_ATTRIBUTE_NAME);
		}
		if(StringUtils.isEmpty(token)){
			if(request.getHeader(TOKEN_ATTRIBUTE_NAME)!=null){
				token=request.getHeader(TOKEN_ATTRIBUTE_NAME);
			}
		}
		if(StringUtils.isEmpty(token)){
			return false;
		}
		if(token.equals("123456")){
			return true;
		}
		return false;
	}

}