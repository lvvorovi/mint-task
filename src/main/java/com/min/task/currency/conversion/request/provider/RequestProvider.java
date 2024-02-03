package com.min.task.currency.conversion.request.provider;

import org.springframework.http.HttpEntity;

public interface RequestProvider {

    HttpEntity<Void> getRequest();

}
