package com.delivery.order.service;

import com.delivery.order.domain.Order;
import com.delivery.order.repository.OrderRepository;
import com.delivery.order.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderValidator orderValidator;

    @Transactional
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllByOrderId(String orderId) {
        return orderRepository.findAllByOrderId(orderId);
    }

    @Transactional(readOnly = true)
    public List<Order> findAllByOrderCreatedAtAfter(LocalDateTime orderCreatedAt) {
        orderValidator.validateMaximumDate(orderCreatedAt);

        return orderRepository.findAllByOrderCreatedAtAfter(orderCreatedAt);
    }

    @Transactional
    public Order updateOrderAddress(String orderId, String orderUserId, String updatedOrderAddress) {
        Order order = findByOrderId(orderId);

        orderValidator.validateUpdateOrderAddress(order.getOrderCreatedAt(), order.getOrderUserId(), orderUserId, order.getOrderStarted());

        order.updateOrderAddress(updatedOrderAddress);

        return order;
    }

    @Transactional(readOnly = true)
    private Order findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
