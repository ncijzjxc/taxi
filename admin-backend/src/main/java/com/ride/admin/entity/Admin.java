package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

public class Admin {
 @TableId(type = IdType.AUTO)
 private Long id;
 private String username;
 private String password;
 private String gender;
 private LocalDate birthday;
 private String role;
 private LocalDateTime createTime;
 private LocalDateTime updateTime;
}
