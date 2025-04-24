package com.qipaishi.modules.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qipaishi.modules.order.entity.Order;
import com.qipaishi.modules.order.mapper.OrderMapper;
import com.qipaishi.modules.order.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public Order createOrder(Long userId, Long storeId, BigDecimal amount) {
        Order order = new Order();
        order.setUserId(userId);
        order.setStoreId(storeId);
        order.setAmount(amount);
        order.setStatus(0);
        save(order);
        return order;
    }

    @Override
    public boolean updatePayStatus(Long orderId, String transactionId) {
        return lambdaUpdate()
                .eq(Order::getId, orderId)
                .set(Order::getStatus, 1)
                .set(Order::getPayTransactionId, transactionId)
                .update();
    }
}
