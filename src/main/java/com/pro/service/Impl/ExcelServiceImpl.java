package com.pro.service.Impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.pro.entity.ExportInfo;
import com.pro.entity.ImportInfo;
import com.pro.listener.ExcelListener;
import com.pro.service.ExcelService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service

public class ExcelServiceImpl implements ExcelService {
    /**
     * 完成从excel表中读取数据到输入数据流
     * 调用监听器将所有数据以对象的形式存储到监听器的List数组中
     * @param filePath 文件路径
     * @return listener
     */
    public ExcelListener excelRead(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(new File(filePath));
        ExcelListener listener = new ExcelListener();
        try {
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
            excelReader.read(new Sheet(1, 2, ImportInfo.class));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listener;
    }

    /**
     * 提取原始数据，处理后封装到导出对象中
     * @param importInfoList 原始数据
     * @return exmportInfoList
     */
    public List<ExportInfo> excelProcess(List<Object> importInfoList){
        List<ExportInfo> exportInfoList=new ArrayList<ExportInfo>();
        for (Object object:importInfoList){
            ImportInfo importInfo=(ImportInfo) object;
            ExportInfo exportInfo= new ExportInfo();
            exportInfo.setApplyPerson(importInfo.getApplyPerson());
            //处理加班时长字符串
            String times=importInfo.getOvertimeDuration();
            int hours=(int)Double.parseDouble(times.substring(0,times.indexOf("小时")));
            exportInfo.setOvertimeDays((hours/4)*0.5);
            //处理开始时间字符串
            String beginTime=importInfo.getBeginTime();
            String[] strs=beginTime.split("/");
            exportInfo.setOvertimeMonth(Integer.parseInt(strs[1]));
            exportInfoList.add(exportInfo);
        }
        return exportInfoList;
    }

    /**
     * 内部类形式实现排序接口Comparator中的compare方法
     */
    Comparator<ExportInfo> comparator = new Comparator<ExportInfo>() {
        public int compare(ExportInfo e1, ExportInfo e2) {
            // 先排月份
            if (e1.getOvertimeMonth() != e2.getOvertimeMonth()) {
                return e1.getOvertimeMonth() -e2.getOvertimeMonth();
            } else {
                // 按加班时长排序
                return (int) ((e2.getOvertimeDays()-e1.getOvertimeDays())*2);
            }
        }
    };
    /**
     * 将同一个人当月的数据进行合并（时长相加），并按照月份和时长进行排序
     * @param exportInfoList 处理后的数据（未合并）
     * @return
     */
    public List<ExportInfo> excelMerge(List<ExportInfo> exportInfoList){
        List<ExportInfo> list = new ArrayList<ExportInfo>();
        for(ExportInfo e:exportInfoList){
            if(list.size()==0){
                list.add(e);
            }else {
                boolean flag=true;
                for(ExportInfo e1:list){
                    if(e.getApplyPerson().equals(e1.getApplyPerson())&&
                            e.getOvertimeMonth()==e1.getOvertimeMonth()){
                        e1.setOvertimeDays(e.getOvertimeDays()+e1.getOvertimeDays());
                        flag=false;
                        break;
                    }
                }
                if(flag==true){
                    list.add(e);
                }
            }
        }
        Collections.sort(list,comparator);
        return list;
    }
}
