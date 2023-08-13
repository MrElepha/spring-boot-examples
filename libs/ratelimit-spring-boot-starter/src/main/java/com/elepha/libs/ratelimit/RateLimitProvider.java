package com.elepha.libs.ratelimit;

import com.elepha.libs.ratelimit.handler.RateLimitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RateLimitProvider {

    private final Map<String, RateLimitHandler> rateLimitHandler;

    @Autowired
    public RateLimitProvider(Map<String, RateLimitHandler> rateLimitHandler) {
        this.rateLimitHandler = rateLimitHandler;
    }
}
