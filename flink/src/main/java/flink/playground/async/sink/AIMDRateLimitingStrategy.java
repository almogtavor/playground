package flink.playground.async.sink;


import org.apache.flink.annotation.Internal;
import org.apache.flink.util.Preconditions;

/**
 * Additive Increase/Multiplicative Decrease implementation of throttling Strategy This
 * implementation is not thread safe.
 *
 * <p>This Strategy is used by the writer to implement rate limiting on request thoroughput to match
 * throttled destinations.
 */
@Internal
public final class AIMDRateLimitingStrategy {
    private final int increaseRate;
    private final double decreaseFactor;
    private final int rateThreshold;

    private int inFlightMessages;

    /**
     * @param increaseRate Linear increase value of rate limit on each acknowledgement.
     * @param decreaseFactor Exponential decrease factor of rate limit on each failure.
     * @param rateThreshold Threshold for maximum value of rate limit, this can be enforced due to
     *     writer or destination specific limits.
     * @param initialRate Initial rate limit to start with.
     */
    public AIMDRateLimitingStrategy(
            int increaseRate, double decreaseFactor, int rateThreshold, int initialRate) {
        Preconditions.checkArgument(
                decreaseFactor < 1.0 && decreaseFactor > 0.0,
                "Decrease factor must be between 0.0 and 1.0.");
        Preconditions.checkArgument(increaseRate > 0, "Increase rate must be positive integer.");
        Preconditions.checkArgument(
                rateThreshold >= initialRate, "Initial rate must not exceed threshold.");

        this.increaseRate = increaseRate;
        this.decreaseFactor = decreaseFactor;
        this.rateThreshold = rateThreshold;
        this.inFlightMessages = initialRate;
    }

    public int getRateLimit() {
        return inFlightMessages;
    }

    public void scaleUp() {
        inFlightMessages = (Math.min(inFlightMessages + increaseRate, rateThreshold));
    }

    public void scaleDown() {
        inFlightMessages = Math.max(1, (int) Math.round(inFlightMessages * decreaseFactor));
    }
}