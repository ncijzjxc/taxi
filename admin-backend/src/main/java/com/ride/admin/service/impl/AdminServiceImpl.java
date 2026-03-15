package com.ride.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ride.admin.entity.Admin;
import com.ride.admin.mapper.AdminMapper;
import com.ride.admin.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {}
