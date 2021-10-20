package com.jinair.sample.cybersourcesdkdemo.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.util.Map;
import java.util.Properties;

/**
 * Project: cybersource-sdk-demo
 * Created by IntelliJ IDEA
 * Author: go2zo
 * Date: 2021/10/14
 * Description:
 */
public interface CybersourceService {
    Map<String, String> setup(Properties props, String propFileName);
    Map<String, String> setup(Properties props, Map<String, String> request);
    Map<String, String> enroll(Properties props, Map<String, String> request);
    Map<String, String> auth(Properties props, String propFileName);
}
