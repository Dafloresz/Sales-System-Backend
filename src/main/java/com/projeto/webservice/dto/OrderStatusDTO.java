package com.projeto.webservice.dto;

import com.projeto.webservice.entities.enums.OrderStatus;

public class OrderStatusDTO {
    private OrderStatus orderStatus;

    public OrderStatusDTO(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
