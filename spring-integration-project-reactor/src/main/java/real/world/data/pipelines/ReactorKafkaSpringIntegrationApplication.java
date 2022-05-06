package real.world.data.pipelines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableIntegration
@ComponentScan({"reactor.kafka.spring.integration.samples.service", "reactor.kafka.spring.integration.samples.config", "reactor.kafka.spring.integration.samples.autoconfigure"})
public class ReactorKafkaSpringIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactorKafkaSpringIntegrationApplication.class, args);
    }
}
