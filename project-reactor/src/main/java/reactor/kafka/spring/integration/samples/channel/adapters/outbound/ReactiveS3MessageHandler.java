package reactor.kafka.spring.integration.samples.channel.adapters.outbound;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.Expression;
import org.springframework.messaging.Message;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Slf4j
public class ReactiveS3MessageHandler {
    private final S3AsyncClient s3client;
    private Expression bucketNameExpression;
    private boolean expectReply;

    public ReactiveS3MessageHandler(S3AsyncClient s3client, Expression bucketName) {
        this.s3client = s3client;
        this.bucketNameExpression = bucketName;
    }

    public ReactiveS3MessageHandler(S3AsyncClient s3client) {
        this.s3client = s3client;
    }

    /**
     * Set the SpEL {@link Expression} that should resolve to a bucket name
     * used by {@link S3AsyncClient} to store data
     * @param bucketNameExpression The bucket name expression.
     */
    public void setBucketNameExpression(Expression bucketNameExpression) {
        Assert.notNull(bucketNameExpression, "'bucketNameExpression' must not be null");
        this.bucketNameExpression = bucketNameExpression;
    }

    public void handleMessageInternal(Message<?> message) {
        String fileKey = UUID.randomUUID().toString();
        Map<String, String> metadata = new HashMap<String, String>();
        ByteBuffer wrap = ByteBuffer.wrap(message.getPayload().toString().getBytes());
        CompletableFuture<PutObjectResponse> future = s3client
                .putObject(PutObjectRequest.builder()
                                .bucket(bucketNameExpression.getExpressionString())
                                .key(fileKey)
                                .metadata(metadata)
                                .build(),
                        AsyncRequestBody.fromPublisher(Mono.just(wrap)));

        Mono.fromFuture(future).subscribe();
    }
}
