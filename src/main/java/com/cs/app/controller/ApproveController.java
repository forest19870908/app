package com.cs.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/app/approve")
@Controller
public class ApproveController {
    @RequestMapping("/list")
    public String list(ModelMap model){
        System.out.println("ApproveController 执行了");
        model.addAttribute("name","helloworld 执行了");
       return "approve_list";
    }
}
