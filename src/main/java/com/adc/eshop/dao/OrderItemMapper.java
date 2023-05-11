package com.adc.eshop.dao;

import com.adc.eshop.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Long orderItemId);


    List<OrderDetail> selectByOrderId(Long orderId);


    List<OrderDetail> selectByOrderIds(@Param("orderIds") List<Long> orderIds);


    int insertBatch(@Param("orderItems") List<OrderDetail> orderDetails);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}