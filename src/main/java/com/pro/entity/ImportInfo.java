package com.pro.entity;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.io.Serializable;

public class ImportInfo extends BaseRowModel implements Serializable {

    @ExcelProperty(index = 0)
    //审批编号
    private String processId;

    @ExcelProperty(index = 1)
    //提交时间
    private String subTime;

    @ExcelProperty(index = 2)
    //申请人
    private String applyPerson;

    @ExcelProperty(index = 3)
    //申请人部门
    private String applyDepartment;

    @ExcelProperty(index = 4)
    //申请人账号
    private String getApplyPersonAccount;

    @ExcelProperty(index = 5)
    //加班事由
    private String overtimeReason;

    @ExcelProperty(index = 6)
    //开始时间
    private String beginTime;

    @ExcelProperty(index = 7)
    //结束时间
    private String endTime;

    @ExcelProperty(index = 8)
    //加班时长
    private String overtimeDuration;

    @ExcelProperty(index = 9)
    //当前审批状态
    private String currentProcessStatus;

    @ExcelProperty(index = 10)
    //审批人
    private String processPerson;

    @ExcelProperty(index = 11)
    //抄送人
    private String copyPerson;

    @ExcelProperty(index = 12)
    //审批流程
    private String processFlow;

    public String getProcessId() {
        return processId;
    }
    public void setProcessId(String processId) {
        this.processId = processId;
    }
    public String getSubTime() {
        return subTime;
    }
    public void setSubTime(String subTime) {
        this.subTime = subTime;
    }
    public String getApplyPerson() {
        return applyPerson;
    }
    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }
    public String getApplyDepartment() {
        return applyDepartment;
    }
    public void setApplyDepartment(String applyDepartment) {
        this.applyDepartment = applyDepartment;
    }
    public String getGetApplyPersonAccount() {
        return getApplyPersonAccount;
    }
    public void setGetApplyPersonAccount(String getApplyPersonAccount) {
        this.getApplyPersonAccount = getApplyPersonAccount;
    }
    public String getOvertimeReason() {
        return overtimeReason;
    }
    public void setOvertimeReason(String overtimeReason) {
        this.overtimeReason = overtimeReason;
    }
    public String getBeginTime() {
        return beginTime;
    }
    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getOvertimeDuration() {
        return overtimeDuration;
    }
    public void setOvertimeDuration(String overtimeDuration) {
        this.overtimeDuration = overtimeDuration;
    }
    public String getCurrentProcessStatus() {
        return currentProcessStatus;
    }
    public void setCurrentProcessStatus(String currentProcessStatus) {
        this.currentProcessStatus = currentProcessStatus;
    }
    public String getProcessPerson() {
        return processPerson;
    }
    public void setProcessPerson(String processPerson) {
        this.processPerson = processPerson;
    }
    public String getCopyPerson() {
        return copyPerson;
    }
    public void setCopyPerson(String copyPerson) {
        this.copyPerson = copyPerson;
    }
    public String getProcessFlow() {
        return processFlow;
    }
    public void setProcessFlow(String processFlow) {
        this.processFlow = processFlow;
    }
    @Override
    public String toString() {
        return "ImportInfo{" +
                "processId='" + processId + '\'' +
                ", subTime='" + subTime + '\'' +
                ", applyPerson='" + applyPerson + '\'' +
                ", applyDepartment='" + applyDepartment + '\'' +
                ", getApplyPersonAccount='" + getApplyPersonAccount + '\'' +
                ", overtimeReason='" + overtimeReason + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", overtimeDuration='" + overtimeDuration + '\'' +
                ", currentProcessStatus='" + currentProcessStatus + '\'' +
                ", processPerson='" + processPerson + '\'' +
                ", copyPerson='" + copyPerson + '\'' +
                ", processFlow='" + processFlow + '\'' +
                '}';
    }
}
