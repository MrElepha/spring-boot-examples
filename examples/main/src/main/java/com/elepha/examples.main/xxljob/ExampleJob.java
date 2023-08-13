package com.elepha.examples.main.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExampleJob {

    @Qualifier("asyncTaskExecutor")
    private final Executor threadPool;

    private final StringRedisTemplate stringRedisTemplate;
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

    @XxlJob("luaTest")
    public ReturnT<String> luaTest() {
        // 先获取指定key的值，然后和传入的arg比较是否相等，相等值删除key，否则直接返回0
        String luaText = """
                if redis.call("get",KEYS[1]) == ARGV[1] then
                    return redis.call("del",KEYS[1])
                else
                    return 0
                end
                """;
        String key = "luaTest";
        String val = RandomStringUtils.randomAlphanumeric(5);
        if (!Boolean.TRUE.equals(stringRedisTemplate.opsForValue().setIfAbsent(key, val, 1, TimeUnit.MINUTES))) {
            XxlJobHelper.log("exist");
        }
        // 执行 lua 脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(luaText, Long.class);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        Long result = stringRedisTemplate.execute(redisScript, List.of(key), val);
        XxlJobHelper.log("res is {}", result);

        return ReturnT.SUCCESS;
    }
}
