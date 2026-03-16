package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderEntity {
 @TableId(type = IdType.AUTO)
 private Long id;
 private Long passengerId;
 private Long driverId;
 private Long vehicleId;
 private String startAddr;
 private String endAddr;
 private String orderStatus;
 private BigDecimal amount;
 private LocalDateTime createTime;
}
