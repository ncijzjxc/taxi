package com.ride.admin.repo;

import com.ride.admin.entity.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
 Page<Feedback> findByType(String type, Pageable pageable);
}
