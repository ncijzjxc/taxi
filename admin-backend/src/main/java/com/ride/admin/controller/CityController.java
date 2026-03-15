package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.City;
import com.ride.admin.repo.CityRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
 private final CityRepo repo;
 public CityController(CityRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<List<City>> list(){
 return ApiResponse.ok(repo.findAll());
 }
}
