//package reactor.kafka.spring.integration.samples.channel.adapters.outbound;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.expression.Expression;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.integration.IntegrationPatternType;
//import org.springframework.integration.expression.ValueExpression;
//import org.springframework.integration.handler.AbstractReactiveMessageHandler;
//import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
//import org.springframework.integration.support.MessageBuilderFactory;
//import org.springframework.messaging.Message;
//import org.springframework.util.Assert;
//import reactor.core.publisher.Mono;
//import software.amazon.awssdk.core.async.AsyncRequestBody;
//import software.amazon.awssdk.services.s3.S3AsyncClient;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//import software.amazon.awssdk.services.s3.model.PutObjectResponse;
//
//import java.nio.ByteBuffer;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.CompletableFuture;
//
//@Slf4j
//public class SolrMessageHandler extends AbstractReactiveMessageHandler {
//    private final S3AsyncClient s3client;
//    private Expression bucketNameExpression;
//    private boolean expectReply;
//
//    public SolrMessageHandler(S3AsyncClient s3client, Expression bucketName) {
//        this.s3client = s3client;
//        this.bucketNameExpression = bucketName;
//    }
//
//    public SolrMessageHandler(S3AsyncClient s3client) {
//        this.s3client = s3client;
//    }
//
//    /**
//     * Set the SpEL {@link Expression} that should resolve to a bucket name
//     * used by {@link S3AsyncClient} to store data
//     * @param bucketNameExpression The bucket name expression.
//     */
//    public void setBucketNameExpression(Expression bucketNameExpression) {
//        Assert.notNull(bucketNameExpression, "'bucketNameExpression' must not be null");
//        this.bucketNameExpression = bucketNameExpression;
//    }
//
//    public boolean isExpectReply() {
//        return this.expectReply;
//    }
//
//    public void setExpectReply(boolean expectReply) {
//        this.expectReply = expectReply;
//    }
//
//    public void setExpectedResponseType(Class<?> expectedResponseType) {
//        Assert.notNull(expectedResponseType, "'expectedResponseType' must not be null");
//        this.setExpectedResponseTypeExpression(new ValueExpression(expectedResponseType));
//    }
//
//    public void setExpectedResponseTypeExpression(Expression expectedResponseTypeExpression) {
//        this.expectedResponseTypeExpression = expectedResponseTypeExpression;
//    }
//
//    public IntegrationPatternType getIntegrationPatternType() {
//        return this.expectReply ? super.getIntegrationPatternType() : IntegrationPatternType.outbound_channel_adapter;
//    }
//
//    protected Object getReply(ResponseEntity<?> httpResponse) {
//        HttpHeaders httpHeaders = httpResponse.getHeaders();
//        Map<String, Object> headers = this.headerMapper.toHeaders(httpHeaders);
//        if (this.transferCookies) {
//            this.doConvertSetCookie(headers);
//        }
//
//        MessageBuilderFactory messageBuilderFactory = this.getMessageBuilderFactory();
//        AbstractIntegrationMessageBuilder replyBuilder;
//        if (httpResponse.hasBody() && this.extractResponseBody) {
//            Object responseBody = httpResponse.getBody();
//            replyBuilder = responseBody instanceof Message ? messageBuilderFactory.fromMessage((Message)responseBody) : messageBuilderFactory.withPayload(responseBody);
//        } else {
//            replyBuilder = messageBuilderFactory.withPayload(httpResponse);
//        }
//
//        replyBuilder.setHeader("http_statusCode", httpResponse.getStatusCode());
//        return replyBuilder.copyHeaders(headers);
//    }
//
//
//    @Override
//    protected Mono<Void> handleMessageInternal(Message<?> message) {
//        String fileKey = UUID.randomUUID().toString();
//        Map<String, String> metadata = new HashMap<String, String>();
//        ByteBuffer wrap = ByteBuffer.wrap(message.getPayload().toString().getBytes());
//        CompletableFuture<PutObjectResponse> future = s3client
//                .putObject(PutObjectRequest.builder()
//                                .bucket(bucketNameExpression.getExpressionString())
//                                .key(fileKey)
//                                .metadata(metadata)
//                                .build(),
//                        AsyncRequestBody.fromPublisher(Mono.just(wrap)));
//
//        return Mono.fromFuture(future).then();
//    }
//}
