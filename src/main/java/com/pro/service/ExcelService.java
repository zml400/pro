package com.pro.service;

import com.pro.entity.ExportInfo;
import com.pro.listener.ExcelListener;

import java.io.IOException;
import java.util.List;


public interface ExcelService {
    public ExcelListener excelRead(String filePath)throws IOException;
    public List<ExportInfo> excelProcess(List<Object> importInfoList);
    public List<ExportInfo> excelMerge(List<ExportInfo> exportInfoList);
}
