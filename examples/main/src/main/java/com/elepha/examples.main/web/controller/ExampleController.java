package com.elepha.examples.main.web.controller;

import com.elepha.examples.main.web.model.response.HttpResp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ExampleController {

    @GetMapping("/hello")
    public ResponseEntity<HttpResp<String>> hello(@RequestParam String name) {
        return ResponseEntity.ok(
                HttpResp.ofMsg("hello %s".formatted(name))
        );
    }

    @GetMapping("/sleep1")
    public ResponseEntity<HttpResp<String>> sleep1() throws InterruptedException {
        Thread.sleep(1000);
        return ResponseEntity.ok(
                HttpResp.ofMsg("ok")
        );
    }
}
