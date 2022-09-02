package com.trion.healthcare;

import java.util.Properties;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

@org.springframework.context.annotation.Configuration
public class Configuration {

	@Autowired
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(batchDataSource());
		em.setPackagesToScan(new String[] { "com.trion.healthcare.entity" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(addionalPtoperties());
		return em;
	}

@Bean(name="batchDs")
@ConfigurationProperties(prefix="batch")
	 DataSource batchDataSource() {

HikariDataSource dataSource=new HikariDataSource();

dataSource.setDriverClassName(env.getProperty("driverClassName"));
dataSource.setJdbcUrl(env.getProperty("jdbcUrl"));		
//		dataSource.setUsername(env.getProperty("jdbc_username"));
//		dataSource.setPassword("password");
//		dataSource.setMinimumIdle(Integer.parseInt(env.getProperty("minimumIdle")));
//		dataSource.setMaximumPoolSize(Integer.parseInt(env.getProperty("maximumpoolsize")));
//		dataSource.setIdleTimeout(Long.parseLong(env.getProperty("idleTimeout")));
//		dataSource.setPoolName(env.getProperty("poolName"));
//		dataSource.setConnectionTimeout(Long.parseLong(env.getProperty("connectionTimeout")));
//		dataSource.getDataSourceProperties().setProperty("dataSource.preStmtCacheSize", env.getProperty("dataSorce.cachePreStmts"));
//dataSource.getDataSourceProperties().set
		
		return dataSource;
	}

	private Properties addionalPtoperties() {
		Properties properties = new Properties();
		properties.setProperty("hibenate.archive.autodetection", "class");
		return properties;
	}

}
