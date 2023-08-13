package com.elepha.libs.ratelimit.handler;

public interface RateLimitHandler {

    public static final String RATE_LIMIT_MODE_FIXED_WINDOW ="fixedWindow";
    public static final String RATE_LIMIT_MODE_SIDE_WINDOW ="sideWindow";
    public static final String RATE_LIMIT_MODE_LEAKY_BUCKET ="leakyBucket";
    public static final String RATE_LIMIT_MODE_TOKEN_BUCKET ="tokenBucket";

}
