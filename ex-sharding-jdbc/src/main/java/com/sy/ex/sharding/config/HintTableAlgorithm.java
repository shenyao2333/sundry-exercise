package com.sy.ex.sharding.config;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @Author: sy
 * @Date: Created by 2021/6/21 16:37
 * @description:
 */
@Component
public class HintTableAlgorithm  implements HintShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection collection, HintShardingValue hintShardingValue) {
        System.out.println(" hintShardingValue "+ hintShardingValue);
        System.out.println("collection  "+ collection);
        return null;
    }
}
