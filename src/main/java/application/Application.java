package application;

import application.bot.BotMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@ComponentScan(basePackages = {"application"})
public class Application {

    public static void main(String[] args) {

        ApiContextInitializer.init();
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    BotMain botMain;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args ->{
            botMain.main(args);
        };
    }
}
