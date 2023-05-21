package com.delivery.order.dto;

import com.delivery.order.domain.Order;
import lombok.Builder;

import java.time.LocalDateTime;

public class OrderSearchResponse {

    private Long id;
    private String orderId;
    private String orderUserId;
    private String orderAddress;
    private String orderStoreId;
    private String orderMoverId;
    private Boolean orderStarted;
    private LocalDateTime orderCreatedAt;

    @Builder
    public OrderSearchResponse(Long id, String orderId, String orderUserId, String orderAddress, String orderStoreId, String orderMoverId, Boolean orderStarted, LocalDateTime orderCreatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.orderUserId = orderUserId;
        this.orderAddress = orderAddress;
        this.orderStoreId = orderStoreId;
        this.orderMoverId = orderMoverId;
        this.orderStarted = orderStarted;
        this.orderCreatedAt = orderCreatedAt;
    }

    public static OrderSearchResponse of(Order order) {
        return OrderSearchResponse.builder()
                .id(order.getId())
                .orderId(order.getOrderId())
                .orderUserId(order.getOrderUserId())
                .orderAddress(order.getOrderAddress())
                .orderStoreId(order.getOrderStoreId())
                .orderMoverId(order.getOrderMoverId())
                .orderStarted(order.getOrderStarted())
                .orderCreatedAt(order.getOrderCreatedAt())
                .build();
    }

    public Long getId() {
        return id;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderUserId() {
        return orderUserId;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public String getOrderStoreId() {
        return orderStoreId;
    }

    public String getOrderMoverId() {
        return orderMoverId;
    }

    public Boolean getOrderStarted() {
        return orderStarted;
    }

    public LocalDateTime getOrderCreatedAt() {
        return orderCreatedAt;
    }
}
