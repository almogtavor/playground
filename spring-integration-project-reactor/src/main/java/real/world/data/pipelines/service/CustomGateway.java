package real.world.data.pipelines.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface CustomGateway {

    @Gateway(requestChannel = "input")
    public void print(Message<String> message);

}