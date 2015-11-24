package uk.co.dashery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;

@Configuration
public class TestDasheryClothingQueryApplication extends DasheryClothingQueryApplication {

    @Bean
    public RestTemplate restTemplate() {
        return mock(RestTemplate.class);
    }
}
