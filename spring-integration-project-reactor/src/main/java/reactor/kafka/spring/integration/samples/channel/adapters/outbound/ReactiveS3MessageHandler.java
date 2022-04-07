package reactor.kafka.spring.integration.samples.channel.adapters.outbound;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.handler.AbstractReactiveMessageHandler;
import org.springframework.messaging.Message;
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
public class ReactiveS3MessageHandler extends AbstractReactiveMessageHandler {
    private final S3AsyncClient s3client;
    private final String bucketName;

    public ReactiveS3MessageHandler(S3AsyncClient s3client, String bucketName) {
        this.s3client = s3client;
        this.bucketName = bucketName;
    }

    @Override
    protected Mono<Void> handleMessageInternal(Message<?> message) {
//        long length = message.getHeaders().getl();
//        if (length < 0) {
//            throw new UploadFailedException(HttpStatus.BAD_REQUEST.value(), Optional.of("required header missing: Content-Length"));
//        }

        String fileKey = UUID.randomUUID().toString();
        Map<String, String> metadata = new HashMap<String, String>();
//        MediaType mediaType = headers.getContentType();
//
//        if (mediaType == null) {
//            mediaType = MediaType.APPLICATION_OCTET_STREAM;
//        }

//        log.info("[I95] uploadHandler: mediaType{}, length={}", mediaType, length);
        ByteBuffer wrap = ByteBuffer.wrap(message.getPayload().toString().getBytes());
        CompletableFuture<PutObjectResponse> future = s3client
                .putObject(PutObjectRequest.builder()
                                .bucket(bucketName)
//                                .contentLength(length)
                                .key(fileKey.toString())
//                                .contentType(mediaType.toString())
                                .metadata(metadata)
                                .build(),
                        AsyncRequestBody.fromPublisher(Mono.just(wrap)));

        return Mono.fromFuture(future).then();
    }
}
