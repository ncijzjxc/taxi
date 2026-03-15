package com.ride.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ride.admin.entity.OrderEntity;
import com.ride.admin.mapper.OrderMapper;
import com.ride.admin.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderEntity> implements OrderService {}
