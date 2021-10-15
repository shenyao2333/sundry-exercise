package com.sy.ex.hbase.config;

import com.alibaba.fastjson.JSON;
import com.sy.ex.hbase.datasource.DataSourceProperties;
import com.sy.ex.hbase.datasource.DynamicDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @author shenyao
 * @date 2019-03-31
 *
 * 动态数据源切换配置
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DynamicDataSourceConfig {
	private final Map<Object, Object> dataSourceMap = new HashMap<>(8);
	private final DataSourceProperties dataSourceProperties;



	@Bean("dynamicDataSource")
	public DynamicDataSource dynamicDataSource() {
		Map<String, DataSourceProperties.Source> dataSourceList = dataSourceProperties.getDataSource();
		Set<String> keySet = dataSourceList.keySet();

		keySet.forEach(key ->{
			DataSourceProperties.Source source = dataSourceList.get(key);
			System.out.println(JSON.toJSONString(dataSourceProperties));
			HikariConfig hikariConfig = new HikariConfig();

			hikariConfig.setMaximumPoolSize(source.getMaximumPoolSize());
			hikariConfig.setMinimumIdle(source.getMinimumIdle());
			hikariConfig.setPoolName(source.getPoolName());
			hikariConfig.setIdleTimeout(source.getIdleTimeout());
			hikariConfig.setJdbcUrl(source.getUrl());
			hikariConfig.setDriverClassName(source.getDriverClassName());
			hikariConfig.setUsername(source.getUsername());
			hikariConfig.setPassword(source.getPassword());
			HikariDataSource dds = new HikariDataSource(hikariConfig);
			dataSourceMap.put(key,dds);
		});
		HikariDataSource hikariDataSource =(HikariDataSource) dataSourceMap.get(dataSourceProperties.getDefaultSource());
		return new DynamicDataSource(hikariDataSource,dataSourceMap);

	}



}
