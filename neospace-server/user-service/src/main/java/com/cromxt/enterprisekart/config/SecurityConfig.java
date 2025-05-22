package com.cromxt.enterprisekart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cromxt.enterprisekart.auth.JwtAuthenticationFilter;
import com.cromxt.enterprisekart.service.JwtService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


  private final AuthenticationProvider authenticationProvider;
  private final JwtService jwtService;

  private static final String[] AUTH_WHITELIST = {
      "/api/v1/auth/**",
      "/api/v1/demo/**"
  };

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, Environment environment) throws Exception {
    String authUrl = environment.getProperty("AUTH_URL");
    JwtAuthenticationFilter jwtFilter = new JwtAuthenticationFilter(jwtService, authUrl);
    return httpSecurity
        .csrf(CsrfConfigurer::disable)
        .httpBasic(HttpBasicConfigurer::disable)
        .formLogin(FormLoginConfigurer::disable)
        .cors(CorsConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authroize -> authroize.requestMatchers(AUTH_WHITELIST).permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .authenticationProvider(authenticationProvider)
        .build();
  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
    return configuration.getAuthenticationManager();
  }
}
