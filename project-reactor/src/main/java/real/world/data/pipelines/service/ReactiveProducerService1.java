//package reactor.kafka.spring.integration.samples.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//@EnableScheduling
//public class ReactiveProducerService {
//
//    private final Logger log = LoggerFactory.getLogger(ReactiveProducerService.class);
//
//    private final ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate;
//
////    @Autowired
////    @Lazy
////    ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate;
//
//    public ReactiveProducerService(ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate) {
//        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
//    }
//
////    @ServiceActivator(inputChannel = "kafkaProducerChannel", outputChannel = "nullChannel")
////    @Scheduled(fixedDelay = 1000)
////    public void sendToKafkaReactively() {
//////        System.out.println("aaaaaaaaaaaaa");
////        this.reactiveKafkaProducerTemplate.send("Topic1", "almooooooooooooooooog");
////    }
//
//    @ServiceActivator(inputChannel = "kafkaProducerChannel", outputChannel = "nullChannel")
//    @Scheduled(fixedDelay = 1000)
//    public void send() {
//        reactiveKafkaProducerTemplate.send("Topic1", "fakeProducerDTO")
//            .doOnSuccess(senderResult -> log.info("sent {} offset : {}", "fakeProducerDTO", senderResult.recordMetadata().offset()))
//            .subscribe();
//    }
//}
