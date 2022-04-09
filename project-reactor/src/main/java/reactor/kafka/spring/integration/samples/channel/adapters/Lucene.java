package reactor.kafka.spring.integration.samples.channel.adapters;

import org.springframework.integration.http.dsl.HttpMessageHandlerSpec;
import reactor.kafka.spring.integration.samples.channel.adapters.outbound.spec.ReactiveS3MessageHandlerSpec;
import software.amazon.awssdk.services.s3.S3AsyncClient;

public final class Lucene {
    public static ReactiveS3MessageHandlerSpec outboundChannelAdapter(S3AsyncClient s3AsyncClient) {
        return (HttpMessageHandlerSpec)(new ReactiveS3MessageHandlerSpec(s3AsyncClient)).expectReply(false);
    }
    public static ReactiveS3MessageHandlerSpec outboundChannelAdapter(S3AsyncClient s3AsyncClient) {
        return (HttpMessageHandlerSpec)(new ReactiveS3MessageHandlerSpec(s3AsyncClient)).expectReply(false);
    }

    private Lucene() {
    }
}
