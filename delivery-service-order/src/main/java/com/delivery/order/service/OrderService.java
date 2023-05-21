package com.delivery.order.service;

import com.delivery.order.domain.Order;
import com.delivery.order.dto.*;
import com.delivery.order.repository.OrderRepository;
import com.delivery.order.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final OrderValidator orderValidator;

    @Transactional
    public OrderAddResponse addOrder(OrderAddRequest orderAddRequest) {
        orderValidator.validateNonExistOrder(findAllByOrderId(orderAddRequest.getOrderId()));

        Order order = orderAddRequest.to();
        return OrderAddResponse.of(orderRepository.save(order));
    }

    @Transactional(readOnly = true)
    public List<OrderSearchResponse> findAllByOrderId(String orderId) {
        return orderRepository.findAllByOrderId(orderId).stream()
                .map(OrderSearchResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OrderSearchResponse> findAllByOrderCreatedAtAfter(OrderSearchDateRequest orderSearchDateRequest) {
        LocalDateTime searchDate = orderSearchDateRequest.getSearchDate();
        orderValidator.validateMaximumDate(searchDate);

        return orderRepository.findAllByOrderCreatedAtAfter(searchDate).stream()
                .map(OrderSearchResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderUpdateAddressResponse updateOrderAddress(String requestUserId, OrderUpdateAddressRequest orderUpdateAddressRequest) {
        Order order = findByOrderId(orderUpdateAddressRequest.getOrderId());

        orderValidator.validateExistOrder(order);
        orderValidator.validateUpdateOrderAddress(order.getOrderCreatedAt(), order.getOrderUserId(), requestUserId, order.getOrderStarted());

        order.updateOrderAddress(orderUpdateAddressRequest.getUpdateOrderAddress());

        return OrderUpdateAddressResponse.of(order);
    }

    @Transactional(readOnly = true)
    private Order findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }
}
