package io.pyxiscode.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class RemindmeServiceApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(RemindmeServiceApplication.class, args);
    }

}
