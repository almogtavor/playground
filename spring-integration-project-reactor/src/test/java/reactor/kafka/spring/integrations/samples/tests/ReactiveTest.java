//package reactor.kafka.spring.integrations.samples.tests;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
////import org.springframework.kafka.test.context.EmbeddedKafka;
//import org.springframework.test.annotation.DirtiesContext;
//import real.world.data.pipelines.ReactorKafkaSpringIntegrationApplication;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
////@EmbeddedKafka(topics = {"${Topic1}", "${Topic1}"})
//class ReactiveTest {
//
//    @Test
//    void main_contextLoads_DoesNotThrow() {
//        assertDoesNotThrow(() -> ReactorKafkaSpringIntegrationApplication.main(new String[]{}));
////        assertDoesNotThrow(() -> ReactorKafkaSpringIntegrationApplication.main(new String[]{"--server.port=0"}));
//    }
//
//}
