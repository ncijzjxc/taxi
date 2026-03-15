package com.ride.admin.repo;

import com.ride.admin.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {}
