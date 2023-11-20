package com.sda.carrental.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // General Spring annotation allowing to use @Bean
@EnableWebSecurity // this is for spring security. We need to provide her 2 configurations - UserDetailsManager and Filter chain
@Profile("prod")
public class SecurityInMemory {

    @Bean
    //Here we are configuring Spring Security behavior
    //two things are most important - setting the authorisation type (Basic Auth) and configuring authorisation
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // we are using Basic Auth (user name nad password are passed in headers), we could also use different types like toke based on oAuth
        httpSecurity
                .httpBasic(Customizer.withDefaults());

        // disabling this security check to be able to use POSTs and PUTs
        httpSecurity
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        //Setting up authorization.
        //We are defining users with which roles have access to which endpoints
        httpSecurity
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .anyRequest()
                            .hasRole("USER");
                });

        return httpSecurity.build();
    }
}
