//package real.world.data.pipelines.channel.adapters.outbound.spec;
//
//import org.springframework.expression.Expression;
//import org.springframework.expression.common.LiteralExpression;
//import org.springframework.integration.dsl.ComponentsRegistration;
//import org.springframework.integration.dsl.ReactiveMessageHandlerSpec;
//import org.springframework.integration.expression.FunctionExpression;
//import org.springframework.messaging.Message;
//import real.world.data.pipelines.channel.adapters.outbound.ReactiveS3MessageHandler;
//import software.amazon.awssdk.services.s3.S3AsyncClient;
//
//import java.util.function.Function;
//
//public class ReactiveS3MessageHandlerSpec
//        extends ReactiveMessageHandlerSpec<ReactiveS3MessageHandlerSpec, ReactiveS3MessageHandler>
//        implements ComponentsRegistration {
//
//    public ReactiveS3MessageHandlerSpec(S3AsyncClient s3AsyncClient) {
//        super(new ReactiveS3MessageHandler(s3AsyncClient));
//    }
//
//    /**
//     * Configure a collection name to store data.
//     * @param bucketName the explicit collection name to use.
//     * @return the spec
//     */
//    public ReactiveS3MessageHandlerSpec bucketName(String bucketName) {
//        return bucketNameExpression(new LiteralExpression(bucketName));
//    }
//
//    /**
//     * Configure a {@link Function} for evaluation a collection against request message.
//     * @param bucketNameFunction the {@link Function} to determine a collection name at runtime.
//     * @param <P> an expected payload type
//     * @return the spec
//     */
//    public <P> ReactiveS3MessageHandlerSpec bucketNameFunction(
//            Function<Message<P>, String> bucketNameFunction) {
//
//        return bucketNameExpression(new FunctionExpression<>(bucketNameFunction));
//    }
//
//    /**
//     * Configure a SpEL expression to evaluate a collection name against a request message.
//     * @param bucketNameExpression the SpEL expression to use.
//     * @return the spec
//     */
//    public ReactiveS3MessageHandlerSpec bucketNameExpression(Expression bucketNameExpression) {
//        this.reactiveMessageHandler.setBucketNameExpression(bucketNameExpression);
//        return this;
//    }
//}
