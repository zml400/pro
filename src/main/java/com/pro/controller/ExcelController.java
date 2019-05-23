package com.pro.controller;

import com.pro.entity.ExportInfo;
import com.pro.listener.ExcelListener;
import com.pro.service.Impl.ExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
public class ExcelController {
    @Autowired
    private ExcelServiceImpl excelService;

    @RequestMapping("/readExcel")
    public List<ExportInfo> readExcel(String filePath)throws IOException{
        ExcelListener listener =excelService.excelRead(filePath);
        List<ExportInfo> exportInfoList = excelService.excelProcess(listener.getDatas());
        List<ExportInfo> list = excelService.excelMerge(exportInfoList);
        return list;
    }
}
