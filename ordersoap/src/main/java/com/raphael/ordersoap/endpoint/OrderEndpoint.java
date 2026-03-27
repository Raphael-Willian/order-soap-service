package com.raphael.ordersoap.endpoint;

import com.raphael.ordersoap.GetOrderRequest;
import com.raphael.ordersoap.GetOrderResponse;
import com.raphael.ordersoap.service.OrderService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.raphael.ordersoap.CreateOrderRequest;
import com.raphael.ordersoap.CreateOrderResponse;

@Endpoint
public class OrderEndpoint {

    private static final String NAMESPACE = "http://raphael.com/ordersoap";

    private final OrderService service;

    public OrderEndpoint(OrderService service) {
        this.service = service;
    }

    //create endpoint to getOrder
    @PayloadRoot(namespace = NAMESPACE, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) {
        GetOrderResponse response =  new GetOrderResponse();

        String result =  service.getOrder(request.getProduct());

        response.setMessage(result);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "CreateOrderRequest")
    @ResponsePayload
    public CreateOrderResponse createOrder(@RequestPayload CreateOrderRequest request) {

        CreateOrderResponse response = new CreateOrderResponse();

        String result = service.createOrder(request.getProduct(), request.getQuantity());

        response.setMessage(result);

        return response;

    }
}
