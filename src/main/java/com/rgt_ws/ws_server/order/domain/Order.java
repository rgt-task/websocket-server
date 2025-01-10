package com.rgt_ws.ws_server.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String food;
    private String status;
    private Integer quantity;
} 