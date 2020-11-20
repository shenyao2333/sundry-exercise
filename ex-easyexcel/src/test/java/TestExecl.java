import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.sy.sunder.ex.easyexcel.domain.ExcelDataDto;
import com.sy.sunder.ex.easyexcel.service.DataListener;
import com.sy.sunder.ex.easyexcel.service.DateMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: shenyao
 * @Date: Created by 2020/11/18 22:05
 * @description:
 */
@Slf4j
public class TestExecl {

    @Resource
    private DateMapper dateMapper;


    @Test
    public void read(){
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        String fileName ="C:/Users/shenyao/Desktop/模板.xlsx";
        /**
         * 参数1：文件资源
         * 2：读取excel映射对象，
         * 3：监听器，读取时是一行一行的读取的
         *
         */
       EasyExcel.read(fileName, ExcelDataDto.class, new DataListener()).sheet()
               .headRowNumber(1)//读取头，默认为1行
               .doRead();

    }

    /**
     * 同步读取，不推荐使用，如果数据量大会把数据放到内存里面
     */
    @Test
    public void adf(){
        String fileName ="C:/Users/shenyao/Desktop/模板.xlsx";
        List<ExcelDataDto> list = EasyExcel.read(fileName).head(ExcelDataDto.class).sheet().doReadSync();

        // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().headRowNumber(0).doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            log.info("读取到数据:{}", JSON.toJSONString(data));
        }
    }



    @Test
    public void write(){
        // 写法1
        String fileName ="C:/Users/shenyao/Desktop/test.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, ExcelDataDto.class).sheet("模板").doWrite(data());

    }


    public List<ExcelDataDto> data(){
        ArrayList<ExcelDataDto> excelDataDtos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelDataDto excelDataDto = new ExcelDataDto();
            excelDataDto.setIbu(i+"");
            excelDataDto.setIsBuy("是");
            excelDataDto.setDate(new Date());
            excelDataDtos.add(excelDataDto);
        }
        return excelDataDtos;
    }




}
