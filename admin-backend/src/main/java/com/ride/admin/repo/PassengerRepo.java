package com.ride.admin.repo;

import com.ride.admin.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {}
