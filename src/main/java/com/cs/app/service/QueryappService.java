package com.cs.app.service;

import com.cs.app.mapper.BillfieldappMapper;
import com.cs.app.model.Billfieldapp;
import com.cs.app.util.SessionUtil;
import com.cs.core.mapper.BillMapper;
import com.cs.core.model.Bill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryappService {
    @Resource
    private BillMapper billMapper;
    @Resource
    private BillfieldappMapper billfieldappMapper;
    /**
     * 获取单据列表页配置规则
     * @param str_billcode
     * @return
     */
    public Map<String,Object> listConfig(String str_billcode){
        Bill bill= billMapper.selectByCode(str_billcode, SessionUtil.getOrgUuid());
        Map<String,Object> map_result=new HashMap<String,Object>();
        map_result.put("query",billfieldappMapper.selectListQueryField(bill.getUuidF()));
        map_result.put("listfields",billfieldappMapper.selectListGridField(bill.getUuidF()));
        return map_result;
    }

    public Map<String,Object> billListData(String str_billcode, Map<String, Object> map_filter) {
        Bill bill= billMapper.selectByCode(str_billcode, SessionUtil.getOrgUuid());
        String str_sql="select ";
        Map<String,Object> map_query=new HashMap<String,Object>();
        Map<String,Object> map_result=new HashMap<String,Object>();
        List<Billfieldapp> list_billfield =billfieldappMapper.selectListGridField(bill.getUuidF());
        for (Billfieldapp billfieldapp : list_billfield) {
            
        }
        return null;
    }
}
