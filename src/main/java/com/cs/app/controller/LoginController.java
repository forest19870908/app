package com.cs.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.cs.app.model.Tokenlog;
import com.cs.app.service.TokenlogService;
import com.cs.app.util.TokenUtils;
import com.cs.app.util.WebUtils;
import com.cs.app.wx.util.WxLoginUtil;
import com.cs.common.util.CodeUtil;
import com.cs.core.enums.StatusEnum;
import com.cs.core.exception.ValidateException;
import com.cs.core.model.Org;
import com.cs.core.model.User;
import com.cs.core.service.OrgService;
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
import javax.servlet.http.HttpSession;

@RequestMapping("")
@Controller
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private OrgService orgService;

    @Resource
    private TokenlogService tokenlogService;
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
        model.addAttribute("code",request.getParameter("code"));
        model.addAttribute("state",request.getParameter("state"));
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
     * @param state 组织id
     * @param code 获取身份信息的code
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model, String username, String password, String code, String state) throws Exception {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ValidateException("请输入账号和密码");
        }
        User user = userService.findUserByUserAccount(username);
        if(user==null|| StatusEnum.DELETE.getKey().equals(user.getStatusF())){
            throw new ValidateException("账号不存在");
        }
        String orgUuid=state;
        Org org = orgService.selectByCode(orgUuid);
        if(!org.getUuidF().equals(user.getOrgOrgF())){
            throw new ValidateException("账号不存在");
        }
        if(StatusEnum.DISABLED.getKey().equals(user.getStatusF())){
            throw new ValidateException("账号被禁用");
        }
        password = DigestUtils.sha256Hex(password);
        if(!password.equals(user.getPasswordF())){
            throw new ValidateException("密码错误");
        }
        JSONObject jsonObject = WxLoginUtil.login(code,org);
        if (!StringUtils.isEmpty(jsonObject)) {
            String errcode = jsonObject.getString("errcode");
            if ("0".equals(errcode)) {//登陆正常
                String userId = jsonObject.getString("UserId");
                String token= CodeUtil.getUuid();
                WebUtils.addCookie(request, response, "token", token);
                Tokenlog tokenlog=new Tokenlog();
                tokenlog.setUuidF(token);
                tokenlog.setCodeF(userId);
                tokenlog.setNameF(username);
                tokenlog.setAppuseridF(userId);
                tokenlog.setOrgOrgF(orgUuid);
                tokenlogService.createTokenlog(tokenlog);
                TokenUtils.putToken(token,tokenlog);
                session.setAttribute("user",user);
                session.setAttribute("org",org);
            } else {
                //获取用户id错误
                throw new ValidateException("请重新登录企业微信");
            }
        }
        return "success";
    }
}
