package com.example.rest_api_assignment.config.securityConfig;

import com.example.rest_api_assignment.filter.DummyJwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private DummyJwtFilter dummyJwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws  Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/auth/login")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .httpBasic(Customizer.withDefaults());
        http.addFilterBefore(dummyJwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}