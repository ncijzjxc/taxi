package com.ride.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Feedback;
import com.ride.admin.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
 private final FeedbackService service;
 public FeedbackController(FeedbackService service){ this.service = service; }

 @GetMapping
 public ApiResponse<Page<Feedback>> list(@RequestParam(defaultValue = "1") int page,
 @RequestParam(defaultValue = "10") int size,
 @RequestParam(required = false) String type){
 Page<Feedback> p = new Page<>(page, size);
 if (type!=null && !type.isEmpty()){
 return ApiResponse.ok(service.lambdaQuery().eq(Feedback::getType, type).page(p));
 }
 return ApiResponse.ok(service.page(p));
 }

 @PostMapping
 public ApiResponse<Feedback> create(@RequestBody Feedback f){
 service.save(f);
 return ApiResponse.ok(f);
 }

 @PutMapping("/{id}")
 public ApiResponse<Feedback> update(@PathVariable Long id, @RequestBody Feedback f){
 f.setId(id);
 service.updateById(f);
 return ApiResponse.ok(f);
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 service.removeById(id);
 return ApiResponse.ok(null);
 }
}


