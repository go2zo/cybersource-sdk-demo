package com.jinair.sample.cybersourcesdkdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * Project: cybersource-sdk-demo
 * Created by IntelliJ IDEA
 * Author: go2zo
 * Date: 2021/10/14
 * Description:
 */
@Configuration
public class PropertiesConfig {

    @Value("classpath:cybs.properties")
    private Resource cybsFile;

    @Bean
    public Properties cybsProps() {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream(cybsFile.getFile());
            props.load(fis);
            fis.close();
            return props;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
