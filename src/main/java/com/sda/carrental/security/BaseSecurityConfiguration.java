package com.sda.carrental.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration // General Spring annotation allowing to use @Bean
@EnableWebSecurity
// this is for spring security. We need to provide her 2 configurations - UserDetailsManager and Filter chain
public class BaseSecurityConfiguration {

    @Bean
    //In context of user we are working with username, password, role and activation
    public UserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    // this is general mechanism allowing us to do the post initialization work
    // in our case are adding user to userDetailsManager
    // but we could also add something to DB and so on
    public InitializingBean initializingBean(UserDetailsManager userDetailsManager) {
        return () -> {
            System.out.println("Hello form initializer - lambda");

            UserDetails user = User
                    .builder()
                    .passwordEncoder(password ->
                            PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password))
                    .password("password")
                    .username("user")
                    .roles("USER")
                    .build();

            userDetailsManager.createUser(user);
        };

    }
}
