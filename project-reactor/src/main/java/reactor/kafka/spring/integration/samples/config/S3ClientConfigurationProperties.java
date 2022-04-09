package reactor.kafka.spring.integration.samples.config;

import java.net.URI;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import software.amazon.awssdk.regions.Region;

/**
 * The S3Mock details are:
 * accessKeyId =
 */
@ConfigurationProperties(prefix = "aws.s3")
@Data
public class S3ClientConfigurationProperties {

    private Region region = Region.US_EAST_1;
    private URI endpoint = null;

    private String accessKeyId;
    private String secretAccessKey;

    // Bucket name we'll be using as our backend storage

    // AWS S3 requires that file parts must have at least 5MB, except
    // for the last part. This may change for other S3-compatible services, so let't
    // define a configuration property for that
    private int multipartMinPartSize = 5*1024*1024;

}
