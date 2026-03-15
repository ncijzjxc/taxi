package com.ride.admin.repo;

import com.ride.admin.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Long> {}
