package com.sy.ex.guava.io;

import com.google.common.io.ByteStreams;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: sy
 * @Date: Created by 2022-01-10-21:54
 * @description:
 */
public class Test1 {



    public static void main(String[] args) {
        File file = new File("sdfsdf");
        /**
         * 1.xia zai
         */
        //        String templateFileName ="https://drinkfile.biqr.cn/upload/file/2022/01/05/8d1881dcc15d45d0a4301bde63e342ce.xlsx";
        //        InputStream inputStream = new URL(templateFileName).openStream();
        //
        //        String filename =  UUID.randomUUID().toString()+"AR日报.xlsx";
        //        String downloadPath = Global.getDownloadPath() + filename;
        //        File desc = new File(downloadPath);
        //        if (!desc.getParentFile().exists()) {
        //            desc.getParentFile().mkdirs();
        //        }
        //
        //        ExcelWriter excelWriter = EasyExcel.write(downloadPath).withTemplate(inputStream).build();
        //        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        //
        //        excelWriter.fill(deptStat,  writeSheet);
        //        excelWriter.fill(scanDataMap,  writeSheet);
        //        excelWriter.finish();


        try {
            String filePath =
            "D:/masterSpring/chapter23/webapp/WEB-INF/classes/conf/file1.txt";
             // ① 使用系统文件路径方式加载文件
            Resource res1 = new FileSystemResource(filePath);
        // ② 使用类路径方式加载文件
            Resource res2 = new ClassPathResource("conf/file1.txt");
            InputStream ins1 = res1.getInputStream();
            InputStream ins2 = res2.getInputStream();
            System.out.println("res1:"+res1.getFilename());
            System.out.println("res2:"+res2.getFilename());
        } catch (IOException e) {
            e.printStackTrace();
        }



}

}
