package com.jinair.sample.cybersourcesdkdemo.controller;

import com.jinair.sample.cybersourcesdkdemo.service.CybersourceService;
import com.jinair.sample.cybersourcesdkdemo.service.CybersourceServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CybersourceController {

    private final Properties cybsProps;

    private final CybersourceService service;

    @PostMapping("/setup")
    public ResponseEntity<Map> setup(@RequestBody Map<String, String> parameters) {
        Map<String, String> reply = service.setup(cybsProps, parameters);
        log.debug("Request => " + parameters.toString());
        log.debug("Reply => " + reply.toString());
        return ResponseEntity.ok(reply);
    }

}
