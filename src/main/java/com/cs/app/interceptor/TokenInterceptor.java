package com.cs.app.interceptor;

import com.cs.app.model.Tokenlog;
import com.cs.app.service.TokenlogService;
import com.cs.app.util.TokenUtils;
import com.cs.app.util.WebUtils;
import com.cs.core.model.Org;
import com.cs.core.model.Position;
import com.cs.core.model.User;
import com.cs.core.service.OrgService;
import com.cs.core.service.PositionService;
import com.cs.core.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interceptor - 令牌
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
	/** "令牌"属性名称 */
	public static final String TOKEN_ATTRIBUTE_NAME = "token";
	@Resource
	private TokenlogService tokenlogService;
	@Resource
	private OrgService orgService;
	@Resource
	private UserService userService;
	@Resource
	private PositionService positionService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(request.getParameter("code")!=null){
			request.getSession().setAttribute("code",request.getParameter("code"));
			request.getSession().setAttribute("state",request.getParameter("state"));
		}
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
			//request.getRequestDispatcher("/login").forward(request,response);
			response.sendRedirect("/login");
			return false;
		}
		if(TokenUtils.getToken(token)!=null){
			if(request.getSession().getAttribute("user")==null) {
				WebUtils.addCookie(request, response, TOKEN_ATTRIBUTE_NAME, token);
				Tokenlog tokenlog = tokenlogService.selectByPrimaryKey(token);
				Org org=orgService.selectByPrimaryKey(tokenlog.getOrgOrgF());
				User user = userService.findUserByUserAccount(tokenlog.getNameF(),tokenlog.getOrgOrgF());
				Position position = positionService.selectByPrimaryKey(user.getPositionPositionF());
				request.getSession().setAttribute("org",org);
				request.getSession().setAttribute("user",user);
				request.getSession().setAttribute("position",position);
				WebUtils.addCookie(request, response, "appuserid", tokenlog.getAppuseridF());
				WebUtils.addCookie(request, response, "apporgid", tokenlog.getOrgOrgF());
			}
			return true;
		}
		response.sendRedirect("/login");
		return false;
	}

}