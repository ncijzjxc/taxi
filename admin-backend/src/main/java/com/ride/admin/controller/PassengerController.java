package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Passenger;
import com.ride.admin.repo.PassengerRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
 private final PassengerRepo repo;
 public PassengerController(PassengerRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<List<Passenger>> list(){
 return ApiResponse.ok(repo.findAll());
 }
}
