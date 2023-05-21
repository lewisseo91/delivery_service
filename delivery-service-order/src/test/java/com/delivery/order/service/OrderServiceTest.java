package com.delivery.order.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("주문 서비스 테스트")
class OrderServiceTest {

    @DisplayName("배달 조회를 할 수 있다.")
    @Test
    public void orderTest() {

    }

    @DisplayName("배달 조회는 3일 전까지만 할 수 있다.")
    @Test
    public void orderMaximumDateTest() {

    }

    @DisplayName("배달 주문 주소지를 수정할 수 있다.")
    @Test
    public void updateOrderAddressTest() {

    }

    @DisplayName("배달 주문 주소지를 3일 전 경우에만 수정할 수 있다.")
    @Test
    public void updateOrderAddressBeforeMaximumDateTest() {

    }

    @DisplayName("배달 주문 주소지를 '배달 시작됨' 전 경우에만 수정할 수 있다.")
    @Test
    public void updateOrderAddressBeforeStartTest() {

    }

    @DisplayName("배달 주문 주소지를 주문자만 수정할 수 있다.")
    @Test
    public void updateOrderAddressOrderOwnerTest() {

    }
}