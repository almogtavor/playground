package reactor.kafka.spring.integration.samples.channel.adapters;

import org.springframework.expression.Expression;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.expression.FunctionExpression;
import org.springframework.integration.http.dsl.HttpControllerEndpointSpec;
import org.springframework.integration.http.dsl.HttpMessageHandlerSpec;
import org.springframework.integration.http.dsl.HttpRequestHandlerEndpointSpec;
import org.springframework.integration.http.inbound.HttpRequestHandlingController;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import reactor.kafka.spring.integration.samples.channel.adapters.outbound.spec.ReactiveS3MessageHandlerSpec;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.net.URI;
import java.util.function.Function;

public final class S3 {
    public static ReactiveS3MessageHandlerSpec outboundChannelAdapter(S3AsyncClient s3AsyncClient) {
        return (HttpMessageHandlerSpec)(new ReactiveS3MessageHandlerSpec(s3AsyncClient)).expectReply(false);
    }
    public static ReactiveS3MessageHandlerSpec outboundChannelAdapter(S3AsyncClient s3AsyncClient) {
        return (HttpMessageHandlerSpec)(new ReactiveS3MessageHandlerSpec(s3AsyncClient)).expectReply(false);
    }

    private S3() {
    }
}
