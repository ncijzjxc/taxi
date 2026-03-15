package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Vehicle;
import com.ride.admin.repo.VehicleRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
 private final VehicleRepo repo;
 public VehicleController(VehicleRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<List<Vehicle>> list(){
 return ApiResponse.ok(repo.findAll());
 }
}
