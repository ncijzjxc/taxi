package com.ride.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Driver {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private String phone;
 private String licenseNo;
 private String auditStatus; // pending/approved/rejected
 private String onlineStatus; // online/offline
}
