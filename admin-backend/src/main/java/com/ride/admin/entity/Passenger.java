package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data

public class Passenger {
 @TableId(type = IdType.INPUT)
 private Long id;
 private String username;
 private String name;
 private String phone;
 private String password;
 private LocalDateTime registerTime;
 private String status;
}
