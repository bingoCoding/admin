/**
 * 
 */
package com.bingo.admin.commons.datasource;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.StringUtils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@ComponentScan
public class DataSourceConfig {
	
	/** 日志 */
//	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
	
	/** 数据源属性 */
	@Resource
	private DatasourceProperties datasourceProperties;
	
    /**
     * 获取数据源
     */
	@Bean
    public DataSource dataSource() {
		
		// HIKARI 数据源
		final HikariConfig config = new HikariConfig(datasourceProperties.getDatasourceProerties());
		final HikariDataSource ds = new HikariDataSource(config);
		configDataSource(ds, datasourceProperties);
		
		// mysql 用
		datasourceProperties.initMysqlConfig(config);

		return ds;
    }
	
	/**
	 * 配置数据源
	 * 
	 * @param ds					Hikari数据源
	 * @param datasourceProperties	数据源配置
	 */
	private void configDataSource(HikariDataSource ds, DatasourceProperties datasourceProperties) {
		
		ds.setLeakDetectionThreshold(datasourceProperties.getLeakDetectionThreshold());
		
		ds.setMaximumPoolSize(getIntegerValue(
				datasourceProperties.getDatasourceExtraProerties().get("maximumPoolSize"), 100));
		ds.setMinimumIdle(getIntegerValue(
				datasourceProperties.getDatasourceExtraProerties().get("minimumIdle"), 5));
	}
	
	/**
	 * 获取整数值
	 * 
	 * @param obj			期望值
	 * @param defaultValue	默认值
	 */
	private Integer getIntegerValue(Object obj, int defaultValue) {
		// 未配置则默认一个值
		if (StringUtils.isEmpty(obj)) {
			return defaultValue;
		}
		// 使用配置的值
		try {
			return Integer.parseInt(obj.toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
