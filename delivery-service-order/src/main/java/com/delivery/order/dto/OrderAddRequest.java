package com.delivery.order.dto;

import com.delivery.order.domain.Order;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

public class OrderAddRequest {
    private Long id;
    private String orderId;
    private String orderUserId;
    private String orderAddress;
    private String orderStoreId;
    private String orderMoverId;
    private Boolean orderStarted;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderCreatedAt;

    public OrderAddRequest() {
    }

    public OrderAddRequest(Long id, String orderId, String orderUserId, String orderAddress, String orderStoreId, String orderMoverId) {
        this.id = id;
        this.orderId = orderId;
        this.orderUserId = orderUserId;
        this.orderAddress = orderAddress;
        this.orderStoreId = orderStoreId;
        this.orderMoverId = orderMoverId;
        this.orderStarted = false;
        this.orderCreatedAt = LocalDateTime.now();
    }

    public OrderAddRequest(Long id, String orderId, String orderUserId, String orderAddress, String orderStoreId, String orderMoverId, Boolean orderStarted, LocalDateTime orderCreatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.orderUserId = orderUserId;
        this.orderAddress = orderAddress;
        this.orderStoreId = orderStoreId;
        this.orderMoverId = orderMoverId;
        this.orderStarted = orderStarted;
        this.orderCreatedAt = orderCreatedAt;
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

    public Order to() {
        return Order.builder()
                .orderId(orderId)
                .orderStoreId(orderStoreId)
                .orderUserId(orderUserId)
                .orderAddress(orderAddress)
                .orderMoverId(orderMoverId)
                .orderStarted(orderStarted)
                .orderCreatedAt(orderCreatedAt)
                .build();
    }
}
