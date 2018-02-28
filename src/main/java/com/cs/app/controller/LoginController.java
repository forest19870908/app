package com.cs.app.controller;

import com.cs.app.util.JsonUtils;
import com.cs.app.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RequestMapping("/app")
@Controller
public class LoginController {
    /**
     * 登陆
     *
     * @param request
     * @param response
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model, String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            Message msg = new Message(Message.FAIL, "请输入账号和密码");
            return JsonUtils.objectToJson(msg);
        }
        return null;
    }
}
