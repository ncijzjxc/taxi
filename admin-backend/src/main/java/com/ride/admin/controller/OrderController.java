package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.OrderEntity;
import com.ride.admin.repo.OrderRepo;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
 private final OrderRepo repo;
 public OrderController(OrderRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<Page<OrderEntity>> list(@RequestParam(defaultValue = "all") String status,
 @RequestParam(defaultValue = "0") int page,
 @RequestParam(defaultValue = "10") int size){
 PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
 if ("all".equals(status)) return ApiResponse.ok(repo.findAll(pr));
 return ApiResponse.ok(repo.findByOrderStatus(status, pr));
 }

 @PostMapping
 public ApiResponse<OrderEntity> create(@RequestBody OrderEntity o){
 return ApiResponse.ok(repo.save(o));
 }

 @PutMapping("/{id}")
 public ApiResponse<OrderEntity> update(@PathVariable Long id, @RequestBody OrderEntity o){
 o.setId(id);
 return ApiResponse.ok(repo.save(o));
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 repo.deleteById(id);
 return ApiResponse.ok(null);
 }
}
