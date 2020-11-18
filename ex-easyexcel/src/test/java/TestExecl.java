import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.sy.sunder.ex.easyexcel.domain.ExeclDataDto;
import com.sy.sunder.ex.easyexcel.service.DataListener;
import com.sy.sunder.ex.easyexcel.service.DateMapper;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/18 22:05
 * @description:
 */
public class TestExecl {

    @Resource
    private DateMapper dateMapper;


    @Test
    public void read(){
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName ="C:/Users/shenyao/Desktop/模板.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
       EasyExcel.read(fileName, ExeclDataDto.class, new DataListener()).sheet().doRead();

       /* // 写法2：
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, ExeclDataDto.class, new DataListener()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }*/

    }

    @Test
    public void write(){
        // 写法1
        String fileName ="C:/Users/shenyao/Desktop/test.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, ExeclDataDto.class).sheet("模板").doWrite(data());

        /*// 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }*/
    }

    public List<ExeclDataDto> data(){
        ArrayList<ExeclDataDto> execlDataDtos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExeclDataDto execlDataDto = new ExeclDataDto();
            execlDataDto.setIbu(i+"");
            execlDataDtos.add(execlDataDto);
        }
        return execlDataDtos;
    }




}
