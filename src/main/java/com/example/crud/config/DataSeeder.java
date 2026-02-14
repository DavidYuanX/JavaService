package com.example.crud.config;

import com.example.crud.model.AuthUser;
import com.example.crud.repository.AuthUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (authUserRepository.findByUsername("admin").isEmpty()) {
            AuthUser admin = new AuthUser("admin", passwordEncoder.encode("admin123"));
            authUserRepository.save(admin);
        }
    }
}
