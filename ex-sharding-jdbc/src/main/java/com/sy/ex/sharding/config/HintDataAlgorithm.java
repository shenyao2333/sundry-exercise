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
 * @description:
 */
@Component
public class HintDataAlgorithm implements  HintShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        List<String> shardingResult = new ArrayList<>();
        Collection<String> values = hintShardingValue.getValues();
        for (Object value : values) {
            for (Object value2 : collection) {
                if (value2.toString().endsWith(value.toString())){
                    shardingResult.add(value2.toString());
                }
            }
        }
        return shardingResult;
    }


}
