package com.delivery.order.repository;

import com.delivery.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderId(String orderId);

    List<Order> findAllByOrderCreatedAtAfter(LocalDateTime orderCreatedAt);

    Order findByOrderId(String orderId);
}
