package com.pro.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.pro.entity.ImportInfo;
import com.pro.listener.ExcelListener;
import jdk.internal.util.xml.impl.Input;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelControllerTest {
    @Test
    public void testRead() {

        try {
            InputStream inputStream = new FileInputStream(new File("C:\\Users\\93543\\Desktop\\审批记录0522.xlsx"));
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, ImportInfo.class, listener);
            excelReader.read();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {

        }
    }
}