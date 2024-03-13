package com.example.vk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests(auth -> auth
                        .requestMatchers("/user/new").permitAll()
                        .requestMatchers("/api/posts/**").hasAnyAuthority("ROLE_POSTS","ROLE_ADMIN")
                        .requestMatchers("/api/albums/**").hasAnyAuthority("ROLE_ALBUMS","ROLE_ADMIN")
                        .requestMatchers("/api/users/**").hasAnyAuthority("ROLE_USERS","ROLE_ADMIN")
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())
                .build();
    }

}
