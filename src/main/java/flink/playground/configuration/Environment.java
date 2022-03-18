package flink.playground.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Environment {
    @Bean
    public String getMode() {
        return "ordered";
    }
}
