package com.cs.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.cs.app.interceptor.TokenInterceptor;
import com.cs.app.model.Tokenlog;
import com.cs.app.service.TokenlogService;
import com.cs.app.util.TokenUtils;
import com.cs.app.util.WebUtils;
import com.cs.app.wx.util.WxLoginUtil;
import com.cs.common.util.CodeUtil;
import com.cs.core.enums.StatusEnum;
import com.cs.core.exception.ValidateException;
import com.cs.core.model.Org;
import com.cs.core.model.Position;
import com.cs.core.model.User;
import com.cs.core.service.OrgService;
import com.cs.core.service.PositionService;
import com.cs.core.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("")
@Controller
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private OrgService orgService;

    @Resource
    private TokenlogService tokenlogService;

    @Resource
    private PositionService positionService;
    /**
     * 登陆--页面
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        return "login";
    }
    /**
     * 登陆--操作
     *
     * @param request
     * @param response
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, HttpServletResponse response, ModelMap model, String username, String password) throws Exception {
        String appuserid=WebUtils.getCookie(request,"appuserid");
        String orgUuid=null;
        if(StringUtils.isEmpty(appuserid)) {
            orgUuid = (String) request.getSession().getAttribute("state");
            if (orgUuid == null) {
                throw new ValidateException("请重新从工作台进入");
            }
        }else{
            orgUuid=WebUtils.getCookie(request,"apporgid");
        }
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ValidateException("请输入账号和密码");
        }
        User user = userService.findUserByUserAccount(username,orgUuid);
        if(user==null|| StatusEnum.DELETE.getKey().equals(user.getStatusF())){
            throw new ValidateException("账号不存在");
        }
        Org org = orgService.selectByPrimaryKey(orgUuid);
//        if(!org.getUuidF().equals(user.getOrgOrgF())){
//            throw new ValidateException("账号不存在");
//        }
        if(StatusEnum.DISABLED.getKey().equals(user.getStatusF())){
            throw new ValidateException("账号被禁用");
        }
        password = DigestUtils.sha256Hex(password);
        if(!password.equals(user.getPasswordF())){
            throw new ValidateException("密码错误");
        }
        if(StringUtils.isEmpty(appuserid)){
            String code = (String) request.getSession().getAttribute("code");
            if (StringUtils.isEmpty(code)) {
                throw new ValidateException("请重新从工作台进入");
            }
            JSONObject jsonObject = WxLoginUtil.login(code,org);
            if (!StringUtils.isEmpty(jsonObject)) {
                String errcode = jsonObject.getString("errcode");
                if ("0".equals(errcode)) {//登陆正常
                    appuserid = jsonObject.getString("UserId");
                    WebUtils.addCookie(request, response, "appuserid", appuserid);
                    WebUtils.addCookie(request, response, "apporgid", orgUuid);
                } else {
                    //获取用户id错误
                    throw new ValidateException("请重新从工作台进入");
                }
            }
        }
        String token= CodeUtil.getUuid();
        WebUtils.addCookie(request, response, TokenInterceptor.TOKEN_ATTRIBUTE_NAME, token);
        Tokenlog tokenlog=new Tokenlog();
        tokenlog.setUuidF(token);
        tokenlog.setCodeF(appuserid);
        tokenlog.setNameF(username);
        tokenlog.setUserCreatorF(user.getUuidF());
        tokenlog.setAppuseridF(appuserid);
        tokenlog.setOrgOrgF(orgUuid);
        tokenlogService.createTokenlog(tokenlog);
        TokenUtils.putToken(token,tokenlog);
        Position position = positionService.selectByPrimaryKey(user.getPositionPositionF());
        request.getSession().setAttribute("user",user);
        request.getSession().setAttribute("org",org);
        request.getSession().setAttribute("position",position);
        return "success";
    }

    /**
     * 解除绑定用户
     * @param request
     * @return
     */
    @RequestMapping("/unbinduser")
    @ResponseBody
    public String unbinduser(HttpServletRequest request, HttpServletResponse response){
        //解除绑定用户
        String tooken=WebUtils.getCookie(request,TokenInterceptor.TOKEN_ATTRIBUTE_NAME);
        tokenlogService.unbinduser(tooken);

        WebUtils.removeCookie(request, response, TokenInterceptor.TOKEN_ATTRIBUTE_NAME);
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("org");
        request.getSession().removeAttribute("position");
        return "success";
    }
}
