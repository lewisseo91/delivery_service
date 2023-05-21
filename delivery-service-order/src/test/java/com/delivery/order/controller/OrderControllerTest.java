package com.delivery.order.controller;

import com.delivery.order.dto.OrderAddRequest;
import com.delivery.order.dto.OrderSearchDateRequest;
import com.delivery.order.dto.OrderUpdateAddressRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("배달 컨트롤러 테스트")
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@WithMockUser(setupBefore = TestExecutionEvent.TEST_EXECUTION, username = "orderUserId_1", roles = {"USER"})
class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @DisplayName("배달을 등록할 수 있다.")
    @Test
    public void addOrder() throws Exception {
        Long orderSeq = 0L;
        String orderId = "order_no_1";
        String orderUserId = "orderUserId_1";
        String diffOrderUserId = "orderUserId_Diff";
        String orderAddress = "orderAddress 1";
        String orderStoreId = "orderStoreId_1";
        String orderMoverId = "orderMoverId_1";
        Boolean orderStarted = false;
        LocalDateTime orderCreatedAt = LocalDateTime.now();

        OrderAddRequest orderAddRequest = new OrderAddRequest(
                orderSeq,
                orderId,
                orderUserId,
                orderAddress,
                orderStoreId,
                orderMoverId,
                orderStarted,
                orderCreatedAt);
        String content = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(orderAddRequest);

        mvc.perform(post("/order/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(orderId)))
                .andExpect(content().string(containsString(orderAddress)))
                .andDo(print());
    }

    @DisplayName("배달을 주문 id 기준으로 조회할 수 있다.")
    @Test
    public void findAllByOrderId() throws Exception {
        addOrder();

        String orderId = "order_no_1";

        mvc.perform(get("/order/search-order-id/" + orderId))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(orderId)))
                .andDo(print());
    }

    @DisplayName("배달을 날짜별로 조회 할 수 있다.")
    @Test
    public void findAllByDate() throws Exception {
        addOrder();

        LocalDateTime orderCreatedAt = LocalDateTime.now().minusDays(1);
        OrderSearchDateRequest orderSearchDateRequest = new OrderSearchDateRequest(
                orderCreatedAt);
        String content = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(orderSearchDateRequest);

        mvc.perform(post("/order/search-date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @DisplayName("배달 주소지를 변경할 수 있다.")
    @Test
    public void updateOrderAddress() throws Exception {
        addOrder();

        String orderId = "order_no_1";
        String updateOrderAddress = "updateOrderAddress 1";

        OrderUpdateAddressRequest orderUpdateAddressRequest = new OrderUpdateAddressRequest(
                orderId,
                updateOrderAddress);
        String content = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(orderUpdateAddressRequest);

        mvc.perform(put("/order/update-address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(orderId)))
                .andExpect(content().string(containsString(updateOrderAddress)))
                .andDo(print());
    }
}