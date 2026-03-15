package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Admin;
import com.ride.admin.repo.AdminRepo;
import com.ride.admin.security.JwtUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
 private final AdminRepo adminRepo;
 public AuthController(AdminRepo adminRepo){ this.adminRepo = adminRepo; }

 @PostMapping("/login")
 public ApiResponse<Map<String,String>> login(@RequestBody LoginReq req){
 Admin admin = adminRepo.findByUsername(req.getUsername()).orElseThrow(() -> new RuntimeException("user not found"));
 if (!admin.getPassword().equals(req.getPassword())) throw new RuntimeException("password error");
 String token = JwtUtil.generateToken(admin.getUsername());
 Map<String,String> map = new HashMap<>();
 map.put("token", token);
 return ApiResponse.ok(map);
 }

 @Data
 public static class LoginReq {
 private String username;
 private String password;
 }
}

