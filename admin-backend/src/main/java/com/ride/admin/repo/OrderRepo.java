package com.ride.admin.repo;

import com.ride.admin.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity, Long> {
 List<OrderEntity> findByOrderStatus(String orderStatus);
}
