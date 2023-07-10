package com.demo.postgresql.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "entityManager",
		basePackages = {"com.demo.postgresql.repo"},
		transactionManagerRef = "transactionManager"
		)
public class PostGreSQLCOnfig {

	@Autowired
	private Environment environment;
	
	
	//datasource
	@Bean
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("second.datasource.url"));
		dataSource.setUsername(environment.getProperty("second.datasource.username"));
		dataSource.setPassword(environment.getProperty("second.datasource.password"));
		dataSource.setDriverClassName(environment.getProperty("second.datasource.driver-class-name"));
		return dataSource;
	}
	
	
	//entity Manager
	@Bean(name = "entityManager")
	public LocalContainerEntityManagerFactoryBean factoryBean() {
		LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		
		JpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
		bean.setJpaVendorAdapter(adapter);
		
		Map<String, String> map=new HashMap<>();
		map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		map.put("hibernate.show-sql", "true");
		map.put("hibernate.hbm2ddl.auto", "update");
		
		bean.setJpaPropertyMap(map);
		bean.setPackagesToScan("com.demo.postgresql.entity");		
		return bean;
	}

	//platform transaction Manager
	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager manager() {
		JpaTransactionManager manager=new JpaTransactionManager();
		manager.setEntityManagerFactory(factoryBean().getObject());
		
		return manager;
	}
}