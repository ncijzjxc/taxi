package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.City;
import com.ride.admin.repo.CityRepo;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CityController {
 private final CityRepo repo;
 public CityController(CityRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<Page<City>> list(@RequestParam(defaultValue = "0") int page,
 @RequestParam(defaultValue = "10") int size,
 @RequestParam(required = false) String name){
 PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
 if (name!=null && !name.isEmpty()){
 return ApiResponse.ok(repo.findByNameContaining(name, pr));
 }
 return ApiResponse.ok(repo.findAll(pr));
 }

 @PostMapping
 public ApiResponse<City> create(@RequestBody City c){
 return ApiResponse.ok(repo.save(c));
 }

 @PutMapping("/{id}")
 public ApiResponse<City> update(@PathVariable Long id, @RequestBody City c){
 c.setId(id);
 return ApiResponse.ok(repo.save(c));
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 repo.deleteById(id);
 return ApiResponse.ok(null);
 }
}

