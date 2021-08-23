package com.my.app.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.integration.transports.netty.NettyConnectorFactory;
import org.hornetq.jms.client.HornetQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ImportResource(value = "spring-jpa.xml")
public class DefaultConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource() throws Exception {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass("com.ibm.db2.jcc.DB2Driver");
		comboPooledDataSource.setJdbcUrl("jdbc:db2://192.168.200.128:50000/testdb");
		comboPooledDataSource.setUser("db2inst1");
		comboPooledDataSource.setPassword("admin123");
		return comboPooledDataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws Exception {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(false);
		jpaVendorAdapter.setGenerateDdl(false);
		jpaVendorAdapter.setDatabase(Database.DB2);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.DB2Dialect");
		return jpaVendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitName("local_persistence");
		factoryBean.setDataSource(dataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		return factoryBean;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public HornetQConnectionFactory connectionFactory() {
		Map<String, Object> params = new HashMap<>();
		params.put("host", "192.168.200.128");
		params.put("port", 5445);
		TransportConfiguration configuration = new TransportConfiguration(NettyConnectorFactory.class.getName(),
				params);

		HornetQConnectionFactory connectionFactory = new HornetQConnectionFactory(configuration);
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		return jmsTemplate;
	}
}
