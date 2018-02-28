package com.cs.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/app")
@Controller
public class MainController {
    /**
     * 主页
     */
    @RequestMapping("")
    public String main() {
        return "main";
    }
}
