package com.bank_customer.bank_customer.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
// @EnableMethodSecurity

public class SecurityConfiguration  {

    private static final SessionCreationPolicy STATELESS = SessionCreationPolicy.STATELESS;
         private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     
// System.out.println("authenticationProvider");
// System.out.println(authenticationProvider);
        http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(req ->
                req.requestMatchers("/api/auth/**")
                        .permitAll()
                        
                        .anyRequest()
                        .authenticated()
                       
        )
        .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
        .authenticationProvider(authenticationProvider)
       
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
      


return http.build();

                 


              
                
                
                
                
                
                }
         
      
    }

