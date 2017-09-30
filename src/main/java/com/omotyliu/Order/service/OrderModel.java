package com.omotyliu.Order.service;

import com.omotyliu.Order.Order;
import com.omotyliu.Order.OrderService;
import com.omotyliu.Order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderModel implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderModel(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;}


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
        limit = (limit == null || limit < 0) ? Integer.MAX_VALUE : limit;
        List<Order> orders = orderRepository.getAll(limit);
        return orders;
    }

    @Override
    public void confirmOrder(Long orderId) {
        Order order = orderRepository.getOrder(orderId);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        orderRepository.saveOrder(order);
    }

    @Override
    public List<String> getErrorList(Errors errors) {
        if (errors.hasErrors())
        {
            return errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
        }
        return null;
    }
}