package com.devsutest.accounts_services.infraestructure.driveradapter.restconsumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Component
@Slf4j
@RequiredArgsConstructor
public class RestClientAdapter {
    private final RestClient restClient;

    public <T> T get(String url, Class<T> clazz) {
        log.info("start get service by url [{}]", url);
        return restClient
                .get()
                .uri(URI.create(url))
                .retrieve()
                .body(clazz);
    }
}
