package com.cs.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.cs.app.util.WebUtils;
import com.cs.app.wx.util.WxLoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/app")
@Controller
public class MainController {

    /**
     * 移动审批入口
     * @param code 获取身份信息的code
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response, String code) {
        String userId=WebUtils.getCookie(request,"userId");
        if(userId==null) {
            JSONObject jsonObject = WxLoginUtil.login(code);
            if (!StringUtils.isEmpty(jsonObject)) {
                String errcode = jsonObject.getString("errcode");
                if ("0".equals(errcode)) {//登陆正常
                    userId = jsonObject.getString("UserId");
                    WebUtils.addCookie(request, response, "userId", userId);
                } else {
                    //获取用户id错误
                }
            }
        }
        return "main";
    }
}
