package com.min.task.currency.conversion.service.impl;

import com.min.task.currency.conversion.request.provider.RequestProvider;
import com.min.task.properties.CurrencyExchangeProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;

import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeConversionServiceTest {

    @Mock
    RestTemplate restTemplate;
    @Mock
    CurrencyExchangeProperties properties;
    @Mock
    RequestProvider requestProvider;

    @InjectMocks
    CurrencyExchangeConversionService victim;

    @Test
    void convertToSourceAmount_whenRequested_thenReturned() {
        var transactionRequest = testTransactionRequest();
        var requestMock = mock(HttpEntity.class);
        var expected = BigDecimal.valueOf(100.00).setScale(2, RoundingMode.UP);
        when(properties.getExchangeUrl()).thenReturn("url");
        when(requestProvider.getRequest()).thenReturn(requestMock);
        when(restTemplate.exchange(URI.create("url?from=USD&to=EUR"), HttpMethod.GET, requestMock, String.class))
                .thenReturn(ResponseEntity.ok(BigDecimal.TEN.toPlainString()));

        var result = victim.convertToSourceAmount(transactionRequest);

        assertEquals(expected, result);
    }
}
