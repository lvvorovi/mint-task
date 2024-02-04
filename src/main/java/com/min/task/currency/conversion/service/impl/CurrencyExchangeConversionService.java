package com.min.task.currency.conversion.service.impl;

import com.min.task.currency.conversion.request.provider.RequestProvider;
import com.min.task.currency.conversion.service.ConversionService;
import com.min.task.properties.CurrencyExchangeProperties;
import com.min.task.transaction.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeConversionService implements ConversionService {

    private final RestTemplate restTemplate;
    private final CurrencyExchangeProperties properties;
    private final RequestProvider requestProvider;

    @Override
    public BigDecimal convertToSourceAmount(TransactionRequest transactionRequest) {
        var uri = UriComponentsBuilder
                .fromUriString(properties.getExchangeUrl())
                .queryParam("from", transactionRequest.destinationCurrency())
                .queryParam("to", transactionRequest.sourceCurrency())
                .build()
                .toUri();

        var httpEntity = requestProvider.getRequest();

         /*
          *   TODO Implement try/catch with resilience4j, retry, and fallback to get latest from DB.
          *     rates are being saved when loaded from API with id as currency pair,
          *     overriding previously saved values.
          *     Not implemented due to lack of time.
          */
        var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        return transactionRequest.amount()
                .multiply(new BigDecimal(Objects.requireNonNull(response.getBody())))
                .setScale(2, RoundingMode.UP);
    }
}
