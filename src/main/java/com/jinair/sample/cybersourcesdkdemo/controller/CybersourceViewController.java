package com.jinair.sample.cybersourcesdkdemo.controller;

import com.jinair.sample.cybersourcesdkdemo.service.CybersourceService;
import lombok.RequiredArgsConstructor;
import org.apache.xml.utils.Hashtree2Node;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Project: cybersource-sdk-demo
 * Created by IntelliJ IDEA
 * Author: go2zo
 * Date: 2021/10/15
 * Description:
 */
@Controller
@RequiredArgsConstructor
public class CybersourceViewController {

    private final Properties cybsProps;
    private final CybersourceService service;

    @GetMapping("/cybs/")
    public String init(Model model) throws IOException {
        Properties properties = PropertiesLoaderUtils
                .loadAllProperties("setup.properties");

        model.addAttribute("data", properties);

        return "/cybs/index";
    }

    @PostMapping("/cybs/setup")
    public String setup(@RequestParam Map<String, String> parameters, Model model) {
        Map<String, String> params = new HashMap<>(parameters);
        Map<String, String> reply = service.setup(cybsProps, params);
        for (String key : reply.keySet()) {
            if (key.startsWith("payerAuthSetupReply_")) {
                params.put(key, reply.get(key));
            }
        }
        model.addAttribute("data", params);
        return "/cybs/setup";
    }

    @RequestMapping("/cybs/enroll")
    public String enroll(@RequestParam Map<String, String> parameters, Model model) throws IOException {
        Map<String, String> params = new HashMap<>(parameters);

        Properties properties = PropertiesLoaderUtils
                .loadAllProperties("order.properties");
        params.putAll((Map) properties);

        Map<String, String> reply = service.enroll(cybsProps, params);
        for (String key : reply.keySet()) {
            if (key.startsWith("payerAuthEnrollReply_")) {
                params.put(key, reply.get(key));
            }
        }
        model.addAttribute("data", params);
        return "/cybs/enroll";
    }

    @GetMapping("/cybs/auth")
    public String auth(@RequestParam Map<String, String> parameters, Model model) {
        return "/cybs/auth";
    }
}
