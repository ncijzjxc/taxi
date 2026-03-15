package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Driver;
import com.ride.admin.repo.DriverRepo;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
 private final DriverRepo repo;
 public DriverController(DriverRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<Page<Driver>> list(@RequestParam(defaultValue = "0") int page,
 @RequestParam(defaultValue = "10") int size,
 @RequestParam(required = false) String name){
 PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
 if (name!=null && !name.isEmpty()){
 return ApiResponse.ok(repo.findByNameContaining(name, pr));
 }
 return ApiResponse.ok(repo.findAll(pr));
 }

 @PostMapping
 public ApiResponse<Driver> create(@RequestBody Driver d){
 return ApiResponse.ok(repo.save(d));
 }

 @PutMapping("/{id}")
 public ApiResponse<Driver> update(@PathVariable Long id, @RequestBody Driver d){
 d.setId(id);
 return ApiResponse.ok(repo.save(d));
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 repo.deleteById(id);
 return ApiResponse.ok(null);
 }
}
