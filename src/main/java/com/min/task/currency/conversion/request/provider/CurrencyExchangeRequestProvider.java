package com.min.task.currency.conversion.request.provider;

import com.min.task.properties.CurrencyExchangeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyExchangeRequestProvider implements RequestProvider {

    public static final String X_RAPID_API_KEY = "X-RapidAPI-Key";
    public static final String X_RAPID_API_HOST = "X-RapidAPI-Host";

    private final CurrencyExchangeProperties properties;

    @Override
    public HttpEntity<Void> getRequest() {
        var headers = new HttpHeaders();
        headers.set(X_RAPID_API_KEY, properties.getKey());
        headers.set(X_RAPID_API_HOST, properties.getHost());

        return new HttpEntity<>(headers);
    }
}
