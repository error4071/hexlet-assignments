package exercise;

import exercise.daytime.Day;
import exercise.daytime.Daytime;
import exercise.daytime.Night;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public static Daytime getDaytime() {
        int hour = LocalDateTime.now()
                .getHour();

        if (hour >= 6 && hour < 22) {
            return new Day();
        } else {
            return new Night();
        }
    }
}
