package com.jinair.sample.cybersourcesdkdemo.service;

import Model.PayerAuthSetupRequest;
import com.cybersource.ws.client.Client;
import com.cybersource.ws.client.ClientException;
import com.cybersource.ws.client.FaultException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Project: cybersource-sdk-demo
 * Created by IntelliJ IDEA
 * Author: go2zo
 * Date: 2021/10/14
 * Description:
 */
@Service
@RequiredArgsConstructor
public class CybersourceServiceImpl implements CybersourceService {
    public static final String PAYER_AUTH_SETUP_SERVICE_RUN = "payerAuthSetupService_run";
    public static final String PAYER_AUTH_ENROLL_SERVICE_RUN = "payerAuthEnrollService_run";
    public static final String PAYER_AUTH_ENROLL_SERVICE_RETURN_URL = "payerAuthEnrollService_returnURL";

    private final ResourceLoader resourceLoader;

    @Override
    public Map<String, String> setup(Properties props, String propFileName) {
        return run(props, propFileName);
    }

    @Override
    public Map<String, String> setup(Properties props, Map<String, String> request) {
        if (!request.containsKey(PAYER_AUTH_SETUP_SERVICE_RUN)) {
            request.put(PAYER_AUTH_SETUP_SERVICE_RUN, "true");
        }
        Map<String, String> result = run(props, request);
        request.remove(PAYER_AUTH_SETUP_SERVICE_RUN);
        return result;
    }

    @Override
    public Map<String, String> enroll(Properties props, Map<String, String> request) {
        if (!request.containsKey(PAYER_AUTH_ENROLL_SERVICE_RUN)) {
            request.put(PAYER_AUTH_ENROLL_SERVICE_RUN, "true");
        }
        if (!request.containsKey(PAYER_AUTH_ENROLL_SERVICE_RETURN_URL)) {
            request.put(PAYER_AUTH_ENROLL_SERVICE_RETURN_URL, "http://localhost:8080/cybs/auth");
        }
        Map<String, String> result = run(props, request);
        request.remove(PAYER_AUTH_ENROLL_SERVICE_RUN);
        return result;
    }

    @Override
    public Map<String, String> auth(Properties props, String propFileName) {
        return run(props, propFileName);
    }

    private Map<String, String> run(Properties props, String propFileName) {
        Properties authProps;

        try {
            authProps = readProperty(propFileName);
            Map<String, String> request = new HashMap<String, String>((Map) authProps);
            return Client.runTransaction(request, props);
        } catch (FaultException | ClientException | IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyMap();
    }

    private Map<String, String> run(Properties props, Map<String, String> request) {
        try {
            return Client.runTransaction(request, props);
        } catch (FaultException | ClientException e) {
            e.printStackTrace();
        }

        return Collections.emptyMap();
    }

    private Properties readProperty(String propFileName) throws IOException {
        Properties props = new Properties();
        Resource resource = resourceLoader.getResource(propFileName);

        FileInputStream fis = new FileInputStream(resource.getFile());
        props.load(fis);
        fis.close();

        return props;
    }

    public static void main(String[] args) {
        PayerAuthSetupRequest request = new PayerAuthSetupRequest();
    }

}
