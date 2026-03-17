package com.ride.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.OrderEntity;
import com.ride.admin.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
 private final OrderService service;
 public OrderController(OrderService service){ this.service = service; }

 @GetMapping
 public ApiResponse<Page<OrderEntity>> list(@RequestParam(defaultValue = "all") String status,
 @RequestParam(defaultValue = "1") int page,
 @RequestParam(defaultValue = "10") int size){
 Page<OrderEntity> p = new Page<>(page, size);
 if ("all".equals(status)) return ApiResponse.ok(service.page(p));
 // 兼容新老两种状态值：新为 1~4，老为字符串
 try {
   int s = Integer.parseInt(status);
   return ApiResponse.ok(service.lambdaQuery().eq(OrderEntity::getOrderStatus, s).page(p));
 } catch (Exception ignored) {
   return ApiResponse.ok(service.page(p));
 }
 }

 @PostMapping
 public ApiResponse<OrderEntity> create(@RequestBody OrderEntity o){
 service.save(o);
 return ApiResponse.ok(o);
 }

 @PutMapping("/{id}")
 public ApiResponse<OrderEntity> update(@PathVariable Long id, @RequestBody OrderEntity o){
 o.setId(id);
 service.updateById(o);
 return ApiResponse.ok(o);
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 service.removeById(id);
 return ApiResponse.ok(null);
 }
}


