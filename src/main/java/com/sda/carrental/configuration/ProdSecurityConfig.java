package com.sda.carrental.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class ProdSecurityConfig {
    //SecurityFilterChain

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic(Customizer.withDefaults());

        httpSecurity
                .csrf(csrfConfigurer -> csrfConfigurer.disable());

        httpSecurity
                .authorizeHttpRequests(authorizationMatcher ->
                        authorizationMatcher
                                .requestMatchers(HttpMethod.POST, "/cars")
                                .hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/car_rentals")
                                .hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                );
        return httpSecurity.build();
    }
}
