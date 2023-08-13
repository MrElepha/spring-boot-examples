package com.elepha.libs.ratelimit.handler;

import org.springframework.stereotype.Component;

/**
 * 固定时间窗口计数器
 */
@Component(RateLimitHandler.RATE_LIMIT_MODE_FIXED_WINDOW)
public class CounterFixedWindowLimiter implements RateLimitHandler {

}
