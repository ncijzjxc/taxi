package com.ride.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ride.admin.entity.Vehicle;
import com.ride.admin.mapper.VehicleMapper;
import com.ride.admin.service.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {}
