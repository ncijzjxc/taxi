package com.ride.admin.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class City {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 private String openStatus; // open/close
 private String operateStatus; // running/paused
}

