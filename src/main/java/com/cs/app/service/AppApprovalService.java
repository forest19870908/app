package com.cs.app.service;

import com.cs.app.dao.AppApprovalDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class AppApprovalService {
    @Resource
    private AppApprovalDao appApprovalDao;
    /**
     * 待办信息
     * @return
     */
    public Map<String, Object> waitdone(){
        return appApprovalDao.waitdone();
    }
    /**
     * 已办信息
     * @return
     */
    public Map<String, Object> done(){
        return appApprovalDao.done();
    }

    /**
     * 审批列表
     * @param type
     * @param code
     * @return
     */
    public Map<String,Object> app_list(Integer type, String code) {
        return appApprovalDao.app_list(type,code);
    }

    public Map<String,Object> app_detail(String code, String uuid) {
        return appApprovalDao.app_detail(code,uuid);
    }
}
