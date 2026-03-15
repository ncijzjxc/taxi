package com.ride.admin.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String plateNo;
 private String model;
 private String status; // normal/maintenance
 private Long driverId; //1-1 bind
}

