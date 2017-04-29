package umik.app.controllers;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class Application {
	
    public static void main(String[] args) {
    	System.out.println("Maven + Spring + Hibernate + AWS + Heroku");
        SpringApplication.run(Application.class, args);
    }
}