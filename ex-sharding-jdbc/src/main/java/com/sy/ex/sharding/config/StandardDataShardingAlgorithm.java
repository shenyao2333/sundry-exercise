package com.sy.ex.sharding.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: sy
 * @Date: Created by 2021/6/21 10:36
 * @description:  Standard标准分片数据库选择逻辑
 */
@Component
public class StandardDataShardingAlgorithm implements PreciseShardingAlgorithm<Integer> ,RangeShardingAlgorithm<Integer>  {


    private final Integer  boundariesAge = 18;

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        Integer value = preciseShardingValue.getValue();
        String s = this.getTb(value);
        for (String tb : collection) {
            if (tb.endsWith(s)){
                return tb;
            }
        }
        return collection.iterator().next();
    }


    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        Set<String> restSet = new HashSet<>(4);
        Range<Integer> valueRange = rangeShardingValue.getValueRange();
        if ( valueRange.hasUpperBound()){
            Integer upper = valueRange.upperEndpoint();
            if (upper<=this.boundariesAge){
                restSet.add("tb0");
            }else {
                restSet.add("tb1");
            }
        }else {
            restSet.add("tb1");
        }
        if ( valueRange.hasLowerBound()){
            Integer lower = valueRange.lowerEndpoint();
            if (lower>=this.boundariesAge){
                restSet.add("tb1");
            }else {
                restSet.add("tb0");
            }
        }else {
            restSet.add("tb0");
        }
        return restSet;
    }

    public String getTb(Integer value){
        String s = "tb1";
        if (value <= this.boundariesAge){
            s = "tb0";
        }
        return s;
    }


}
