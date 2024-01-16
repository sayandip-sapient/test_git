package com.bank_customer.bank_customer.security.auth;

import javax.management.relation.Role;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank_customer.bank_customer.security.config.JwtService;
import com.bank_customer.bank_customer.security.user.User;
import com.bank_customer.bank_customer.security.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AuthenticationService { 
     private final UserRepository repository;
//   private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .aadharNo(request.getAadharNo())
        // .aadharNo(request.aadhar_no())
       
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    // var refreshToken = jwtService.generateRefreshToken(user);
    // saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            // .refreGshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
  authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getAadharNo(),
            request.getPassword()
        )
    );
    System.out.println( authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getAadharNo(),
            request.getPassword()
        )
    ));
    System.out.println("12345");
    var user = repository.findByAadharNo(request.getAadharNo())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    // var refreshToken = jwtService.generateRefreshToken(user);
    // revokeAllUserTokens(user);
    // saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            // .refreshToken(refreshToken)
        .build();
  }
    
}
