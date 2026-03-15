package com.ride.admin.repo;

import com.ride.admin.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
 Page<Vehicle> findByPlateNoContaining(String plateNo, Pageable pageable);
}
