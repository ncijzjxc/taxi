package com.ride.admin.repo;

import com.ride.admin.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Long> {
 Page<Driver> findByNameContaining(String name, Pageable pageable);
}
