package com.pro.service.Impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.pro.entity.ExportInfo;
import com.pro.entity.Result;
import com.pro.service.ExcelExportService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {
    /**
     * 导出 Excel
     * 一个 sheet，带表头
     * @param list 数据 list，每个元素为一个 ExportInfo
     */
//    public String exportExcel(List<ExportInfo> list) {
//        String filePath = "E:\\test.xlsx";
//        try {
//            File file = new File(filePath);
//            if (!file.exists() || file.isDirectory()) {
//                file.createNewFile();
//            }
//            OutputStream out = new FileOutputStream(file);
//            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
//            Sheet sheet = new Sheet(1, 0, new ExportInfo().getClass());
//            //表名字
//            sheet.setSheetName("test");
//            writer.write(list, sheet);
//            writer.finish();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return "success";
//    }
}
