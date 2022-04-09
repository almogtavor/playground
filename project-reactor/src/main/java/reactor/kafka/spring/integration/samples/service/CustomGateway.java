package reactor.kafka.spring.integration.samples.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface CustomGateway {

    @Gateway(requestChannel = "input")
    public void print(Message<String> message);

}