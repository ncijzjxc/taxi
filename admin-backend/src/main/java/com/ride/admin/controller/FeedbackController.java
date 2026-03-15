package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Feedback;
import com.ride.admin.repo.FeedbackRepo;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
 private final FeedbackRepo repo;
 public FeedbackController(FeedbackRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<Page<Feedback>> list(@RequestParam(defaultValue = "0") int page,
 @RequestParam(defaultValue = "10") int size,
 @RequestParam(required = false) String type){
 PageRequest pr = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
 if (type!=null && !type.isEmpty()){
 return ApiResponse.ok(repo.findByType(type, pr));
 }
 return ApiResponse.ok(repo.findAll(pr));
 }

 @PostMapping
 public ApiResponse<Feedback> create(@RequestBody Feedback f){
 return ApiResponse.ok(repo.save(f));
 }

 @PutMapping("/{id}")
 public ApiResponse<Feedback> update(@PathVariable Long id, @RequestBody Feedback f){
 f.setId(id);
 return ApiResponse.ok(repo.save(f));
 }

 @DeleteMapping("/{id}")
 public ApiResponse<Void> delete(@PathVariable Long id){
 repo.deleteById(id);
 return ApiResponse.ok(null);
 }
}
