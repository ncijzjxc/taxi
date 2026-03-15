package com.ride.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ride.admin.entity.Driver;
import com.ride.admin.mapper.DriverMapper;
import com.ride.admin.service.DriverService;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements DriverService {}
