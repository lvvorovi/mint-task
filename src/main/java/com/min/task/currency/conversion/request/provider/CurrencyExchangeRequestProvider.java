package com.min.task.currency.conversion.request.provider;

import com.min.task.properties.CurrencyExchangeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyExchangeRequestProvider implements RequestProvider {

    private final CurrencyExchangeProperties properties;

    @Override
    public HttpEntity<Void> getRequest() {
        var headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", properties.getKey());
        headers.set("X-RapidAPI-Host", properties.getHost());

        return new HttpEntity<>(headers);
    }
}
