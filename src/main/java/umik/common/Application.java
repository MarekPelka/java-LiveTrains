package umik.common;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	System.out.println("Maven + Spring + Hibernate + MySQL");
        SpringApplication.run(Application.class, args);
    }
}