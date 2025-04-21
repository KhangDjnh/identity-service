package com.devteria.indentity_service.configuration;


import com.devteria.indentity_service.entity.User;
import com.devteria.indentity_service.entity.Role;
import com.devteria.indentity_service.repository.RoleRepository;
import com.devteria.indentity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository,
                                        RoleRepository roleRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                // Lấy Role ADMIN từ DB
                Role adminRole = roleRepository.findById("ADMIN")
                        .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));

                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                log.info("Admin role found {}", adminRole.getName());
                log.info("role {}", roles);

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .isActive(true)
                        .build();

                userRepository.save(user);
                log.warn("Info: Admin user created with default password");
            }
        };
    }
}
