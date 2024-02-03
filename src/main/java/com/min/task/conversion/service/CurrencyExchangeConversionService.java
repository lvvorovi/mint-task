package com.min.task.conversion.service;

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

    @Override
    public BigDecimal convert(TransactionRequest transactionRequest) {
        var uri = UriComponentsBuilder
                .fromUriString(properties.getExchangeUrl())
                .queryParam("from", transactionRequest.destinationCurrency())
                .queryParam("to", transactionRequest.sourceCurrency())
                .build()
                .toUri();

        var httpEntity = CurrencyExchangeRequestProvider.getRequest(properties);

        var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        return transactionRequest.amount()
                .multiply(new BigDecimal(Objects.requireNonNull(response.getBody())))
                .setScale(2, RoundingMode.UP);
    }
}
