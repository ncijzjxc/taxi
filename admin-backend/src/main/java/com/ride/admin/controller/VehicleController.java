package com.ride.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Vehicle;
import com.ride.admin.service.VehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
 private final VehicleService service;
 public VehicleController(VehicleService service){ this.service = service; }

 @GetMapping
 public ApiResponse<Page<Vehicle>> list(@RequestParam(defaultValue = "1") int page,
 @RequestParam(defaultValue = "10") int size,
 @RequestParam(required = false) String plateNo){
 Page<Vehicle> p = new Page<>(page, size);
 if (plateNo!=null && !plateNo.isEmpty()){
 return ApiResponse.ok(service.lambdaQuery().like(Vehicle::getPlateNo, plateNo).page(p));
 }
 return ApiResponse.ok(service.page(p));
 }

 @PostMapping
 public ApiResponse<Vehicle> create(@RequestBody Vehicle v){
 service.save(v);
 return ApiResponse.ok(v);
 }

 @PutMapping("/{id}")
 public ApiResponse<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle v){
 v.setId(id);
 service.updateById(v);
 return ApiResponse.ok(v);
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 service.removeById(id);
 return ApiResponse.ok(null);
 }
}


