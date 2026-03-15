package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Passenger;
import com.ride.admin.repo.PassengerRepo;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
 private final PassengerRepo repo;
 public PassengerController(PassengerRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<Page<Passenger>> list(@RequestParam(defaultValue = "0") int page,
 @RequestParam(defaultValue = "10") int size,
 @RequestParam(required = false) String name){
 PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
 if (name!=null && !name.isEmpty()){
 return ApiResponse.ok(repo.findByNameContaining(name, pr));
 }
 return ApiResponse.ok(repo.findAll(pr));
 }

 @PostMapping
 public ApiResponse<Passenger> create(@RequestBody Passenger p){
 return ApiResponse.ok(repo.save(p));
 }

 @PutMapping("/{id}")
 public ApiResponse<Passenger> update(@PathVariable Long id, @RequestBody Passenger p){
 p.setId(id);
 return ApiResponse.ok(repo.save(p));
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 repo.deleteById(id);
 return ApiResponse.ok(null);
 }
}

