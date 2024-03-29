package com.min.task.currency;

import com.min.task.currency.conversion.request.provider.RequestProvider;
import com.min.task.properties.CurrencyExchangeProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class CurrencyManager {

    private static final List<String> CURRENCY_LIST = new ArrayList<>();

    private final RestTemplate restTemplate;
    private final CurrencyExchangeProperties properties;
    private final RequestProvider requestProvider;

    public static List<String> getCurrencyList() {
        return List.copyOf(CURRENCY_LIST);
    }

    @PostConstruct
    public void registerCurrencies() {
        var uri = UriComponentsBuilder
                .fromUriString(properties.getListUrl())
                .build()
                .toUri();

        var httpEntity = requestProvider.getRequest();

        try {
            var response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
            if (Objects.isNull(response.getBody())) {
                throw new IllegalStateException("Exchange Rate Api support no currencies");
            }
            var currencyList = Arrays.stream(response.getBody().replace("[", "")
                            .replace("]", "")
                            .replace("\"", "")
                            .split(","))
                    .toList();

            CURRENCY_LIST.addAll(currencyList);
        } catch (RestClientException ex) {
            log.error("Exception while fetching currency list from api", ex);
            throw new IllegalStateException("Cannot continue without supported currencies");
        }

    }

}
