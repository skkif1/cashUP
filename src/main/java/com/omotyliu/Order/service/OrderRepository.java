package com.omotyliu.Order.service;

import com.omotyliu.Order.Order;

import java.util.List;

public interface OrderRepository {

    public Long saveOrder(Order order);
    public Order getOrder(Long id);
    public List<Order> getCustomerOrders(Long clientId);
    public List<Order> getAll(Integer limit);
}
