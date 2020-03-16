package com.main.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.user}")
	private String user;
	@Value("${jdbc.pass}")
	private String pass;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.show_sql}")
	private String showSql;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddlAuto;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
      sessionFactory.setDataSource(restDataSource());
      sessionFactory.setPackagesToScan(new String[] { "com.main.model" });
      sessionFactory.setHibernateProperties(hibernateProperties());
 
      return sessionFactory;
	}
 
	@Bean
	public DataSource restDataSource() {
      BasicDataSource dataSource = new BasicDataSource();
      dataSource.setDriverClassName(driverClassName);
      dataSource.setUrl(url);
      dataSource.setUsername(user);
      dataSource.setPassword(pass);
      return dataSource;
	}
 
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
      HibernateTransactionManager txManager = new HibernateTransactionManager();
      txManager.setSessionFactory(sessionFactory);
      return txManager;
	}
	
	Properties hibernateProperties() {
	      return new Properties() {
	         {
	            setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
	            setProperty("hibernate.dialect", dialect);
	            setProperty("hibernate.globally_quoted_identifiers",
	             "true");
	         }
	      };
	   }
}
