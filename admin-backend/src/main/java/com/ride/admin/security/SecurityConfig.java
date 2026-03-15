package com.ride.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 http.cors().and().csrf().disable()
 .authorizeRequests(auth -> auth
 .antMatchers("/api/auth/login", "/error").permitAll()
 .antMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
 .anyRequest().authenticated()
 )
 .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
 .httpBasic(Customizer.withDefaults());
 return http.build();
 }
}


