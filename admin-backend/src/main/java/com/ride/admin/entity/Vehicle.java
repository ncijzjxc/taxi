package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(" vehicle\)
public class Vehicle {
 @TableId(type = IdType.AUTO)
 private Long id;
 private String plateNo;
 private String model;
 private String status;
 private Long driverId;
}
