package com.delivery.order.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@NoArgsConstructor
public class OrderSearchDateRequest {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime searchDate;

    public OrderSearchDateRequest(LocalDateTime searchDate) {
        this.searchDate = searchDate;
    }

    public LocalDateTime getSearchDate() {
        return searchDate;
    }
}
