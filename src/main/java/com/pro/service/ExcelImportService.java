package com.pro.service;

import com.pro.entity.ExportInfo;
import com.pro.entity.Result;
import com.pro.listener.ExcelListener;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface ExcelImportService {
    public ExcelListener excelRead(InputStream inputStream)throws IOException;
    public Result excelProcess(List<Object> importInfoList,int pageNum, int pageSize);
    public List<ExportInfo> excelMerge(List<ExportInfo> exportInfoList);
    public Result divPage(List<ExportInfo> exportInfoList, int pageNum, int pageSize);
}
