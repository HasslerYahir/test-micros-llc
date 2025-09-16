package com.devsutest.clients_services.domain.usecase;

import com.devsutest.clients_services.application.exceptions.UserExistException;
import com.devsutest.clients_services.domain.gateways.ClientRepository;
import com.devsutest.clients_services.domain.gateways.CredentialRepository;
import com.devsutest.clients_services.domain.models.Client;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterClientUseCaseTest {
    private final ClientRepository clientRepository = mock(ClientRepository.class);
    private final CredentialRepository credentialRepository = mock(CredentialRepository.class);
    private final RegisterClientUseCase useCase = new RegisterClientUseCase(clientRepository, credentialRepository);

    @Test
    void executeThrowsWhenClientAlreadyExists() {
        Client client = Client.builder()
                .identification("123")
                .password("plain")
                .build();

        when(clientRepository.findByIdentification("123"))
                .thenReturn(Optional.of(client));

        assertThrows(UserExistException.class, () -> useCase.execute(client));
        verify(clientRepository, never()).save(any());
    }

    @Test
    void executeSavesClientWithEncodedPassword() {
        Client client = Client.builder()
                .identification("123")
                .password("plain")
                .build();

        when(clientRepository.findByIdentification("123"))
                .thenReturn(Optional.empty());
        when(credentialRepository.encode("plain")).thenReturn("encoded");

        useCase.execute(client);

        verify(clientRepository).save(argThat(saved ->
                saved.getPassword().equals("encoded")
                        && saved.isStatus()
                        && saved.getIdentification().equals("123")
        ));
    }

}