package com.ride.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.PriceRule;
import com.ride.admin.service.PriceRuleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/price-rules")
public class PriceRuleController {
 private final PriceRuleService service;
 public PriceRuleController(PriceRuleService service){ this.service = service; }

 @GetMapping
 public ApiResponse<Page<PriceRule>> list(@RequestParam Long cityId,
 @RequestParam(required = false) String carType,
 @RequestParam(defaultValue = "1") int page,
 @RequestParam(defaultValue = "10") int size){
 Page<PriceRule> p = new Page<>(page, size);
 com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper<PriceRule> q =
   service.lambdaQuery().eq(PriceRule::getCityId, cityId);
 if (carType!=null && !carType.isEmpty()) q.eq(PriceRule::getCarType, carType);
 return ApiResponse.ok(q.orderByDesc(PriceRule::getEffectiveTime).page(p));
 }

 @GetMapping("/latest")
 public ApiResponse<PriceRule> latest(@RequestParam Long cityId,
                                      @RequestParam String carType){
 if (cityId == null) throw new RuntimeException("cityId required");
 if (carType == null || carType.isEmpty()) throw new RuntimeException("carType required");
 PriceRule r = service.lambdaQuery()
   .eq(PriceRule::getCityId, cityId)
   .eq(PriceRule::getCarType, carType)
   .eq(PriceRule::getStatus, "enabled")
   .orderByDesc(PriceRule::getEffectiveTime)
   .orderByDesc(PriceRule::getId)
   .last("limit 1")
   .one();
 if (r == null) throw new RuntimeException("price rule not found");
 return ApiResponse.ok(r);
 }

 @PostMapping
 public ApiResponse<PriceRule> create(@RequestBody PriceRule r){
 if (r.getCityId()==null) throw new RuntimeException("cityId required");
 service.save(r);
 return ApiResponse.ok(r);
 }

 @PutMapping("/{id}")
 public ApiResponse<PriceRule> update(@PathVariable Long id, @RequestBody PriceRule r){
 r.setId(id);
 service.updateById(r);
 return ApiResponse.ok(r);
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 service.removeById(id);
 return ApiResponse.ok(null);
 }
}
