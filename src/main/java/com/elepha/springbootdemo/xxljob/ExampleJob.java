package com.elepha.springbootdemo.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executor;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExampleJob {

    @Qualifier("asyncTaskExecutor")
    private final Executor threadPool;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    @XxlJob("hello")
    public ReturnT<String> hello() {
        XxlJobHelper.log("hello {}", XxlJobHelper.getJobParam());
        return ReturnT.SUCCESS;
    }

    @XxlJob("asyncTest")
    public ReturnT<String> asyncTest() {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/sleep1"))
                .GET()
                .build();

        for (int i = 0; i < 100; ++i) {
            threadPool.execute(
                    () -> {
                        try {
                            log.info("start send to sleep request");
                            httpClient.send(req, HttpResponse.BodyHandlers.ofString());
                        } catch (IOException | InterruptedException e) {
                            XxlJobHelper.log("asyncTest occur http get error {}", e.getMessage(), e);
                        }
                    }
            );
        }

        return ReturnT.SUCCESS;
    }
}
