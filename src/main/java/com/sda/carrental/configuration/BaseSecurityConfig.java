package com.sda.carrental.configuration;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class BaseSecurityConfig {

    //Create userDetailsManager to handle users
    @Bean
    public UserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager();
    }

    //Create and safe users using Initializing Bean to add some users to UserDetailsManager
    @Bean
    public InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
        return () -> {
            System.out.println("Hello from init bean");

            UserDetails user = User
                    .builder()
                    .passwordEncoder(password ->
                            PasswordEncoderFactories
                                    .createDelegatingPasswordEncoder()
                                    .encode(password))
                    .username("user")
                    .password("password")
                    .roles("USER")
                    .build();
            userDetailsManager.createUser(user);

            UserDetails admin = User
                    .builder()
                    .passwordEncoder(password ->
                            PasswordEncoderFactories
                                    .createDelegatingPasswordEncoder()
                                    .encode(password))
                    .username("admin")
                    .password("password")
                    .roles("ADMIN")
                    .build();
            userDetailsManager.createUser(admin);
        };
    }
}
