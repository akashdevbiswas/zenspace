package com.cromxt.enterprisekart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cromxt.enterprisekart.repository.UserRepository;
import com.cromxt.enterprisekart.service.JwtService;
import com.cromxt.enterprisekart.service.impl.JwtServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationConfig {

  @Bean
  UserDetailsService userDetailsService(UserRepository userRepository){
    return username -> userRepository.findByUsername(username).orElseThrow(()->{
      log.error("Username not found {}", username);
      return new UsernameNotFoundException(String.format("username not found %s", username));
    });
  }

  @Bean
  JwtService jwtService(Environment environment){
    String secret = environment.getProperty("JWT_SECRET", String.class);;
    Long expiration = environment.getProperty("JWT_EXPIRATION", Long.class);;
    return new JwtServiceImpl(secret , expiration);
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }
  
}
