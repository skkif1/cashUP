package com.omotyliu.Order.service;

import com.omotyliu.Order.Order;
import com.omotyliu.Order.OrderService;
import com.omotyliu.Order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderModel implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderModel(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public List<Order> getClientsOrders(Long customerId) {
        List<Order> clientsOrder = orderRepository.getCustomerOrders(customerId);
        return clientsOrder;
    }

    @Override
    public Order createOrder(Order order) {
        Long orderId = orderRepository.saveOrder(order);
        order.setId(orderId);
        return order;
    }

    @Override
    public List<Order> getAllOrders(Integer limit) {
        orderRepository.getAll(limit);
        return null;
    }

    @Override
    public void confirmOrder(Long orderId) {
        Order order = orderRepository.getOrder(orderId);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        orderRepository.saveOrder(order);
    }
}
