package com.pro.controller;

import com.pro.entity.ExportInfo;
import com.pro.entity.Result;
import com.pro.listener.ExcelListener;
import com.pro.service.ExcelExportService;
import com.pro.service.Impl.ExcelImportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExcelController {
    @Autowired
    private ExcelImportServiceImpl excelService;

    @RequestMapping("/readExcel")
    public Result readExcel(@RequestParam(value = "pageNum",defaultValue="1",required = true)int pageNum,
                            @RequestParam(value="pageSize",defaultValue="-1",required = true)int pageSize,
                            @RequestParam("file") MultipartFile file)throws IOException{
        //传入文件输入流，获得数据监听器
        ExcelListener listener =excelService.excelRead(file.getInputStream());
        return excelService.excelProcess(listener.getDatas(),pageNum,pageSize);
    }
}
