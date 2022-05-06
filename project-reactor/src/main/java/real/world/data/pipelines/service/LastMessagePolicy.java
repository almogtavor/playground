//package reactor.kafka.spring.integration.samples.service;
//
//import org.springframework.messaging.Message;
//
//import java.util.List;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//
//public class LastMessagePolicy implements GroupingPolicy {
//    @Override
//    public void logOnConflict(boolean condition) {
//
//    }
//
//    @Override
//    public void failOnConflict(boolean condition) {
//
//    }
//
//    @Override
//    public void onConflict(Supplier<?> condition) {
//
//    }
//
//    @Override
//    public <T> Message<T> getPolicyFunction(List<Message<T>> messages, GroupingPolicyActivator groupingPolicyActivator) {
//        return messages.stream().flatMap(a -> {
//            if (a.getPayload().getClass().isInstance(Integer)) {
//
//            }
//            return null;
//        })
//    }
//
//    public <T> Message<T> getPolicyFunction(List<Message<T>> messages) {
//        return messages.stream().reduce(message, (message1,message2) -> {
//
//        })
//    }
//}
