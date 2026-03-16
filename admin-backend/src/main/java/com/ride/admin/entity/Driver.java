package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data

public class Driver {
 @TableId(type = IdType.AUTO)
 private Long id;
 private String name;
 private String phone;
 private String licenseNo;
 private String auditStatus;
 private String onlineStatus;
}
