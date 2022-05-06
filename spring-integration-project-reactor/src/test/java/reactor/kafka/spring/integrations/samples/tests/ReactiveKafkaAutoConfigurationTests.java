///*
// * Copyright 2012-2022 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package reactor.kafka.spring.integrations.samples.tests;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.autoconfigure.AutoConfigurations;
//import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
//import org.springframework.boot.test.context.runner.ApplicationContextRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;
//import org.springframework.kafka.listener.RecordInterceptor;
//import reactor.kafka.receiver.ReceiverOptions;
//import reactor.kafka.sender.SenderOptions;
//import real.world.data.pipelines.autoconfigure.ReactiveKafkaAutoConfiguration;
//
//import java.util.Arrays;
//import java.util.Map;
//import java.util.concurrent.Semaphore;
//import java.util.regex.Pattern;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//
///**
// * Tests for {@link ReactiveKafkaAutoConfiguration}.
// *
// * @author Almog Tavor
// */
//class ReactiveKafkaAutoConfigurationTests {
//    private final Semaphore assignSemaphore = new Semaphore(0);
//    static final String TEST_TOPIC = "testTopic";
//
//    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
//        .withConfiguration(AutoConfigurations.of(KafkaAutoConfiguration.class))
//        .withConfiguration(AutoConfigurations.of(ReactiveKafkaAutoConfiguration.class));
//
//    /**
//     * Currently it is hard to test autoconfiguration for classes such
//     * as those that are expected in spring.reactor.kafka.receiver.assign-listeners.
//     * Waiting until tests will get uploaded to the reactor-kafka repository.
//     *
//     * @see <a href="https://github.com/reactor/reactor-kafka/issues/269">https://github.com/reactor/reactor-kafka/issues/269</a>
//     * assignListeners, revokeListeners, schedulerSupplier, assignTopicPartitions are missing tests
//     */
//    @Test
//    void receiverProperties() {
//        this.contextRunner.withPropertyValues(
//                "spring.reactor.kafka.receiver.commit-interval=2000", "spring.reactor.kafka.receiver.close-timeout=1500",
//                "spring.reactor.kafka.receiver.commit-batch-size=100", "spring.reactor.kafka.receiver.poll-timeout=1000",
//                "spring.reactor.kafka.receiver.atmost-once-commit-ahead-size=42", "spring.reactor.kafka.receiver.max-commit-attempts=3",
//                "spring.reactor.kafka.receiver.max-deferred-commits=5",
//                "spring.reactor.kafka.receiver.subscribe-topics=foo,bar",
//                "spring.reactor.kafka.receiver.subscribe-pattern=mytopic.+")
//            .run((context) -> {
//                ReceiverOptions<?, ?> receiverOptions = context
//                    .getBean(ReceiverOptions.class);
//                Map<String, Object> configs = receiverOptions.consumerProperties();
//                assertThat(configs.get("commitInterval")).isEqualTo(2000);
//                assertThat(configs.get("closeTimeout")).isEqualTo(1500);
//                assertThat(configs.get("commitBatchSize")).isEqualTo(100);
//                assertThat(configs.get("pollTimeout")).isEqualTo(1000);
//                assertThat(configs.get("atmostOnceCommitAheadSize")).isEqualTo(42);
//                assertThat(configs.get("maxCommitAttempts")).isEqualTo(3);
//                assertThat(configs.get("maxDeferredCommits")).isEqualTo(5);
//                assertThat(configs.get("subscribeTopics")).isEqualTo(Arrays.asList("foo", "bar"));
//                assertThat(configs.get("subscribePattern").toString()).isEqualTo(Pattern.compile("mytopic.+").toString());
//            });
//    }
//
//    /**
//     * Currently it is hard to test autoconfiguration for classes
//     * such as those that are expected in spring.reactor.kafka.receiver.assign-listeners.
//     * Waiting until tests will get uploaded to the reactor-kafka repository.
//     *
//     * @see <a href="https://github.com/reactor/reactor-kafka/issues/269">https://github.com/reactor/reactor-kafka/issues/269</a>
//     * assignListeners, revokeListeners, schedulerSupplier, assignTopicPartitions are missing tests
//     */
//    @Test
//    void producerProperties() {
//        this.contextRunner.withPropertyValues(
//                "spring.reactor.kafka.sender.scheduler=reactor.core.scheduler.Scheduler.boundedElastic", "spring.reactor.kafka.sender.max-in-flight=1500",
//                "spring.reactor.kafka.sender.stop-on-error=false", "spring.reactor.kafka.sender.close-timeout=500")
//            .run((context) -> {
//                SenderOptions<?, ?> senderOptions = context
//                    .getBean(SenderOptions.class);
//                Map<String, Object> configs = senderOptions.producerProperties();
////                assertThat(configs.get("scheduler")).isEqualTo(Schedulers.boundedElastic());
//                assertThat(configs.get("maxInFlight")).isEqualTo(1500);
//                assertThat(configs.get("stopOnError")).isEqualTo(false);
//                assertThat(configs.get("closeTimeout")).isEqualTo(500);
//            });
//    }
//
//    @Configuration(proxyBeanMethods = false)
//    static class ConsumerFactoryConfiguration {
//
//        @SuppressWarnings("unchecked")
//        private final ConsumerFactory<String, Object> consumerFactory = mock(ConsumerFactory.class);
//
//        @Bean
//        ConsumerFactory<String, Object> myConsumerFactory() {
//            return this.consumerFactory;
//        }
//
//    }
//
//    @Configuration(proxyBeanMethods = false)
//    static class RecordInterceptorConfiguration {
//
//        @Bean
//        RecordInterceptor<Object, Object> recordInterceptor() {
//            return (record) -> record;
//        }
//
//    }
//
//    @Configuration(proxyBeanMethods = false)
//    static class RebalanceListenerConfiguration {
//
//        @Bean
//        ConsumerAwareRebalanceListener rebalanceListener() {
//            return mock(ConsumerAwareRebalanceListener.class);
//        }
//
//    }
//
//
//}
