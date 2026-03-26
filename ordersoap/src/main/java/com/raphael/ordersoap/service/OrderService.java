package com.raphael.ordersoap.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public String createOrder(String product,int quantity) {
        return "Pedido criado: " + product + " x " + quantity;
    }


}
