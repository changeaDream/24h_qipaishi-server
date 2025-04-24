package com.qipaishi.modules.pay.controller;

import com.qipaishi.common.ApiResponse;
import com.qipaishi.modules.order.entity.Order;
import com.qipaishi.modules.order.service.OrderService;
import com.qipaishi.modules.pay.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pay")
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;

    public PaymentController(PaymentService paymentService, OrderService orderService) {
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ApiResponse<String> createPrepay(@RequestParam Long orderId) {
        Order order = orderService.getById(orderId);
        if (order == null) {
            return ApiResponse.error("订单不存在");
        }
        return ApiResponse.success(paymentService.createWechatPrepay(order));
    }

    @PostMapping("/callback")
    public String payCallback(@RequestBody String notifyData) {
        return paymentService.handlePayNotify(notifyData);
    }
}
