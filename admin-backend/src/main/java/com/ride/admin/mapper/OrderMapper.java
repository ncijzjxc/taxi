package com.ride.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ride.admin.entity.OrderEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<OrderEntity> {}
