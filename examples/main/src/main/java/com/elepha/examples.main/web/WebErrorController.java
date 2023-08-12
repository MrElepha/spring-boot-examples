package com.elepha.examples.main.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@ConditionalOnProperty(name = "web.use-default-exception-handler", matchIfMissing = true)
public class WebErrorController implements ErrorController {

    // todo 异常校验
}
