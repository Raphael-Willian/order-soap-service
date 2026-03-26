package com.raphael.ordersoap.service;

import com.raphael.ordersoap.CreateOrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String createOrder(String product, int quantity) {
        return "Pedido criado: " + product + " x " + quantity;
    }


}
