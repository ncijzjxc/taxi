package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName(" feedback\)
public class Feedback {
 @TableId(type = IdType.AUTO)
 private Long id;
 private String userType;
 private Long userId;
 private String type;
 private String content;
 private String status;
 private LocalDateTime createTime;
}
