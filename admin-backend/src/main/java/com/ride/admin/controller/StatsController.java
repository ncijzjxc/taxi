package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.City;
import com.ride.admin.entity.Driver;
import com.ride.admin.entity.OrderEntity;
import com.ride.admin.service.CityService;
import com.ride.admin.service.DriverService;
import com.ride.admin.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
 private final OrderService orderService;
 private final DriverService driverService;
 private final CityService cityService;

 public StatsController(OrderService orderService, DriverService driverService, CityService cityService){
 this.orderService = orderService; this.driverService = driverService; this.cityService = cityService;
 }

 @GetMapping("/overview")
 public ApiResponse<Map<String,Object>> overview(){
 List<OrderEntity> orders = orderService.list();
 long total = orders.size();
 long valid = orders.stream().filter(o -> o.getOrderStatus() != null && o.getOrderStatus() != 0).count();
 BigDecimal income = orders.stream()
   .filter(o -> o.getOrderStatus() != null && o.getOrderStatus() == 4)
   .map(o->o.getAmount()==null?BigDecimal.ZERO:o.getAmount())
   .reduce(BigDecimal.ZERO, BigDecimal::add);
 long activeDrivers = driverService.lambdaQuery().eq(Driver::getOnlineStatus, "online").count();
 Map<String,Object> map = new HashMap<>();
 map.put("totalOrders", total);
 map.put("validOrders", valid);
 map.put("totalIncome", income);
 map.put("activeDrivers", activeDrivers);
 return ApiResponse.ok(map);
 }

 @GetMapping("/cities")
 public ApiResponse<List<Map<String,Object>>> cities(){
 List<City> cities = cityService.list();
 List<Map<String,Object>> list = new ArrayList<>();
 for (City c: cities){
 Map<String,Object> m = new HashMap<>();
 m.put("city", c.getName());
 // 按城市过滤订单
 long cityOrderCount = orderService.lambdaQuery().eq(OrderEntity::getCityId, c.getId()).count();
 BigDecimal cityIncome = orderService.lambdaQuery()
   .eq(OrderEntity::getCityId, c.getId())
   .eq(OrderEntity::getOrderStatus, 4)
   .list()
   .stream()
   .map(o->o.getAmount()==null?BigDecimal.ZERO:o.getAmount())
   .reduce(BigDecimal.ZERO, BigDecimal::add);
 // 按城市过滤司机
 long cityDriverCount = driverService.lambdaQuery()
   .eq(Driver::getCityId, c.getId())
   .eq(Driver::getOnlineStatus, "online")
   .count();
 m.put("totalOrders", cityOrderCount);
 m.put("totalIncome", cityIncome);
 m.put("activeDrivers", cityDriverCount);
 list.add(m);
 }
 return ApiResponse.ok(list);
 }
}


