package com.sy.ex.hbase.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author sy
 * @date 2020/2/1
 *
 * 参考DruidDataSourceWrapper
 */
@Data
@Component
@ConfigurationProperties("spring.datasource")
public class DataSourceProperties {

	private Map<String,Source> dataSource;

	private String defaultSource;

	@Data
	public static class  Source {
		private String username;
		private String password;
		private String url;
		private String driverClassName;
		private String type;

		private int minimumIdle;
		private int maximumPoolSize;
		private int idleTimeout;
		private String poolName;
	}







}
