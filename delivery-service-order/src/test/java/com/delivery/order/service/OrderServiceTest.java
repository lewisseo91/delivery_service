package com.delivery.order.service;

import com.delivery.order.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("주문 서비스 테스트")
@DataJpaTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    private Long orderSeq;
    private String orderId;
    private String orderUserId;
    private String diffOrderUserId;
    private String orderAddress;
    private String orderStoreId;
    private String orderMoverId;
    private Boolean orderStarted;
    private LocalDateTime orderCreatedAt;

    private OrderAddRequest order;

    private OrderAddRequest order4DayBefore;

    private OrderAddRequest orderStartedTrue;

    private OrderAddRequest orderOwnerDiff;

    @BeforeEach
    public void setup() {
        orderSeq = 0L;
        orderId = "order_no_1";
        orderUserId = "orderUserId_1";
        diffOrderUserId = "orderUserId_Diff";
        orderAddress = "주문 주소 1";
        orderStoreId = "orderStoreId_1";
        orderMoverId = "orderMoverId_1";
        orderStarted = false;
        orderCreatedAt = LocalDateTime.now();

        order = new OrderAddRequest(
                orderSeq,
                orderId,
                orderUserId,
                orderAddress,
                orderStoreId,
                orderMoverId,
                orderStarted,
                orderCreatedAt);

        LocalDateTime nowDayBefore4 = LocalDateTime.now().minusDays(4);
        LocalDateTime startOfDayBefore4 = nowDayBefore4.toLocalDate().atStartOfDay();
        order4DayBefore = new OrderAddRequest(
                orderSeq,
                orderId,
                orderUserId,
                orderAddress,
                orderStoreId,
                orderMoverId,
                orderStarted,
                startOfDayBefore4);

        orderStartedTrue = new OrderAddRequest(
                orderSeq,
                orderId,
                orderUserId,
                orderAddress,
                orderStoreId,
                orderMoverId,
                true,
                orderCreatedAt);

        orderOwnerDiff = new OrderAddRequest(
                orderSeq,
                orderId,
                orderUserId,
                orderAddress,
                orderStoreId,
                orderMoverId,
                orderStarted,
                orderCreatedAt);
    }

    @DisplayName("배달을 등록 할 수 있다.")
    @Test
    public void orderTest() {

        // when
        OrderAddResponse savedOrder = 배달을_등록하다(order);

        // then
        assertEquals(savedOrder.getOrderAddress(), orderAddress);
        assertEquals(savedOrder.getOrderCreatedAt(), orderCreatedAt);
    }

    @DisplayName("배달 조회를 할 수 있다.")
    @Test
    public void orderSearchTest() {
        // given
        배달을_등록하다(order);

        // when
        List<OrderSearchResponse> orders = orderService.findAllByOrderId(orderId);

        // then
        assertEquals(orders.size(), 1);
        assertEquals(orders.get(0).getOrderId(), orderId);
    }

    @DisplayName("배달 조회를 기간으로 할 수 있다.")
    @Test
    public void orderSearchByDateTest() {
        // given
        배달을_등록하다(order);
        LocalDateTime nowDayBefore3 = LocalDateTime.now().minusDays(3);
        LocalDateTime startOfDayBefore3 = nowDayBefore3.toLocalDate().atStartOfDay();

        // when
        List<OrderSearchResponse> orders = orderService.findAllByOrderCreatedAtAfter(new OrderSearchDateRequest(startOfDayBefore3));

        // then
        assertEquals(orders.size(), 1);
        assertEquals(orders.get(0).getOrderId(), orderId);
    }

    @DisplayName("배달 조회는 3일 전까지만 할 수 있다.")
    @Test
    public void orderMaximumDateTest() {
        // given
        배달을_등록하다(order);
        LocalDateTime nowDayBefore4 = LocalDateTime.now().minusDays(4);
        LocalDateTime startOfDayBefore4 = nowDayBefore4.toLocalDate().atStartOfDay();

        // when & then
        assertThrows(RuntimeException.class,
                () -> orderService.findAllByOrderCreatedAtAfter(new OrderSearchDateRequest(startOfDayBefore4)));
    }

    @DisplayName("배달 주문 주소지를 수정할 수 있다.")
    @Test
    public void updateOrderAddressTest() {
        // given
        배달을_등록하다(order);
        String updatedOrderAddress = "updatedOrderAddress";
        OrderUpdateAddressRequest orderUpdateAddressRequest = new OrderUpdateAddressRequest(orderId, updatedOrderAddress);

        // when
        OrderUpdateAddressResponse updatedOrder = orderService.updateOrderAddress(orderUserId, orderUpdateAddressRequest);

        // then
        assertEquals(updatedOrder.getOrderAddress(), updatedOrderAddress);
    }

    @DisplayName("배달 주문 주소지를 3일 전 경우에만 수정할 수 있다.")
    @Test
    public void updateOrderAddressBeforeMaximumDateTest() {
        // given
        배달을_등록하다(order4DayBefore);
        String updatedOrderAddress = "updatedOrderAddress";
        OrderUpdateAddressRequest orderUpdateAddressRequest = new OrderUpdateAddressRequest(orderId, updatedOrderAddress);

        // when & then
        assertThrows(RuntimeException.class,
                () -> orderService.updateOrderAddress(orderUserId, orderUpdateAddressRequest));
    }

    @DisplayName("배달 주문 주소지를 '배달 시작됨' 전 경우에만 수정할 수 있다.")
    @Test
    public void updateOrderAddressBeforeStartTest() {
        // given
        배달을_등록하다(order);

        String updatedOrderAddress = "updatedOrderAddress";
        OrderUpdateAddressRequest orderUpdateAddressRequest = new OrderUpdateAddressRequest(orderId, updatedOrderAddress);

        // when
        OrderUpdateAddressResponse updatedOrder = orderService.updateOrderAddress(orderUserId, orderUpdateAddressRequest);

        assertFalse(updatedOrder.getOrderStarted());
    }

    @DisplayName("배달 주문 주소지를 주문자만 수정할 수 있다.")
    @Test
    public void updateOrderAddressOrderOwnerTest() {
        // given
        배달을_등록하다(order);
        String updatedOrderAddress = "updatedOrderAddress";
        OrderUpdateAddressRequest orderUpdateAddressRequest = new OrderUpdateAddressRequest(orderId, updatedOrderAddress);

        // when

        // when & then
        assertThrows(RuntimeException.class,
                () -> orderService.updateOrderAddress(diffOrderUserId, orderUpdateAddressRequest));
    }

    private OrderAddResponse 배달을_등록하다(OrderAddRequest orderAddRequest) {
        return orderService.addOrder(orderAddRequest);
    }
}