package com.cs.app.controller;

import com.cs.app.service.AppApprovalService;
import com.cs.core.util.excel.DgformatterUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RequestMapping("/app/query")
@Controller
public class AppQueryController {
    @Resource
    private AppApprovalService appApprovalService;

    /**
     * 审批列表
     * @param type 0待办列表 1已办列表
     * @param code billcode
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(Integer type,String code) {
        Map<String, Object> map=appApprovalService.app_list(type,code);
        List<Map<String,Object>> list_value= (List<Map<String, Object>>) map.get("list_value");
        for (int i = 0; i < list_value.size(); i++) {
            Map<String, Object> value= list_value.get(i);
            for (String key:  value.keySet()) {
                if("status_f".equals(key)){
                    value.put(key, DgformatterUtil.formatterstatus(Integer.valueOf(value.get(key).toString())));
                }
            }
        }
        return map;
    }

    /**
     * 审批详情
     * @param code billcode
     * @param uuid
     * @return
     */
    @RequestMapping("/detail")
    @ResponseBody
    public Map<String, Object> detail(String code,String uuid) {
        Map<String, Object> map=appApprovalService.app_detail(code,uuid);
        return map;
    }
}
