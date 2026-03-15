package com.ride.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName(" price_rule\)
public class PriceRule {
 @TableId(type = IdType.AUTO)
 private Long id;
 private Long cityId;
 private String carType; // economy/premium/luxury
 private BigDecimal startPrice;
 private Integer startKm;
 private BigDecimal pricePerKm;
 private BigDecimal pricePerMin;
 private String version;
 private String status; // enabled/disabled
 private LocalDateTime effectiveTime;
 private LocalDateTime createTime;
}
