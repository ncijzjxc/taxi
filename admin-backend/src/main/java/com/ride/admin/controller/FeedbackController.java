package com.ride.admin.controller;

import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Feedback;
import com.ride.admin.repo.FeedbackRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
 private final FeedbackRepo repo;
 public FeedbackController(FeedbackRepo repo){ this.repo = repo; }

 @GetMapping
 public ApiResponse<List<Feedback>> list(){
 return ApiResponse.ok(repo.findAll());
 }
}
