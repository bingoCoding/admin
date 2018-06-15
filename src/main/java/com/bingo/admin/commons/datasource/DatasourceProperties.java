/**
 * 
 */
package com.bingo.admin.commons.datasource;

import java.util.Properties;

import com.bingo.admin.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;

//@Component
//@ConfigurationProperties(prefix = "spring.dataSource")
public class DatasourceProperties {

	/** 数据源 */
	private String dataSourceClassName;
	
	/** 数据库链接URL */
	private String url;
	
	/** 驱动类 */
	private String driverClassName;
	
	/** 用户名 */
	private String user;
	
	/** 用户名 */
	private String userName;
	
	/** 密码 */
	private String password;
	
	/** 数据库名 */
	private String databaseName;
	
	/** 服务器名 */
	private String serverName;
	
	/** 端口号 */
	private String portNumber;
	
	/** 连接测试查询 */
	private String connectionTestQuery;
	
	/** 连接泄露检测阈值，不常用 */
	private int leakDetectionThreshold;
	
	/** 连接池最大连接数 */
	private String maximumPoolSize;
	
	/** 连接池空闲连接数 */
	private String minimumIdle;
	
	/** 预处理缓存大小 */
	private String prepStmtCacheSize;
	
	/** 预处理缓存SQL大小 */
	private String prepStmtCacheSqlLimit; 
	
	
	/**
	 * 获取数据源Proerties
	 */
	public Properties getDatasourceProerties() {
		Properties props = new Properties();
		// 配置属性
		if (StringUtils.isNotEmpty(getConnectionTestQuery())) {
			props.setProperty("connectionTestQuery", getConnectionTestQuery());
		}
		if (StringUtils.isEmpty(getDataSourceClassName())) {
			throw new RuntimeException("miss dataSourceClassName");
		} else {
			props.setProperty("dataSourceClassName", getDataSourceClassName());
		}
		if (!StringUtils.isEmpty(getUser())) {
			props.setProperty("dataSource.user", getUser());
		}
		if (!StringUtils.isEmpty(getPassword())) {
			props.setProperty("dataSource.password", getPassword());
		}
		if (!StringUtils.isEmpty(getServerName())) {
			props.setProperty("dataSource.serverName", getServerName());
		}
		if (!StringUtils.isEmpty(getDatabaseName())) {
			props.setProperty("dataSource.databaseName", getDatabaseName());
		}
		// driverClassName
		if (!StringUtils.isEmpty(getDriverClassName())) {
			props.setProperty("driverClassName", getDriverClassName());
		}
		
		
		// portNumber
		if (!StringUtils.isEmpty(getPortNumber())) {
			props.setProperty("dataSource.portNumber", getPortNumber());
		}
		// 数据源属性
		Properties dataSourceProperties = new Properties();
		props.put("dataSourceProperties", dataSourceProperties);
		
		// url
		if (!StringUtils.isEmpty(getUrl())) {
			dataSourceProperties.setProperty("url", getUrl());
		}
		
		return props;
	}
	
	/**
	 * 获取数据源额外配置Proerties
	 */
	public Properties getDatasourceExtraProerties() {
		Properties prop = new Properties();
		// 最大连接数
		if (!StringUtils.isEmpty(getMaximumPoolSize())) {
			prop.setProperty("maximumPoolSize", getMaximumPoolSize());
		}
		// 最小空闲数
		if (!StringUtils.isEmpty(getMinimumIdle())) {
			prop.setProperty("minimumIdle", getMinimumIdle());
		}
		return prop;
	}
	
	/**
	 * 初始化mysql
	 */
	public void initMysqlConfig(HikariConfig config) {
		if ((StringUtils.isNotEmpty(getDriverClassName()) && getDriverClassName().toLowerCase().contains("mysql"))
				|| (StringUtils.isNotEmpty(getDataSourceClassName()) && getDataSourceClassName().toLowerCase().contains("mysql"))) {
			config.addDataSourceProperty("useUnicode", "true");
		    config.addDataSourceProperty("characterEncoding", "utf8");
			config.addDataSourceProperty("cachePrepStmts", "true");
			config.addDataSourceProperty("useServerPrepStmts", "true");
			config.addDataSourceProperty("prepStmtCacheSize", StringUtil.getSafeValue(getPrepStmtCacheSize(), "250"));
			config.addDataSourceProperty("prepStmtCacheSqlLimit", StringUtil.getSafeValue(getPrepStmtCacheSqlLimit(), "2048"));
		}
	}

	/**
	 * @return the dataSourceClassName
	 */
	public String getDataSourceClassName() {
		return dataSourceClassName;
	}

	/**
	 * @param dataSourceClassName the dataSourceClassName to set
	 */
	public void setDataSourceClassName(String dataSourceClassName) {
		this.dataSourceClassName = dataSourceClassName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * @param driverClassName the driverClassName to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * @return the portNumber
	 */
	public String getPortNumber() {
		return portNumber;
	}

	/**
	 * @param portNumber the portNumber to set
	 */
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * @return the connectionTestQuery
	 */
	public String getConnectionTestQuery() {
		return connectionTestQuery;
	}

	/**
	 * @param connectionTestQuery the connectionTestQuery to set
	 */
	public void setConnectionTestQuery(String connectionTestQuery) {
		this.connectionTestQuery = connectionTestQuery;
	}

	/**
	 * @return the leakDetectionThreshold
	 */
	public int getLeakDetectionThreshold() {
		return leakDetectionThreshold;
	}

	/**
	 * @param leakDetectionThreshold the leakDetectionThreshold to set
	 */
	public void setLeakDetectionThreshold(int leakDetectionThreshold) {
		this.leakDetectionThreshold = leakDetectionThreshold;
	}

	/**
	 * @return the maximumPoolSize
	 */
	public String getMaximumPoolSize() {
		return maximumPoolSize;
	}

	/**
	 * @param maximumPoolSize the maximumPoolSize to set
	 */
	public void setMaximumPoolSize(String maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	/**
	 * @return the minimumIdle
	 */
	public String getMinimumIdle() {
		return minimumIdle;
	}

	/**
	 * @param minimumIdle the minimumIdle to set
	 */
	public void setMinimumIdle(String minimumIdle) {
		this.minimumIdle = minimumIdle;
	}

	/**
	 * @return the prepStmtCacheSize
	 */
	public String getPrepStmtCacheSize() {
		return prepStmtCacheSize;
	}

	/**
	 * @param prepStmtCacheSize the prepStmtCacheSize to set
	 */
	public void setPrepStmtCacheSize(String prepStmtCacheSize) {
		this.prepStmtCacheSize = prepStmtCacheSize;
	}

	/**
	 * @return the prepStmtCacheSqlLimit
	 */
	public String getPrepStmtCacheSqlLimit() {
		return prepStmtCacheSqlLimit;
	}

	/**
	 * @param prepStmtCacheSqlLimit the prepStmtCacheSqlLimit to set
	 */
	public void setPrepStmtCacheSqlLimit(String prepStmtCacheSqlLimit) {
		this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
	}
}
