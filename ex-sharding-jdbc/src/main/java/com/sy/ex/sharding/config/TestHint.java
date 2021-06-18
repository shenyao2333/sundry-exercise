package com.sy.ex.sharding.config;

import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: sy
 * @Date: Created by 2021/6/18 17:26
 * @description:
 */
@Component
public class TestHint implements HintShardingAlgorithm {




    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        String s = hintShardingValue.getLogicTableName();
        for (Object value : hintShardingValue.getValues()) {
            Long aLong = Long.valueOf(String.valueOf(value));
            String tablename = s + aLong;
            result.add(tablename);
        }
        System.out.println(result);
        return result;


    }


}
