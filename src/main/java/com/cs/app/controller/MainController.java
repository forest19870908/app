package com.cs.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/app")
@Controller
public class MainController {

    /**
     * 移动审批入口
     * @param state 由微信企业id，微信企业应用id，微信应用密钥,组织id组成，逗号隔开
     * @param code 获取身份信息的code
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request, HttpServletResponse response, String code,String state) {
        return "main";
    }
}
