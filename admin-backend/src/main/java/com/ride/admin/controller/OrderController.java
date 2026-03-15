package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.OrderEntity;
import com.ride.admin.repo.OrderRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
 private final OrderRepo repo;
 public OrderController(OrderRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<List<OrderEntity>> list(@RequestParam(defaultValue = "all") String status){
 if ("all".equals(status)) return ApiResponse.ok(repo.findAll());
 String real = switch (status) {
 case "ongoing" -> "ongoing";
 case "completed" -> "completed";
 case "canceled" -> "canceled";
 default -> status;
 };
 return ApiResponse.ok(repo.findByOrderStatus(real));
 }
}
