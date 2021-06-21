package com.sy.ex.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: sy
 * @Date: Created by 2021/6/21 10:36
 * @description: Standard标准分片表选择逻辑
 */
@Component
public class StandardTableShardingAlgorithm implements PreciseShardingAlgorithm<String> ,RangeShardingAlgorithm<String>  {

    /**
     *
     * @param collection 分片表集合
     * @param preciseShardingValue 分片依据对象信息
     * @return 真实表名
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        String tableSuffix = this.getTableSuffix(preciseShardingValue.getValue());
        for (String tableName : collection) {
            if (tableName.endsWith(tableSuffix)){
                return tableName;
            }
        }
        return collection.iterator().next();
    }



    private String getTableSuffix(String value){
        if (value==null){
            return "0";
        }
        String substr = value.substring(value.length()-1);
        try {
            int num = Integer.parseInt(substr);
            if (num<2){
                return "0";
            }else if (num<=5){
                return "1";
            }else if (num<=7) {
                return "2";
            }else {
                return "3";
            }
        }catch (Exception e){
            return "0";
        }
    }


    /**
     * 获取数据时判断取哪个库
     * @param collection
     * @param rangeShardingValue
     * @return 范围时 真实表集合
     */

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        System.out.println(rangeShardingValue);
        System.out.println(rangeShardingValue.getValueRange());
        return collection;
    }


}
