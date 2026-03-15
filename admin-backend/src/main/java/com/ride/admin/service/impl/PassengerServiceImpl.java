package com.ride.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ride.admin.entity.Passenger;
import com.ride.admin.mapper.PassengerMapper;
import com.ride.admin.service.PassengerService;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService {}
