package com.ride.admin.config;

import com.ride.admin.entity.*;
import com.ride.admin.mapper.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataInit {
 @Bean
 CommandLineRunner init(AdminMapper adminMapper, PassengerMapper passengerMapper, DriverMapper driverMapper, VehicleMapper vehicleMapper, OrderMapper orderMapper, CityMapper cityMapper, FeedbackMapper feedbackMapper){
 return args -> {
 if (adminMapper.selectCount(null)==0){
 Admin admin = new Admin();
 admin.setUsername( admin);
 admin.setPassword(123456);
 admin.setRole(SUPER);
 adminMapper.insert(admin);
 }
 if (passengerMapper.selectCount(null)==0){
 Passenger p = new Passenger();
 p.setName(UserA); p.setPhone(13800000000); p.setRegisterTime(LocalDateTime.now()); p.setStatus(normal);
 passengerMapper.insert(p);
 }
 if (driverMapper.selectCount(null)==0){
 Driver d = new Driver();
 d.setName(DriverA); d.setPhone(13900000000); d.setLicenseNo(A12345); d.setAuditStatus(approved); d.setOnlineStatus(online);
 driverMapper.insert(d);
 Vehicle v = new Vehicle(); v.setPlateNo(GD12345); v.setModel(Car); v.setStatus(normal); v.setDriverId(d.getId());
 vehicleMapper.insert(v);
 OrderEntity o = new OrderEntity();
 o.setPassengerId(1L); o.setDriverId(d.getId()); o.setVehicleId(v.getId());
 o.setStartAddr(A); o.setEndAddr(B); o.setOrderStatus(completed); o.setAmount(new BigDecimal(25.5)); o.setCreateTime(LocalDateTime.now());
 orderMapper.insert(o);
 City c = new City(); c.setName(Shenzhen); c.setOpenStatus(open); c.setOperateStatus(running);
 cityMapper.insert(c);
 Feedback f = new Feedback(); f.setUserType(passenger); f.setUserId(1L); f.setType(suggestion); f.setContent(add coupon); f.setStatus(pending); f.setCreateTime(LocalDateTime.now());
 feedbackMapper.insert(f);
 }
 };
 }
}
