package com.elepha.libs.ratelimit.model;

import com.elepha.libs.ratelimit.handler.RateLimitHandler;

public enum RateLimitMode {

    FixedWindow(RateLimitHandler.RATE_LIMIT_MODE_FIXED_WINDOW),

    SideWindow(RateLimitHandler.RATE_LIMIT_MODE_SIDE_WINDOW),

    LeakyBucket(RateLimitHandler.RATE_LIMIT_MODE_LEAKY_BUCKET),

    TokenBucket(RateLimitHandler.RATE_LIMIT_MODE_TOKEN_BUCKET),
    ;

    private final String value;

    RateLimitMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
