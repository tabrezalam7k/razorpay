package com.razorpay.config;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;


@Configuration
public class RazorpayConfig {

    @Value("${razorpay.key-id}")
    private String apiKey;

    @Value("${razorpay.key-secret}")
    private String apiSecret;

    public String getRazorpayKeyId(){
        return apiKey;
    }
    public String getRazorpayKeySecret(){
        return apiSecret;
    }
}
