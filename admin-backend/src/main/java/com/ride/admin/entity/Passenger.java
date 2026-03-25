package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

public class Passenger {
 @TableId(type = IdType.INPUT)
 private Long id;
 private String username;
 private String name;
 private String phone;
 private String password;
 private String gender;
 private LocalDate birthday;
 private String status;
 private LocalDateTime createTime;
 private LocalDateTime updateTime;
}
