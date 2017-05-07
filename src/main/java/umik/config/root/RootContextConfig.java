//package umik.config.root;
//
//import javax.persistence.EntityManagerFactory;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@ComponentScan({ "umik" })
//public class RootContextConfig {
//
//	@Bean(name = "transactionManager")
//	public PlatformTransactioManager transactionManager(EntityManagerFactory entityManagerFactory,
//			DriverManagerDataSource dataSource) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(entityManagerFactory);
//		transactionManager.setDataSource(dataSource);
//		return transactionManager;
//	}
//
//}
