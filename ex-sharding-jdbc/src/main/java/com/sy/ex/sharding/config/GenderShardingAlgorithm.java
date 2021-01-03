package com.sy.ex.sharding.config;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;

/**
 * @Author: shenyao
 * @Date: Created by 2021/1/3 18:54
 * @description:
 */
public class GenderShardingAlgorithm  implements PreciseShardingAlgorithm<Integer>, RangeShardingAlgorithm<Integer> {


    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data source or table's name
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        String databaseName = collection.stream().findFirst().get();

        for (String dbName : collection) {
            if (dbName.endsWith(genderToTableSuffix(preciseShardingValue.getValue()))) {
                databaseName = dbName;
            }
        }

        return databaseName;
    }

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        return null;
    }


    /**
     * 字段与分库的映射关系
     *
     * @param gender
     * @return
     */
    private String genderToTableSuffix(Integer gender) {
        return gender%2==0? "0" : "1";
    }


}
