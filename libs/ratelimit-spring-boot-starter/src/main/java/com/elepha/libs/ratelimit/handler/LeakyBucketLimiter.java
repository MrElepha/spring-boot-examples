package com.elepha.libs.ratelimit.handler;

import org.springframework.stereotype.Component;

/**
 * 漏桶
 */
@Component(RateLimitHandler.RATE_LIMIT_MODE_LEAKY_BUCKET)
public class LeakyBucketLimiter {
}
