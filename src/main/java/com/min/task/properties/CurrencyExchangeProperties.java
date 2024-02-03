package com.min.task.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "api.currency-exchange")
@Component
@Validated
@Data
public class CurrencyExchangeProperties {

    @NotBlank
    private String exchangeUrl;
    @NotBlank
    private String listUrl;
    @NotBlank
    private String key;
    @NotBlank
    private String host;

}
