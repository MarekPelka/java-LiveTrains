package umik.app.controllers;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class Application {
	
	static Logger log = Logger.getLogger(Application.class.getName());
	
    public static void main(String[] args) {
    	log.info("Maven + Spring + Hibernate + AWS + Heroku");
        SpringApplication.run(Application.class, args);
    }
}