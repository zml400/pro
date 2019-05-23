package com.pro.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

public class ExportInfo implements Serializable {
    @ExcelProperty(value = "申请人",index=1)
    private String applyPerson;
    @ExcelProperty(value = "加班月份",index=2)
    private int overtimeMonth;
    @ExcelProperty(value = "加班天数",index=3)
    private double overtimeDays;

    public String getApplyPerson() {
        return applyPerson;
    }
    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }
    public int getOvertimeMonth() {
        return overtimeMonth;
    }
    public void setOvertimeMonth(int overtimeMonth) {
        this.overtimeMonth = overtimeMonth;
    }
    public double getOvertimeDays() {
        return overtimeDays;
    }
    public void setOvertimeDays(double overtimeDays) {
        this.overtimeDays = overtimeDays;
    }
    @Override
    public String toString() {
        return "ExportInfo{" +
                ", applyPerson='" + applyPerson + '\'' +
                ", overtimeMonth=" + overtimeMonth +
                ", overtimeDays=" + overtimeDays +
                '}';
    }
}
