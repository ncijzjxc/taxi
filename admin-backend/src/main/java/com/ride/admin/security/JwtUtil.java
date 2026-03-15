package com.ride.admin.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {
 private static final String SECRET = "taxi-admin-secret";
 private static final long EXP =1000 *60 *60 *6;

 public static String generateToken(String username){
 return Jwts.builder()
 .setSubject(username)
 .setIssuedAt(new Date())
 .setExpiration(new Date(System.currentTimeMillis()+EXP))
 .signWith(SignatureAlgorithm.HS256, SECRET)
 .compact();
 }

 public static String parse(String token){
 return Jwts.parser().setSigningKey(SECRET)
 .parseClaimsJws(token).getBody().getSubject();
 }
}

