package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Driver;
import com.ride.admin.repo.DriverRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
 private final DriverRepo repo;
 public DriverController(DriverRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<List<Driver>> list(){
 return ApiResponse.ok(repo.findAll());
 }
}
