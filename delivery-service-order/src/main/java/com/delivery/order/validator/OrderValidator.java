package com.delivery.order.validator;

import com.delivery.order.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    public static final int MAXIMUM_SEARCH_DATE = 3;

    public void validateMaximumDate(LocalDateTime orderCreatedAt) {
        LocalDateTime baseDate = DateUtil.startOfDateBefore(LocalDateTime.now(), MAXIMUM_SEARCH_DATE);
        if (DateUtil.isBeforeDateFromBaseDate(baseDate, orderCreatedAt)) {
            throw new RuntimeException("최대 날짜 조회 기간을 초과하였습니다");
        }
    }

    public void validateUpdateOrderAddress(LocalDateTime orderCreatedAt, String orderUserId, String requestUserId, Boolean orderStarted) {
        validateMaximumDate(orderCreatedAt);
        validateOrderStarted(orderStarted);
        validateOrderUserId(orderUserId, requestUserId);
    }

    private void validateOrderUserId(String orderUserId, String requestUserId) {
        if (!orderUserId.equals(requestUserId)) {
            throw new RuntimeException("다른 주문자가 변경을 시도하였습니다.");
        }
    }

    private void validateOrderStarted(Boolean orderStarted) {
        if (Boolean.TRUE.equals(orderStarted)) {
            throw new RuntimeException("배달이 이미 시작되었습니다.");
        }
    }
}
