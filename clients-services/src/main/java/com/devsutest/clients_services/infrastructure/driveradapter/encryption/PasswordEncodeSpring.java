package com.devsutest.clients_services.infrastructure.driveradapter.encryption;

import com.devsutest.clients_services.domain.gateways.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PasswordEncodeSpring implements CredentialRepository {
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
