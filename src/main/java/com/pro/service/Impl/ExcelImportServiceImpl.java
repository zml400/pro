package com.pro.service.Impl;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.pro.entity.ExportInfo;
import com.pro.entity.ImportInfo;
import com.pro.entity.Result;
import com.pro.listener.ExcelListener;
import com.pro.service.ExcelExportService;
import com.pro.service.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service

public class ExcelImportServiceImpl implements ExcelImportService {

//    @Autowired
//    private ExcelExportService exportService;
    /**
     * 完成从excel表中读取数据到输入数据流
     * 调用监听器将所有数据以对象的形式存储到监听器的List数组中
     * @param inputStream 文件输入流
     * @return listener
     */
    public ExcelListener excelRead(InputStream inputStream) throws IOException {
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
     * @return Result
     */
    public Result excelProcess(List<Object> importInfoList,int pageNum,int pageSize){
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

        List<ExportInfo> list= excelMerge( exportInfoList);
        //将数据写入本地
        //exportService.exportExcel(list);
        return divPage(list,pageNum,pageSize);
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
     * @return List<ExportInfo>
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

    /**
     * 分页处理
     * @param list 结果数据数组
     * @param pageNum 第几页
     * @param pageSize 每页的记录数
     * @return Result
     */
    public Result divPage(List<ExportInfo> list, int pageNum, int pageSize){
        //分页处理

        Result result = new Result();
        //若pagesize=-1，则不分页
        if(pageNum==1&&pageSize==-1){
            result.setCurrentPage(pageNum);
            result.setMessage("请求成功");
            result.setCode(0);
            result.setPagesTotal(1);
            result.setObject(list);
            return result;
        }else {
            //页数
            int pagestotal = list.size() / pageSize + (list.size() % pageSize == 0 ? 0 : 1);
            if (pageNum > pagestotal || pageNum < 1) {
                result.setCode(1);
                result.setMessage("你输入的页数不在数据范围之内，请重新输入");
            } else {
                result.setCode(0);
                result.setMessage("请求成功");
                result.setCurrentPage(pageNum);
                result.setPagesTotal(pagestotal);
                int fromIndex = (pageNum - 1) * pageSize;
                result.setObject(list.subList(fromIndex, fromIndex + pageSize));
            }
        }
        return result;
    }
}
