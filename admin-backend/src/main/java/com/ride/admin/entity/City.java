package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.time.LocalDateTime;

@Data

public class City {
 @TableId(type = IdType.AUTO)
 private Long id;
 private String name;
 private String openStatus;
 private String operateStatus;
 private LocalDateTime createTime;
 private LocalDateTime updateTime;
}
