package com.jinair.sample.cybersourcesdkdemo.controller;

import com.jinair.sample.cybersourcesdkdemo.service.CybersourceService;
import com.jinair.sample.cybersourcesdkdemo.service.CybersourceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Properties;

/**
 * Project: cybersource-sdk-demo
 * Created by IntelliJ IDEA
 * Author: go2zo
 * Date: 2021/10/14
 * Description:
 */
@RestController
@RequiredArgsConstructor
public class CybersourceController {

    private final Properties cybsProps;

    private final CybersourceService service;

    @GetMapping("/setup")
    public ResponseEntity<Map> setup() {
        Map<String, String> reply = service.setup(cybsProps, "classpath:setup.properties");
        return ResponseEntity.ok(reply);
    }

    @GetMapping("/auth")
    public ResponseEntity<Map> auth() {
        Map<String, String> reply = service.auth(cybsProps, "classpath:auth.properties");
        return ResponseEntity.ok(reply);
    }
}
