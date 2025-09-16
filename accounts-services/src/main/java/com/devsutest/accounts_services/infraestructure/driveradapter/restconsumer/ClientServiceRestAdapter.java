package com.devsutest.accounts_services.infraestructure.driveradapter.restconsumer;

import com.devsutest.accounts_services.domain.gateways.ClientGateway;
import com.devsutest.accounts_services.domain.model.Client;
import com.devsutest.accounts_services.infraestructure.driveradapter.restconsumer.config.RestClientAdapter;
import com.devsutest.accounts_services.infraestructure.driveradapter.restconsumer.dto.ClientDTO;
import com.devsutest.accounts_services.infraestructure.driveradapter.restconsumer.mappers.ClientMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ClientServiceRestAdapter implements ClientGateway {
    private final RestClientAdapter restClient;
    private final ClientMapper clientMapper;
    @Value("${rest-consumer.client-service-base-path}")
    private String urlBaseClient;
    @Value("${rest-consumer.client-service-get-user-path}")
    private String getClientPath;

    @Override
    @CircuitBreaker(name = "client-service")
    public Optional<Client> findById(String id) {
        ClientDTO clientDTO = restClient.get(getClientId(id), ClientDTO.class);

        return Optional.of(clientMapper.toDomain(clientDTO));
    }

    private String getClientId(String id) {
        return String.format(urlBaseClient + getClientPath, id);
    }
}
