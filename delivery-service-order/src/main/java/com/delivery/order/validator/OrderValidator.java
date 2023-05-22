package com.delivery.order.validator;

import com.delivery.order.domain.Order;
import com.delivery.order.dto.OrderSearchResponse;
import com.delivery.order.exception.*;
import com.delivery.order.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OrderValidator {
    public static final int MAXIMUM_SEARCH_DATE = 3;

    public void validateMaximumDate(LocalDateTime orderCreatedAt) {
        LocalDateTime baseDate = DateUtil.startOfDateBefore(LocalDateTime.now(), MAXIMUM_SEARCH_DATE);
        if (DateUtil.isBeforeDateFromBaseDate(baseDate, orderCreatedAt)) {
            throw new MaximumDateExceedException("최대 날짜 조회 기간을 초과하였습니다");
        }
    }

    public void validateUpdateOrderAddress(LocalDateTime orderCreatedAt, String orderUserId, String requestUserId, Boolean orderStarted) {
        validateMaximumDate(orderCreatedAt);
        validateOrderStarted(orderStarted);
        validateOrderUserId(orderUserId, requestUserId);
    }

    private void validateOrderUserId(String orderUserId, String requestUserId) {
        if (!orderUserId.equals(requestUserId)) {
            throw new UnmatchedUserException("다른 주문자가 변경을 시도하였습니다.");
        }
    }

    private void validateOrderStarted(Boolean orderStarted) {
        if (Boolean.TRUE.equals(orderStarted)) {
            throw new OrderAlreadyStartedException("배달이 이미 시작되었습니다.");
        }
    }

    public void validateNonExistOrder(List<OrderSearchResponse> orders) {
        if (!CollectionUtils.isEmpty(orders)) {
            throw new OrderAlreadyExistException("주문이 이미 존재 합니다.");
        }
    }

    public void validateExistOrder(Order order) {
        if (Objects.isNull(order)) {
            throw new OrderNotExistException("주문이 존재하지 않습니다.");
        }
    }
}
