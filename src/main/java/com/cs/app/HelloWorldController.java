package com.cs.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
@Controller
public class HelloWorldController {
    @RequestMapping("")
    public String hello(ModelMap model){
        System.out.println("helloworld 执行了");
        model.addAttribute("name","helloworld 执行了");
       return "index.jsp";
    }
}
