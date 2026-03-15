package com.ride.admin.repo;

import com.ride.admin.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver, Long> {}
