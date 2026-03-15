package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName( passenger)
public class Passenger {
 @TableId(type = IdType.AUTO)
 private Long id;
 private String name;
 private String phone;
 private LocalDateTime registerTime;
 private String status;
}
