package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Admin;
import com.ride.admin.mapper.AdminMapper;
import com.ride.admin.security.JwtUtil;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
 private final AdminMapper adminMapper;
 public AuthController(AdminMapper adminMapper){ this.adminMapper = adminMapper; }

 @PostMapping("/login")
 public ApiResponse<Map<String,String>> login(@RequestBody LoginReq req){
 Admin admin = adminMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Admin>()
 .eq("username", req.getUsername()));
 if (admin==null) throw new RuntimeException("user not found");
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


