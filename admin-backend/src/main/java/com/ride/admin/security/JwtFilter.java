package com.ride.admin.security;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

 @Override
 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
 throws ServletException, IOException {
 String path = request.getRequestURI();
 if (path.contains("/auth/login")) { chain.doFilter(request, response); return; }
 String header = request.getHeader("Authorization");
 if (header != null && header.startsWith("Bearer ")) {
 try {
 String username = JwtUtil.parse(header.substring(7));
 org.springframework.security.authentication.UsernamePasswordAuthenticationToken auth =
 new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(username, null, java.util.Collections.emptyList());
 org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(auth);
 chain.doFilter(request, response);
 return;
 } catch (Exception ignored) {}
 }
 response.setStatus(401);
 }
}


