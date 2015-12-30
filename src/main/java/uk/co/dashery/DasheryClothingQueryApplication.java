package uk.co.dashery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import uk.co.dashery.clothing.rabbitmq.RabbitMqConfig;

@SpringBootApplication
@Import(RabbitMqConfig.class)
public class DasheryClothingQueryApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(DasheryClothingQueryApplication.class, args);
    }

}
