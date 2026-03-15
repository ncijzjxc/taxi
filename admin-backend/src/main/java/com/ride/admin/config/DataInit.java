package com.ride.admin.config;

import com.ride.admin.entity.*;
import com.ride.admin.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataInit {
 @Bean
 CommandLineRunner init(AdminRepo adminRepo, PassengerRepo passengerRepo, DriverRepo driverRepo, VehicleRepo vehicleRepo, OrderRepo orderRepo, CityRepo cityRepo, FeedbackRepo feedbackRepo){
 return args -> {
 if (adminRepo.count()==0){
 Admin admin = new Admin();
 admin.setUsername("admin");
 admin.setPassword("123456");
 admin.setRole("SUPER");
 adminRepo.save(admin);
 }
 if (passengerRepo.count()==0){
 Passenger p = new Passenger();
 p.setName("张三"); p.setPhone("13800000000"); p.setRegisterTime(LocalDateTime.now()); p.setStatus("normal");
 passengerRepo.save(p);
 }
 if (driverRepo.count()==0){
 Driver d = new Driver();
 d.setName("李四"); d.setPhone("13900000000"); d.setLicenseNo("A12345"); d.setAuditStatus("approved"); d.setOnlineStatus("online");
 driverRepo.save(d);
 Vehicle v = new Vehicle(); v.setPlateNo("粤B12345"); v.setModel("大众"); v.setStatus("normal"); v.setDriverId(d.getId());
 vehicleRepo.save(v);
 OrderEntity o = new OrderEntity();
 o.setPassengerId(1L); o.setDriverId(d.getId()); o.setVehicleId(v.getId());
 o.setStartAddr("A地"); o.setEndAddr("B地"); o.setOrderStatus("completed"); o.setAmount(new BigDecimal("25.5")); o.setCreateTime(LocalDateTime.now());
 orderRepo.save(o);
 City c = new City(); c.setName("深圳"); c.setOpenStatus("open"); c.setOperateStatus("running");
 cityRepo.save(c);
 Feedback f = new Feedback(); f.setUserType("passenger"); f.setUserId(1L); f.setType("suggestion"); f.setContent("希望增加优惠券"); f.setStatus("pending"); f.setCreateTime(LocalDateTime.now());
 feedbackRepo.save(f);
 }
 };
 }
}


