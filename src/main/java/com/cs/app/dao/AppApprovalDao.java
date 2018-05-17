package com.cs.app.dao;

import com.cs.app.util.SessionUtil;
import com.cs.common.util.DbUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppApprovalDao {
    @Resource
    private DbUtil dbUtil;

    /**
     * 待办信息
     * @return
     */
    public Map<String, Object> waitdone(){
        Map<String, Object> map=new HashMap<String, Object>();
        StringBuffer sql=new StringBuffer("SELECT b.code_f as code,b.name_f as title,count(b.code_f) as number from tbl_billtask t,tbl_bill b where t.bill_bill_f=b.uuid_f and t.user_approval_f=? group by b.code_f,b.name_f");
        List<Map<String,Object>> list=dbUtil.getJdbcTemplate().queryForList(sql.toString(), new Object[] {SessionUtil.getUserUuid()});
        map.put("waitdonelist",list);
        StringBuffer count_sql=new StringBuffer("SELECT count(*) as number from tbl_billtask where user_approval_f=?");
        List<Map<String,Object>> numberlist=dbUtil.getJdbcTemplate().queryForList(count_sql.toString(), new Object[] {SessionUtil.getUserUuid()});
        if(numberlist.size()==1) {
            map.put("waitdonenumber", numberlist.get(0).get("number"));
        }
        return map;
    }
    /**
     * 已办信息
     * @return
     */
    public Map<String, Object> done(){
        Map<String, Object> map=new HashMap<String, Object>();
        StringBuffer sql=new StringBuffer("SELECT b.code_f as code,b.name_f as title from tbl_billapprovalresult t,tbl_bill b where t.bill_bill_f=b.uuid_f and t.user_approval_f=? group by b.code_f,b.name_f");
        List<Map<String,Object>> list=dbUtil.getJdbcTemplate().queryForList(sql.toString(), new Object[] {SessionUtil.getUserUuid()});
        map.put("waitdonelist",list);
        return map;
    }
    /**
     * 审批列表
     * @param type
     * @param code
     * @return
     */
    public Map<String,Object> app_list(Integer type, String code) {
        Map<String,Object> map=null;
        switch (type){
            case 0 : map=wait_app_list(code);break;
            case 1 : map=done_app_list(code);break;
        }
        return map;
    }

    /**
     * 列表属性名
     * @return
     */
    public String getsqlnamelist(){
        return "SELECT\n" +
                "\t'uuid_f' AS uuid_f,\n" +
                "\t'单号' AS code_f,\n" +
                "\t'状态' AS status_f,\n" +
                "\t'抬头' AS p_company_f,\n" +
                "\t'制单人' AS p_creator_f,\n" +
                "\t'客户' AS p_customer_f,\n" +
                "\t'作价方式' AS p_pricingmode_f,\n" +
                "\t'商品类别' AS p_goodcat_f,\n" +
                "\t'数量' AS contractqty_f";
    }

    /**
     * 列表属性值
     * @return
     */
    public String getsqlvaluelist(){
        return "SELECT\n" +
                "\tsc.uuid_f,\n" +
                "\tsc.code_f,\n" +
                "\tsc.status_f,\n" +
                "\tc.name_f as p_company_f,\n" +
                "\tu.name_f as p_creator_f,\n" +
                "\tcu.name_f as p_customer_f,\n" +
                "\tpricingmode.name_f as p_pricingmode_f,\n" +
                "\tcategory.name_f as p_goodcat_f,\n" +
                "\tcontractqty_f\n" +
                "FROM\n" +
                "\ttbl_schead sc\n" +
                "left join tbl_company c on sc.company_company_f=c.uuid_f\n" +
                "left join tbl_customer cu on sc.customer_customer_f=cu.uuid_f\n" +
                "left join tbl_user u on sc.user_creator_f=u.uuid_f\n" +
                "left join tbl_pricingmode pricingmode on sc.pricingmode_pricingmode_f=pricingmode.uuid_f\n" +
                "left join tbl_category category on sc.category_goodcat_f=category.uuid_f\n";
    }
    /**
     * 待审批列表
     * @param code
     * @return
     */
    public Map<String,Object> wait_app_list(String code) {
        Map map=new HashMap();
        StringBuffer sql_name=new StringBuffer(getsqlnamelist());
        List<Map<String,Object>> list_name=dbUtil.getJdbcTemplate().queryForList(sql_name.toString());
        map.put("list_name",list_name);
        StringBuffer sql=new StringBuffer( getsqlvaluelist()+
                "WHERE\n" +
                "\tsc.uuid_f IN (\n" +
                "\t\tSELECT\n" +
                "\t\t\tt.billuuid_f\n" +
                "\t\tFROM\n" +
                "\t\t\ttbl_billtask t,\n" +
                "\t\t\ttbl_bill b\n" +
                "\t\tWHERE\n" +
                "\t\t\tt.bill_bill_f = b.uuid_f\n" +
                "\t\tAND t.user_approval_f =?\n" +
                "\t\tAND b.code_f =?\n" +
                "\t)");
        List<Map<String,Object>> list_value=dbUtil.getJdbcTemplate().queryForList(sql.toString(), new Object[] {SessionUtil.getUserUuid(),code});
        map.put("list_value",list_value);
        return map;
    }
    /**
     * 已审批列表
     * @param code
     * @return
     */
    public Map<String,Object> done_app_list(String code) {
        Map map=new HashMap();
        StringBuffer sql_name=new StringBuffer(getsqlnamelist());
        List<Map<String,Object>> list_name=dbUtil.getJdbcTemplate().queryForList(sql_name.toString());
        map.put("list_name",list_name);
        StringBuffer sql=new StringBuffer( getsqlvaluelist()+
                "WHERE\n" +
                "\tsc.uuid_f IN (\n" +
                "\t\tSELECT\n" +
                "\t\t\tt.billuuid_f\n" +
                "\t\tFROM\n" +
                "\t\t\ttbl_billapprovalresult t,\n" +
                "\t\t\ttbl_bill b\n" +
                "\t\tWHERE\n" +
                "\t\t\tt.bill_bill_f = b.uuid_f\n" +
                "\t\tAND t.user_approval_f =?\n" +
                "\t\tAND b.code_f =?\n" +
                "\t)");
        List<Map<String,Object>> list_value=dbUtil.getJdbcTemplate().queryForList(sql.toString(), new Object[] {SessionUtil.getUserUuid(),code});
        map.put("list_value",list_value);
        return map;
    }
    /**
     * 审批详情
     * @param code
     * @param uuid
     * @return
     */
    public Map<String,Object> app_detail(String code, String uuid) {
        Map map=new HashMap();
        StringBuffer sql_name=new StringBuffer(getsqlnamelist());
        List<Map<String,Object>> detail_name=dbUtil.getJdbcTemplate().queryForList(sql_name.toString());
        map.put("detail_name",detail_name);
        StringBuffer sql=new StringBuffer( getsqlvaluelist()+
                "WHERE\n" +
                " sc.uuid_f=?");
        List<Map<String,Object>> detail_value=dbUtil.getJdbcTemplate().queryForList(sql.toString(), new Object[] {uuid});
        map.put("detail_value",detail_value);
        StringBuffer sql_list_name=new StringBuffer("SELECT '商品名称' as name_f,'数量' as qty_f,'基价'as baseprice_f,'升贴水' as bwd_f,'备注' as remark_f");
        List<Map<String,Object>> detail_list_name=dbUtil.getJdbcTemplate().queryForList(sql_list_name.toString());
        map.put("detail_list_name",detail_list_name);
        StringBuffer sql_list_value=new StringBuffer("SELECT good.name_f,sc.qty_f,sc.baseprice_f,sc.bwd_f,sc.remark_f from tbl_scdetail sc,tbl_good good where sc.good_good_f=good.uuid_f and sc.mainuuid_f=?");
        List<Map<String,Object>> detail_list_value=dbUtil.getJdbcTemplate().queryForList(sql_list_value.toString(), new Object[] {uuid});
        map.put("detail_list_value",detail_list_value);
        return map;
    }
}
