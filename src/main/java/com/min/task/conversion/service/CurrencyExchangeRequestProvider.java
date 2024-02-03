package com.min.task.conversion.service;

import com.min.task.properties.CurrencyExchangeProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class CurrencyExchangeRequestProvider {

    private CurrencyExchangeRequestProvider() {
    }

    public static HttpEntity<Void> getRequest(CurrencyExchangeProperties properties) {
        var headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", properties.getKey());
        headers.set("X-RapidAPI-Host", properties.getHost());

        return new HttpEntity<>(headers);
    }
}
