package com.elepha.springbootdemo.web.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class HttpResp<T> {

    private T data;
    private long now = Instant.now().getEpochSecond();

    public static <T> HttpResp<T> of(T data) {
        HttpResp<T> resp = new HttpResp<>();
        resp.setData(data);
        return resp;
    }

    public static HttpResp<String> ofMsg(String msg) {
        return HttpResp.of(msg);
    }
}
