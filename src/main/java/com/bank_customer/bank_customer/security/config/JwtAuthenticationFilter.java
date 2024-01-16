package com.bank_customer.bank_customer.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // @Autowired
     private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
//   private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain filterChain)
            throws ServletException, IOException {

    //              if (request.getServletPath().contains("/api/v1/auth")) {
    //   filterChain.doFilter(request, response);
    //   return;
    // }
    // System.out.println(jwtService.extractUsername("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwMTIiLCJpYXQiOjE3MDUzMTA5MTAsImV4cCI6ODY0MTcwNTMxMDkxMH0.jFmIjV6GkmeX2DM8MpBAJ1CC9UqdoW5f9YzZ-4pUi-M"));
    // System.out.println("TOKEN--123");

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userAadharNo;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);
    userAadharNo = jwtService.extractUsername(jwt);
    if (userAadharNo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        // System.out.println(this.userDetailsService);
        //      System.out.println("this.userDetailsService");
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userAadharNo);
    //   var isTokenValid = tokenRepository.findByToken(jwt)
    //       .map(t -> !t.isExpired() && !t.isRevoked())
    //       .orElse(false);
      if (jwtService.isTokenValid(jwt, userDetails) ) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
        
    }
    
}
