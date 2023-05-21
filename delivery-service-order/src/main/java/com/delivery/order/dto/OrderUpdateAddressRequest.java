package com.delivery.order.dto;

public class OrderUpdateAddressRequest {
    private String orderId;

    private String updateOrderAddress;

    public OrderUpdateAddressRequest(String orderId, String updateOrderAddress) {
        this.orderId = orderId;
        this.updateOrderAddress = updateOrderAddress;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUpdateOrderAddress() {
        return updateOrderAddress;
    }
}
