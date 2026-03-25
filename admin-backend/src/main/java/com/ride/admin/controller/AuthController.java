package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Admin;
import com.ride.admin.entity.LoginLog;
import com.ride.admin.mapper.AdminMapper;
import com.ride.admin.security.JwtUtil;
import com.ride.admin.service.LoginLogService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
 private final AdminMapper adminMapper;
 private final LoginLogService loginLogService;
 public AuthController(AdminMapper adminMapper, LoginLogService loginLogService){
  this.adminMapper = adminMapper;
  this.loginLogService = loginLogService;
 }

 @PostMapping("/login")
 public ApiResponse<Map<String,String>> login(@RequestBody LoginReq req, HttpServletRequest request){
 if (req.getUsername() == null || req.getUsername().isEmpty()) {
  writeLoginLog("admin", null, null, 0, "请输入账号", request);
  throw new RuntimeException("请输入账号");
 }
 if (req.getPassword() == null || req.getPassword().isEmpty()) {
  writeLoginLog("admin", null, req.getUsername(), 0, "请输入密码", request);
  throw new RuntimeException("请输入密码");
 }
 Admin admin = adminMapper.selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Admin>()
 .eq("username", req.getUsername()));
 if (admin==null) {
  writeLoginLog("admin", null, req.getUsername(), 0, "用户不存在", request);
  throw new RuntimeException("用户不存在");
 }
 if (!admin.getPassword().equals(req.getPassword())) {
  writeLoginLog("admin", admin.getId(), req.getUsername(), 0, "用户名或密码错误", request);
  throw new RuntimeException("用户名或密码错误");
 }
 String token = JwtUtil.generateToken(admin.getUsername());
 Map<String,String> map = new HashMap<>();
 map.put("token", token);
 writeLoginLog("admin", admin.getId(), req.getUsername(), 1, "登录成功", request);
 return ApiResponse.ok(map);
 }

 private void writeLoginLog(String userType, Long userId, String username, Integer success, String message, HttpServletRequest request) {
  try {
   LoginLog log = new LoginLog();
   log.setUserType(userType);
   log.setUserId(userId);
   log.setUsername(username);
   log.setSuccess(success);
   log.setMessage(message);
   log.setIp(request == null ? null : request.getRemoteAddr());
   log.setLoginTime(LocalDateTime.now());
   loginLogService.save(log);
  } catch (Exception ignore) {
  }
 }

 @Data
 public static class LoginReq {
 private String username;
 private String password;
 }
}


