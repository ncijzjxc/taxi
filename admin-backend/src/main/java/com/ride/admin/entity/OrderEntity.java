package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("orders")
public class OrderEntity {
 @TableId(type = IdType.AUTO)
 private Long id;
 private Long passengerId;
 private Long driverId;
 private Long vehicleId;
 private Long cityId;
 private String startAddr;
 private String endAddr;
 private BigDecimal pickupLat;
 private BigDecimal pickupLng;
 private BigDecimal dropoffLat;
 private BigDecimal dropoffLng;
 private String carType; // economy/premium/luxury
 private BigDecimal distanceKm;
 private Integer durationMin;
 private BigDecimal estimatedFare;
 private Integer orderStatus;
 private BigDecimal amount;
 private LocalDateTime createTime;
}
