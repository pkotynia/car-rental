package com.sda.carrental.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("test")
public class TestSecurityConfig {

    //SecurityFilterChain, zachowanie naszego spring security
    @Bean
    public SecurityFilterChain testFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrfConfigurer -> csrfConfigurer.disable());

        httpSecurity
                .authorizeHttpRequests(authorizationMatcher ->
                        authorizationMatcher
                                .anyRequest()
                                .permitAll()
                );

        return httpSecurity.build();
    }
}
