package com.min.task.currency;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyManagerTest {

    @Mock
    RestTemplate restTemplate;
    @Mock
    CurrencyExchangeProperties properties;
    @Mock
    RequestProvider requestProvider;

    @InjectMocks
    CurrencyManager victim;

    @Test
    void registerCurrencies_whenFound_register() {
        var requestMock = mock(HttpEntity.class);
        when(properties.getListUrl()).thenReturn("url");
        when(requestProvider.getRequest()).thenReturn(requestMock);
        when(restTemplate.exchange(URI.create("url"), HttpMethod.GET, requestMock, String.class))
                .thenReturn(ResponseEntity.ok("[\"EUR\",\"USD\"]"));

        assertThatNoException().isThrownBy(() -> victim.registerCurrencies());
    }

    @Test
    void registerCurrencies_whenResponseBodyNull_throwIllegalStateException() {
        var requestMock = mock(HttpEntity.class);
        when(properties.getListUrl()).thenReturn("url");
        when(requestProvider.getRequest()).thenReturn(requestMock);
        when(restTemplate.exchange(URI.create("url"), HttpMethod.GET, requestMock, String.class))
                .thenReturn(ResponseEntity.ok(null));

        assertThatThrownBy(() -> victim.registerCurrencies())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("support no currencies");
    }

    @Test
    void registerCurrencies_whenRestClientException_throwIllegalStateException() {
        var requestMock = mock(HttpEntity.class);
        when(properties.getListUrl()).thenReturn("url");
        when(requestProvider.getRequest()).thenReturn(requestMock);
        when(restTemplate.exchange(URI.create("url"), HttpMethod.GET, requestMock, String.class))
                .thenThrow(new RestClientException("testMessage"));

        assertThatThrownBy(() -> victim.registerCurrencies())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Cannot continue");
    }


}
