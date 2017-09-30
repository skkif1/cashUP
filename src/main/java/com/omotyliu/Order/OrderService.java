package com.omotyliu.Order;

import org.springframework.validation.Errors;

import java.util.List;

public interface OrderService {

    public List<Order> getClientsOrders(Long customerId);
    public Order createOrder(Order order);
    public List<Order> getAllOrders(Integer limit);
    public void confirmOrder(Long orderId);
    public List<String> getErrorList(Errors errors);
}
