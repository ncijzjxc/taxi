package com.ride.admin.entity;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Feedback {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String userType; // passenger/driver
 private Long userId;
 private String type; // suggestion/complaint/praise
 private String content;
 private String status; // pending/processed
 private LocalDateTime createTime;
}

