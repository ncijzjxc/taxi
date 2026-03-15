package com.ride.admin.repo;

import com.ride.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin, Long> {
 Optional<Admin> findByUsername(String username);
}
