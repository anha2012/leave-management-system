package com.bugfearless.leave_management_system.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bugfearless.leave_management_system.model.User;
import com.bugfearless.leave_management_system.repository.UserRepository;
import com.bugfearless.leave_management_system.utils.enums.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppInitConfig {
    private final PasswordEncoder passwordEncoder;
    
    @Bean
    ApplicationRunner applicationRunner(
        UserRepository userRepository
    ) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {

                User user = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .build();
                userRepository.save(user); 
            }
        };
    }
}
