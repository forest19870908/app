package com.cs.app.controller;

import com.cs.app.service.AppApprovalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@RequestMapping("/app/index")
@Controller
public class IndexCountroller {
    @Resource
    private AppApprovalService appApprovalService;
    @RequestMapping("")
    public String index(ModelMap model) {
        Map<String, Object> mapall = appApprovalService.waitdone();
        model.addAttribute("waitdonelist",mapall.get("waitdonelist"));
        model.addAttribute("waitdonenumber",mapall.get("waitdonenumber"));//待办数量
        return "index";
    }

    /**
     * 待办
     * @return
     */
    @RequestMapping("waitdone")
    @ResponseBody
    public Map<String, Object> waitdone() {
        return appApprovalService.waitdone();
    }

    /**
     * 已办
     * @return
     */
    @RequestMapping("done")
    @ResponseBody
    public Map<String, Object> done() {
        return appApprovalService.done();
    }
}
