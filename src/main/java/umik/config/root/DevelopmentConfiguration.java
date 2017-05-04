package umik.config.root;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DevelopmentConfiguration {
	
	@Bean(name = "datasource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://umik.cb2bxn4cggik.us-west-2.rds.amazonaws.com:3306/umik");
		dataSource.setUsername("admin");
		dataSource.setPassword("Amazonroot");
		return dataSource;
	}

	
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DriverManagerDataSource dataSource) {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan(new String[] { "umik.app.model" });
		entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Map<String, Object> jpaProperties = new HashMap<String, Object>();
		//jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.format_sql", "true");
		jpaProperties.put("hibernate.use_sql_comments", "true");
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

		return entityManagerFactoryBean;
	}
}
