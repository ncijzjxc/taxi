package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

public class Driver {
 @TableId(type = IdType.INPUT)
 private Long id;
 private String username;
 private String name;
 private String phone;
 private String password;
 private String gender;
 private LocalDate birthday;
 private String licenseNo;
 private String carType;
 private String auditStatus;
 private String onlineStatus;
 private Long cityId;
 private LocalDateTime createTime;
 private LocalDateTime updateTime;
}
