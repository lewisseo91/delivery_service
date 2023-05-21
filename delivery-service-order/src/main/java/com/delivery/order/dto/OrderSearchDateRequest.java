package com.delivery.order.dto;

import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
public class OrderSearchDateRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime searchDate;

    public OrderSearchDateRequest(LocalDateTime searchDate) {
        this.searchDate = searchDate;
    }

    public LocalDateTime getSearchDate() {
        return searchDate;
    }
}
