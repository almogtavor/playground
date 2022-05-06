package real.world.data.pipelines.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ConsumerEndpoint {

    @ServiceActivator(inputChannel = "output")
    public void consumeStringMessage(String message) {
        System.out.println("Received message from myOutputChannel : " + message);
    }

    @ServiceActivator(inputChannel = "input", outputChannel = "output")
    public String toUppercase(Message<String> message) {
        System.out.println("Received message from myInputChannel : " + message.getPayload());

        return message.getPayload().toUpperCase();
    }
}