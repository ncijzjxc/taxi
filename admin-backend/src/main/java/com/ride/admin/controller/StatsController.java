package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Driver;
import com.ride.admin.entity.OrderEntity;
import com.ride.admin.repo.CityRepo;
import com.ride.admin.repo.DriverRepo;
import com.ride.admin.repo.OrderRepo;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
 private final OrderRepo orderRepo;
 private final DriverRepo driverRepo;
 private final CityRepo cityRepo;

 public StatsController(OrderRepo orderRepo, DriverRepo driverRepo, CityRepo cityRepo){
 this.orderRepo = orderRepo; this.driverRepo = driverRepo; this.cityRepo = cityRepo;
 }

 @GetMapping("/overview")
 public ApiResponse<Map<String,Object>> overview(){
 List<OrderEntity> orders = orderRepo.findAll();
 long total = orders.size();
 long valid = orders.stream().filter(o->!"canceled".equals(o.getOrderStatus())).count();
 BigDecimal income = orders.stream().map(o->o.getAmount()==null?BigDecimal.ZERO:o.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
 long activeDrivers = driverRepo.findAll().stream().filter(d->"online".equals(d.getOnlineStatus())).count();
 Map<String,Object> map = new HashMap<>();
 map.put("totalOrders", total);
 map.put("validOrders", valid);
 map.put("totalIncome", income);
 map.put("activeDrivers", activeDrivers);
 return ApiResponse.ok(map);
 }

 @GetMapping("/cities")
 public ApiResponse<List<Map<String,Object>>> cities(){
 var cities = cityRepo.findAll();
 List<Map<String,Object>> list = new ArrayList<>();
 for (var c: cities){
 Map<String,Object> m = new HashMap<>();
 m.put("city", c.getName());
 m.put("totalOrders", orderRepo.count());
 m.put("totalIncome", orderRepo.findAll().stream().map(o->o.getAmount()==null?BigDecimal.ZERO:o.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
 m.put("activeDrivers", driverRepo.findAll().stream().filter(d->"online".equals(d.getOnlineStatus())).count());
 list.add(m);
 }
 return ApiResponse.ok(list);
 }
}
