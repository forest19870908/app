package com.cs.app.interceptor;

import com.cs.app.util.TokenUtils;
import com.cs.app.util.WebUtils;
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
		//静态资源不需要拦截
		if(request.getRequestURI().startsWith("/public")){
			return true;
		}
		//登陆接口不需要拦截
		if(request.getRequestURI().startsWith("/login")||request.getRequestURI().startsWith("/doLogin")){
			return true;
		}
		String token=WebUtils.getCookie(request,TOKEN_ATTRIBUTE_NAME);
		if(StringUtils.isEmpty(token)) {
			if (request.getParameter(TOKEN_ATTRIBUTE_NAME) != null) {
				token = request.getParameter(TOKEN_ATTRIBUTE_NAME);
			}
		}
		if(StringUtils.isEmpty(token)){
			if(request.getHeader(TOKEN_ATTRIBUTE_NAME)!=null){
				token=request.getHeader(TOKEN_ATTRIBUTE_NAME);
			}
		}
		if(StringUtils.isEmpty(token)){
			//跳转到登录页面时带参数
			request.getRequestDispatcher("/login").forward(request,response);
			//response.sendRedirect("/login");
			return false;
		}
		if(TokenUtils.getToken(token)!=null){
			return true;
		}
		response.sendRedirect("/login");
		return false;
	}

}