package com.min.task.currency.conversion.request.provider;

import com.min.task.properties.CurrencyExchangeProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.min.task.currency.conversion.request.provider.CurrencyExchangeRequestProvider.X_RAPID_API_HOST;
import static com.min.task.currency.conversion.request.provider.CurrencyExchangeRequestProvider.X_RAPID_API_KEY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeRequestProviderTest {

    @Mock
    CurrencyExchangeProperties properties;

    @InjectMocks
    CurrencyExchangeRequestProvider victim;

    @Test
    void getRequest_returnsRequestWithHeaders() {
        when(properties.getHost()).thenReturn("testHost");
        when(properties.getKey()).thenReturn("testKey");

        var result = victim.getRequest();

        assertEquals(List.of("testHost"), result.getHeaders().get(X_RAPID_API_HOST));
        assertEquals(List.of("testKey"), result.getHeaders().get(X_RAPID_API_KEY));
    }

}
