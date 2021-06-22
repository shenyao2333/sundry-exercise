package com.sy.ex.sharding.config;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2021/6/21 16:37
 * @description: 不再从SQL 解析中获取值，而是直接通过hintManager.addTableShardingValue("t_order", 1)参数指定
 */
@Component
public class HintTableAlgorithm  implements HintShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection availableTargetNames, HintShardingValue shardingValue) {
        System.out.println("选表条件=" + shardingValue);

        List<String> shardingResult = new ArrayList<>();
        for (Object targetName : availableTargetNames) {
            // hint分片算法的ShardingValue有两种具体类型:
            // ListShardingValue和RangeShardingValue
            // 使用哪种取决于HintManager.addDatabaseShardingValue(String, String, ShardingOperator,...),ShardingOperator的类型
            Collection<Object> values = shardingValue.getValues();
            for (Object value : values) {
                if (targetName.toString().endsWith(value.toString())){
                    shardingResult.add(targetName.toString());
                }
            }
        }
        System.out.println(shardingResult);
        return shardingResult;

    }
}
