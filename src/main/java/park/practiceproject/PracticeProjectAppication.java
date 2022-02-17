package park.practiceproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PracticeProjectAppication {
	public static void main(String[] args) {
		SpringApplication.run(PracticeProjectAppication.class, args);
	}
}
