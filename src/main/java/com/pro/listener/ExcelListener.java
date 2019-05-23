package com.pro.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pro.entity.ImportInfo;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {
    //自定义用于暂时存储data，可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();

    //表中有多少记录，就会调用多少次该函数
    public void invoke(Object object, AnalysisContext context) {
        //System.out.println("当前行："+context.getCurrentRowNum());

        //数据过滤：只保留审核已经通过的
        ImportInfo importInfo=(ImportInfo)object;
        if(importInfo.getCurrentProcessStatus().equals("已通过")) {
            //数据存储到list，供批量处理，或后续自己业务逻辑处理。
            datas.add(importInfo);
        }
    }
    private void doSomething(Object object) {
        //1、入库调用接口
    }
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析结束销毁不用的资源
        //datas.clear();
    }
    public List<Object> getDatas() {
        return datas;
    }
    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
