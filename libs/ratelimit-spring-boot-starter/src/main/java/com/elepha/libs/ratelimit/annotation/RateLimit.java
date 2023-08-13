package com.elepha.libs.ratelimit.annotation;

import com.elepha.libs.ratelimit.model.RateLimitMode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RateLimit {

    RateLimitMode mode() default RateLimitMode.FixedWindow;

    // todo
}
