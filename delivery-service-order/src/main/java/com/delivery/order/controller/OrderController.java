package com.delivery.order.controller;

import com.delivery.order.dto.*;
import com.delivery.order.service.OrderService;
import com.delivery.user.domain.AuthorityRole;
import com.delivery.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;

    private final OrderService orderService;

    @PostMapping("/add")
    @Secured(AuthorityRole.RoleName.USER)
    public ResponseEntity<OrderAddResponse> addOrder(@AuthenticationPrincipal User user, @RequestBody OrderAddRequest orderAddRequest) {

        return ResponseEntity.ok(orderService.addOrder(orderAddRequest));
    }

    @GetMapping("/search-order-id/{orderId}")
    @Secured(AuthorityRole.RoleName.USER)
    public ResponseEntity<List<OrderSearchResponse>> findAllByOrderId(@AuthenticationPrincipal User user, @PathVariable String orderId) {

        return ResponseEntity.ok(orderService.findAllByOrderId(orderId));
    }

    @PostMapping("/search-date")
    @Secured(AuthorityRole.RoleName.USER)
    public ResponseEntity<List<OrderSearchResponse>> findAllByDate(@AuthenticationPrincipal User user, @RequestBody OrderSearchDateRequest orderSearchDateRequest) {

        return ResponseEntity.ok(orderService.findAllByOrderCreatedAtAfter(orderSearchDateRequest));
    }

    @PutMapping("/update-address")
    @Secured(AuthorityRole.RoleName.USER)
    public ResponseEntity<OrderUpdateAddressResponse> updateOrderAddress(@AuthenticationPrincipal User user, @RequestBody OrderUpdateAddressRequest orderUpdateAddressRequest) {

        return ResponseEntity.ok(orderService.updateOrderAddress(user.getUsername(), orderUpdateAddressRequest));
    }

    @GetMapping("/test")
    @Secured(AuthorityRole.RoleName.USER)
    public ResponseEntity<String> authTest(@AuthenticationPrincipal User user) {
        System.out.println(user.getUsername() + " " + user.getAuthorities());
        return ResponseEntity.ok(userService.findUserByUserId(user.getUsername()).toString());
    }
}
