package com.cs.app.model;

import java.util.Date;

import com.cs.core.Annotation.Column;
public class Tokenlog {
    /** 主键，唯一标识 */
    @Column(name="主键，唯一标识",required="true")
    private String uuidF;

    /** 自增，用于排序 */
    @Column(name="自增，用于排序",required="true")
    private Integer idF;

    /** 编码 */
    @Column(name="编码",required="true")
    private String codeF;

    /** 名称 */
    @Column(name="名称",required="true")
    private String nameF;

    /** 版本号 */
    @Column(name="版本号",required="true")
    private Integer versionF;

    /** 单据状态 0未审核，1已提交，2审核中，3审核通过，4已删除，5作废 */
    @Column(name="单据状态 0未审核，1已提交，2审核中，3审核通过，4已删除，5作废",required="true")
    private Integer statusF;

    /** 制档人 */
    @Column(name="制档人",required="true")
    private String userCreatorF;

    /**  */
    @Column(name="",required="true")
    private Date createtimeF;

    /** 关联组织id */
    @Column(name="关联组织id",required="true")
    private String orgOrgF;

    /** 微信账号 */
    @Column(name="微信账号",required="true")
    private String appuseridF;

    public String getUuidF() {
        return uuidF;
    }

    public void setUuidF(String uuidF) {
        this.uuidF = uuidF == null ? null : uuidF.trim();
    }

    public Integer getIdF() {
        return idF;
    }

    public void setIdF(Integer idF) {
        this.idF = idF;
    }

    public String getCodeF() {
        return codeF;
    }

    public void setCodeF(String codeF) {
        this.codeF = codeF == null ? null : codeF.trim();
    }

    public String getNameF() {
        return nameF;
    }

    public void setNameF(String nameF) {
        this.nameF = nameF == null ? null : nameF.trim();
    }

    public Integer getVersionF() {
        return versionF;
    }

    public void setVersionF(Integer versionF) {
        this.versionF = versionF;
    }

    public Integer getStatusF() {
        return statusF;
    }

    public void setStatusF(Integer statusF) {
        this.statusF = statusF;
    }

    public String getUserCreatorF() {
        return userCreatorF;
    }

    public void setUserCreatorF(String userCreatorF) {
        this.userCreatorF = userCreatorF == null ? null : userCreatorF.trim();
    }

    public Date getCreatetimeF() {
        return createtimeF;
    }

    public void setCreatetimeF(Date createtimeF) {
        this.createtimeF = createtimeF;
    }

    public String getOrgOrgF() {
        return orgOrgF;
    }

    public void setOrgOrgF(String orgOrgF) {
        this.orgOrgF = orgOrgF == null ? null : orgOrgF.trim();
    }

    public String getAppuseridF() {
        return appuseridF;
    }

    public void setAppuseridF(String appuseridF) {
        this.appuseridF = appuseridF == null ? null : appuseridF.trim();
    }
}