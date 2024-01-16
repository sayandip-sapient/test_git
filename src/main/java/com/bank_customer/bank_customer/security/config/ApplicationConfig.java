package com.bank_customer.bank_customer.security.config;



import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bank_customer.bank_customer.security.user.User;
import com.bank_customer.bank_customer.security.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UserRepository repository;
//   private final User user;

  @Bean
  public UserDetailsService userDetailsService() {
    return username ->repository.findByAadharNo(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    System.out.println(userDetailsService());
    System.out.println("userDetailsService()");
    authProvider.setUserDetailsService(userDetailsService());
    // System.out.println(userDetailsService());
    // System.out.println(userDetailsService());
    //  System.out.println("userDetailsService()");

    authProvider.setPasswordEncoder(passwordEncoder());
    System.out.println(authProvider);
     System.out.println("authProvider");
    return authProvider;
  }

//   @Bean
//   public AuditorAware<Integer> auditorAware() {
//     return new ApplicationAuditAware();
//   }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}

