package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Vehicle;
import com.ride.admin.repo.VehicleRepo;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
 private final VehicleRepo repo;
 public VehicleController(VehicleRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<Page<Vehicle>> list(@RequestParam(defaultValue = "0") int page,
 @RequestParam(defaultValue = "10") int size,
 @RequestParam(required = false) String plateNo){
 PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
 if (plateNo!=null && !plateNo.isEmpty()){
 return ApiResponse.ok(repo.findByPlateNoContaining(plateNo, pr));
 }
 return ApiResponse.ok(repo.findAll(pr));
 }

 @PostMapping
 public ApiResponse<Vehicle> create(@RequestBody Vehicle v){
 return ApiResponse.ok(repo.save(v));
 }

 @PutMapping("/{id}")
 public ApiResponse<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle v){
 v.setId(id);
 return ApiResponse.ok(repo.save(v));
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 repo.deleteById(id);
 return ApiResponse.ok(null);
 }
}
