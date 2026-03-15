package com.ride.admin.entity;

import javax.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private Long passengerId;
 private Long driverId;
 private Long vehicleId;
 private String startAddr;
 private String endAddr;
 private String orderStatus; // ongoing/completed/canceled
 private BigDecimal amount;
 private LocalDateTime createTime;
}

