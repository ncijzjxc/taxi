package com.ride.admin.repo;

import com.ride.admin.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
 Page<OrderEntity> findByOrderStatus(String orderStatus, Pageable pageable);
}
