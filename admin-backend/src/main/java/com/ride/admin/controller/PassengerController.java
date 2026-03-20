package com.ride.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Passenger;
import com.ride.admin.service.PassengerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
 private final PassengerService service;
 public PassengerController(PassengerService service){ this.service = service; }

 @GetMapping
 public ApiResponse<Page<Passenger>> list(@RequestParam(defaultValue = "1") int page,
 @RequestParam(defaultValue = "10") int size,
 @RequestParam(required = false) String name){
 Page<Passenger> p = new Page<>(page, size);
 if (name!=null && !name.isEmpty()){
 return ApiResponse.ok(service.lambdaQuery().like(Passenger::getName, name).page(p));
 }
 return ApiResponse.ok(service.page(p));
 }

 @PostMapping
 public ApiResponse<Passenger> create(@RequestBody Passenger p){
 if (p.getUsername() == null || p.getUsername().isEmpty()) {
   throw new RuntimeException("请输入账号");
 }
 if (p.getId() == null) {
   p.setId(generatePassengerId());
 }
 service.save(p);
 return ApiResponse.ok(p);
 }

 private long generatePassengerId() {
   while (true) {
     long id = 100000L + (long) (Math.random() * 900000L);
     if (service.getById(id) == null) {
       return id;
     }
   }
 }

 @PutMapping("/{id}")
 public ApiResponse<Passenger> update(@PathVariable Long id, @RequestBody Passenger p){
 p.setId(id);
 service.updateById(p);
 return ApiResponse.ok(p);
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 service.removeById(id);
 return ApiResponse.ok(null);
 }
}


