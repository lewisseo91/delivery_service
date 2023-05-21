package com.delivery.order.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_user_id")
    private String orderUserId;

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "order_store_id")
    private String orderStoreId;

    @Column(name = "order_mover_id")
    private String orderMoverId;

    @Column(name = "order_started")
    private Boolean orderStarted;

    @Column(name = "order_created_at")
    private LocalDateTime orderCreatedAt;

    public Order() {
    }

    public Order(Long id, String orderId, String orderUserId, String orderAddress, String orderStoreId, String orderMoverId, Boolean orderStarted, LocalDateTime orderCreatedAt) {
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

    public void updateOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }
}
