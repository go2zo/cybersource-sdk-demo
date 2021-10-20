package com.jinair.sample.cybersourcesdkdemo.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Project: cybersource-sdk-demo
 * Created by IntelliJ IDEA
 * Author: go2zo
 * Date: 2021/10/14
 * Description:
 */
@Component
@PropertySource({ "classpath:auth.properties"})
@ConfigurationProperties(prefix = "cybs")
public class AuthProperties {

}
