package uz.dev.infomoneybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;
@EnableScheduling
@SpringBootApplication
public class InfoMoneyBotApplication {

  public static void main(String[] args) {
    SpringApplication.run(InfoMoneyBotApplication.class, args);
  }
  @Bean
  public WebClient webClient() {
    return WebClient.builder().build();
  }

}
